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

/**
 * Classe astratta che rappresenta i Controller per la gestione dell'interfaccia grafica
 *
 * @author Lorenzo, Jacopo, Stefano
 */
public abstract class Controller {
    
    /**
     * <code> scene </code> oggetto contenente le informazioni grafiche
     */
    private Scene scene;
    
    /**
     * <code> stage </code> rappresenta la finestra
     */
    private Stage stage;
    
    /**
     * <code> root </code> rappresenta il nodo genitore della scena corrente
     */
    private Parent root;
    
    /**
     * Metodo che stampa a video un alert
     *
     * @param alertType rappresenta il tipo di alert
     *
     * @param message rappresenta il messaggio che si vuole stampare a video
     *
     * @param button rappresenta il bottone che sarà presente nell'alert
     */
    public void printAlert(AlertType alertType, String message, ButtonType button) {
        Alert alert = new Alert(alertType, message, button);
        alert.showAndWait();
    }
    
    /**
     * Metodo che rappresenta l'uscita dall'applicazione
     *
     * @param actionEvent azione compiuta dall'interfaccia quando si vuole eseguire l'uscita dall'applicazione
     */
    @FXML
    public void exit(ActionEvent actionEvent) {
        try {
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
            ManagerConnection.getManagerConnection().closeConnection();
        }catch(IOException e){
            printAlert(Alert.AlertType.ERROR, Constants.CLOSING_CONNECTION_ERROR, ButtonType.OK);
        }
    }
    
    /**
     * Metodo che restiutisce stage che rappresenta la finestra
     *
     * @return l'attributo d'istanza stage
     */
    Stage getStage () {
        return stage;
    }
    
    /**
     * Metodo che restituisce root, il nodo genitore della scena
     *
     * @return l'attributo d'istanza root
     */
    Parent getRoot() {
        return root;
    }
    
    /**
     * Metodo che restituisce scene, avente le informazioni grafiche
     *
     * @return l'attributo d'istanza scene
     */
    Scene getScene () {
        return scene;
    }
    
    /**
     * Metodo di set per l'attributo root
     *
     * @param root rappresenta la nuva root
     */
    void setRoot (Parent root) {
        this.root = root;
    }
    
    /**
     * Metodo di set per l'attributo scene
     *
     * @param scene rappresenta la nuva scena
     */
    void setScene (Scene scene) {
        this.scene = scene;
    }
    
    /**
     * Metodo di set per l'attributo stage
     *
     * @param stage rappresenta il nuov stage 
     */
    void setStage (Stage stage) {
        this.stage = stage;
    }
    
}
