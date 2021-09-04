package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

class ControllerParameters extends Controller{
	
	// dove inserire il close e il continue ?
	
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	@FXML
	TextField option;
	@FXML
	TextField minSup;
	@FXML
	TextField growRate;
	@FXML
	TextField targ;
	@FXML
	TextField back;
	
	@FXML
	void PatternMining(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PrintResult.fxml"));
			String o = option.getText();
			float sup = new Float(minSup.getText());
			float rate = new Float(growRate.getText());
			String target = targ.getText();
			String background = back.getText();
			try {
				ManagerConnection.getManagerConnection().openStreams();
				ManagerConnection.getManagerConnection().getOutputStream().writeObject(o);
				ManagerConnection.getManagerConnection().getOutputStream().writeObject(sup);
				ManagerConnection.getManagerConnection().getOutputStream().writeObject(rate);
				ManagerConnection.getManagerConnection().getOutputStream().writeObject(target);
				ManagerConnection.getManagerConnection().getOutputStream().writeObject(background);
			} catch (IOException e) {
				printAlert(Alert.AlertType.ERROR, "Errore nell'invio dei dati al Server" , ButtonType.OK);
			}
			root =  loader.load();
			stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, "Errore nel Caricamento della pagina" , ButtonType.OK);
		}
		
	}
}
