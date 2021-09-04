package controller;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.SocketException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ControllerPort extends Controller {
	@FXML
	TextField address;
	@FXML
	TextField port;
	
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	public void clientConnection (ActionEvent actionEvent) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertParameters.fxml"));
			String addr = address.getText();
			String p = port.getText();
			ManagerConnection manager = ManagerConnection.getManagerConnection();
			manager.initConnection(addr,p);
			root = loader.load();
			stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (LoadException e) {
			printAlert(Alert.AlertType.ERROR, "Errore nel Caricamento della pagina" , ButtonType.OK);
		} catch (SocketException e) {
			printAlert(Alert.AlertType.ERROR, "Connessione con il server fallita", ButtonType.OK);
		}
	}
}
