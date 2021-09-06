package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.Costants;
import javafx.event.ActionEvent;
import utility.ControllerException;

import java.io.IOException;


public class ControllerParameters extends Controller{
	
	@FXML
	private RadioButton discovery;
	@FXML
	private RadioButton archive;
	@FXML
	private TextField minSup;
	@FXML
	private TextField growRate;
	@FXML
	private TextField targ;
	@FXML
	private TextField back;
	
	// dubbio loading--------------------------------------------------------------------------
	public ControllerResults loadingController (ActionEvent actionEvent, String nameFile) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PrintResults.fxml"));
		setRoot(loader.load());
		setStage(new Stage());
		getStage().setTitle(Costants.TITLE);
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().show();
		return loader.getController();
	}
	
	void checkEmptyParameters() throws ControllerException {
		//target background supporto growrate
		if (!discovery.isSelected() && !archive.isSelected()) {
			throw new ControllerException(Costants.NOT_SELECTED_SEARCH_OPTION);
		} else if (targ.getText().isEmpty()) {
			throw new ControllerException(Costants.EMPTY_TARGET_FIELD);
		} else if (back.getText().isEmpty()) {
			throw new ControllerException(Costants.EMPTY_BACKGROUND_FIELD);
		} else if (minSup.getText().isEmpty()) {
			throw new ControllerException(Costants.EMPTY_SUPPORT_FIELD);
		} else if (growRate.getText().isEmpty()) {
			throw new ControllerException(Costants.EMPTY_GROWRATE_FIELD);
		}
	}
	
	void checkNumber(float sup, float rate) throws ControllerException{
		if (sup <= Costants.VALUE_ZERO || sup > Costants.VALUE_ONE) {
			throw new ControllerException(Costants.ERROR_SUPPORT_VALUE);
		}
		if(rate <= Costants.VALUE_ZERO){
			throw new ControllerException(Costants.ERROR_GROWRATE_VALUE);
		}
	}
	@FXML
	public void clear(ActionEvent actionEvent) {
		discovery.setSelected(false);
		archive.setSelected(false);
		minSup.clear();
		targ.clear();
		growRate.clear();
		back.clear();
	}
	
	// verrà richiamata sempre dopo il controllo dei parametri vuoti
	int optionValue() {
		return (archive.isSelected() ? Costants.VALUE_TWO : Costants.VALUE_ONE);
	}
	
	
	public void patternMining (ActionEvent actionEvent) {
		try {
			checkEmptyParameters();
			int option = optionValue();
			float sup = new Float(minSup.getText());
			float rate = new Float(growRate.getText());
			checkNumber(sup, rate);
			String target = targ.getText();
			String background = back.getText();
			ManagerConnection.getManagerConnection().ServerComunication(option, sup, rate, target, background);
			
			// ---------------------------------------dubbio ------------------------------------
			String freqPattern = (String) (ManagerConnection.getManagerConnection().getInputStream().readObject());
			String emergPattern = (String) (ManagerConnection.getManagerConnection().getInputStream().readObject());
			//---------------------------------------------------------------------------------------
			
			ControllerResults controllerResults = loadingController(actionEvent, "fxml/PrintResults");
			controllerResults.printResults(freqPattern,emergPattern, sup, rate, target, background);
		} catch (IOException  | ClassNotFoundException e) {
			printAlert(Alert.AlertType.ERROR, Costants.ERROR_SENDING_DATA_SERVER, ButtonType.OK);
		} catch (ControllerException e) {
			printAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
		} catch (IllegalArgumentException e) {
			printAlert(Alert.AlertType.ERROR, "In support e growRate devono essere inseriti valori numerici", ButtonType.OK);
		}
	}
}
