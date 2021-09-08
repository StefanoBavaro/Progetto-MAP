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
import utility.Constants;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.PRINT_RESULT));
		setRoot(loader.load());
		setStage(new Stage());
		getStage().setTitle(Constants.TITLE);
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().show();
		getStage().setMinWidth(500);
		getStage().setMinHeight(600);
		return loader.getController();
	}
	
	private void checkEmptyParameters() throws ControllerException {
		String error = new String();
		if (!discovery.isSelected() && !archive.isSelected()) {
			error += Constants.NOT_SELECTED_SEARCH_OPTION + "\n";
		}
		if (targ.getText().isEmpty()) {
			error += Constants.EMPTY_TARGET_FIELD + "\n";
		}
		if (back.getText().isEmpty()) {
			error += Constants.EMPTY_BACKGROUND_FIELD + "\n";
		}
		if (minSup.getText().isEmpty()) {
			error += Constants.EMPTY_SUPPORT_FIELD + "\n";
		}
		if (growRate.getText().isEmpty()) {
			error += Constants.EMPTY_GROWRATE_FIELD;
		}
		if (!error.isEmpty()) {
			throw new ControllerException(error);
		}
	}
	
	private void checkNumber(float sup, float rate) throws ControllerException{
		if (sup <= Constants.VALUE_ZERO || sup > Constants.VALUE_ONE) {
			throw new ControllerException(Constants.ERROR_SUPPORT_VALUE);
		}
		if(rate <= Constants.VALUE_ZERO){
			throw new ControllerException(Constants.ERROR_GROWRATE_VALUE);
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
		return (archive.isSelected() ? Constants.VALUE_TWO : Constants.VALUE_ONE);
	}
	
	private void checkError(String freqPattern, String emergPattern) throws ControllerException {
		if (emergPattern.equals(Constants.DEFAULT)) {
			throw new ControllerException(freqPattern);
		} else if (freqPattern.isEmpty() && emergPattern.isEmpty()) {
			throw new ControllerException(Constants.ERROR_TABLES);
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
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_SENDING_DATA_SERVER, ButtonType.OK);
		} catch (ControllerException e) {
			printAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
		} catch (IllegalArgumentException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_NUMBER, ButtonType.OK);
		}
	}
}
