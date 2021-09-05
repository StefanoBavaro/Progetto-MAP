package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    private Scene scene;
    private Stage stage;
    private Parent root;
    
    void printAlert(AlertType alertType, String message, ButtonType button){
        Alert alert = new Alert(alertType, message, button);
        alert.showAndWait();
    }
    
    void loading(ActionEvent actionEvent,String nameFile) throws IOException {
        root =  FXMLLoader.load(getClass().getResource(nameFile + ".fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
