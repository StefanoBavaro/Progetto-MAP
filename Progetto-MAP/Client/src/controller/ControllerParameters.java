package controller;

import connection.ManagerConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	
	
	public ControllerResults loadingController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Costants.PRINT_RESULT));
		setRoot(loader.load());
		setStage(new Stage());
		getStage().setTitle(Costants.TITLE);
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().show();
		getStage().setMinWidth(500);
		getStage().setMinHeight(600);
		return loader.getController();
	}
	
	private void checkEmptyParameters() throws ControllerException {
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
	
	private void checkNumber(float sup, float rate) throws ControllerException{
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
	private int optionValue() {
		return (archive.isSelected() ? Costants.VALUE_TWO : Costants.VALUE_ONE);
	}
	
	private void checkError(String freqPattern, String emergPattern) throws ControllerException {
		if (emergPattern.isEmpty()) {
			throw new ControllerException(freqPattern);
		}
	}
	
	@FXML
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
			String freqPattern = ManagerConnection.getManagerConnection().readString();
			String emergPattern = ManagerConnection.getManagerConnection().readString();
			
			checkError(freqPattern, emergPattern);
			ControllerResults controllerResults = loadingController();
			controllerResults.printResults(freqPattern, emergPattern, sup, rate, target, background);
			((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
		} catch (IOException  | ClassNotFoundException e) {
			printAlert(Alert.AlertType.ERROR, Costants.ERROR_SENDING_DATA_SERVER, ButtonType.OK);
		} catch (ControllerException e) {
			printAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
		} catch (IllegalArgumentException e) {
			printAlert(Alert.AlertType.ERROR, Costants.ERROR_NUMBER, ButtonType.OK);
		}
	}
}
