package controller;

import connection.ManagerConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.stage.Stage;
import utility.Costants;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControllerPort extends Controller {
	@FXML
	private TextField address;
	@FXML
	private TextField port;
	
	@FXML
	public void clientConnection (ActionEvent actionEvent) {
		try {
			String addr = address.getText();
			String p = port.getText();
			ManagerConnection.getManagerConnection().initConnection(addr,p);
			loadingClient(actionEvent);
		} catch (LoadException e) {
			printAlert(Alert.AlertType.ERROR, Costants.ERROR_LOADING_PAGE, ButtonType.OK);
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, Costants.ERROR_OPENING_STREAMS, ButtonType.OK);
		}
	}
	
	
	@FXML
	public void helpCommand() {
		printAlert(Alert.AlertType.NONE, Costants.HELP, ButtonType.OK);
	}
	
	
	private void loadingClient(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Costants.INSERT_PARAMETERS));
		setRoot(loader.load());
		setStage((Stage)((Node) actionEvent.getSource()).getScene().getWindow());
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().show();
	}
}
