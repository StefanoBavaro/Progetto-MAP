package controller;

import connection.ManagerConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utility.Constants;
import java.io.IOException;

public abstract class Controller {
    private Scene scene;
    private Stage stage;
    private Parent root;
    
    public void printAlert(AlertType alertType, String message, ButtonType button) {
        Alert alert = new Alert(alertType, message, button);
        alert.showAndWait();
    }
    
    
    @FXML
    public void exit(ActionEvent actionEvent) {
        try {
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
            ManagerConnection.getManagerConnection().closeConnection();
        }catch(IOException e){
            printAlert(Alert.AlertType.ERROR, Constants.CLOSING_CONNECTION_ERROR, ButtonType.OK);
        }
    }
    
    Stage getStage () {
        return stage;
    }
    
    Parent getRoot() {
        return root;
    }
    
    Scene getScene () {
        return scene;
    }
    
    void setRoot (Parent root) {
        this.root = root;
    }
    
    void setScene (Scene scene) {
        this.scene = scene;
    }
    
    void setStage (Stage stage) {
        this.stage = stage;
    }
    
}
