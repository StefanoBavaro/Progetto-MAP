package controller;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControllerPort extends Controller {
	@FXML
	TextField address;
	@FXML
	TextField port;
	
	@FXML
	public void clientConnection (ActionEvent actionEvent) throws IOException {
		try {
			String addr = address.getText();
			String p = port.getText();
			ManagerConnection.getManagerConnection().initConnection(addr,p);
			loading(actionEvent, "fxml/InsertParameters");
		} catch (LoadException e) {
			printAlert(Alert.AlertType.ERROR, "Errore nel Caricamento della pagina" , ButtonType.OK);
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, "Errore nell'apertura degli streams", ButtonType.OK);
		}
	}
	
	@FXML
	public void helpCommand() {
		printAlert(Alert.AlertType.NONE, "Progetto Map \nCiao\n \n \n \nCIao", ButtonType.OK);
	}
}
