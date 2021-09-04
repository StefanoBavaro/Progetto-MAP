package controller;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;

abstract class Controller {
        void printAlert(AlertType alertType, String message, ButtonType button){
            Alert alert = new Alert(alertType, message, button);
            alert.showAndWait();
        }
}
