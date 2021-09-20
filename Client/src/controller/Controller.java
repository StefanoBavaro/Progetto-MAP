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
 * Classe astratta che rappresenta i Controller per la gestione dell'interfaccia grafica.
 */
public abstract class Controller {
    
    /**
     * Oggetto contenente le informazioni grafiche.
     */
    private Scene scene;
    
    /**
     * Singola finestra.
     */
    private Stage stage;
    
    /**
     * Nodo genitore della scena corrente.
     */
    private Parent root;
    
    /**
     * Stampa a video un alert.
     * @param alertType tipologia di alert.
     * @param message messaggio che si vuole stampare a video.
     * @param button bottone che sarà presente nell'alert.
     */
    public void printAlert(AlertType alertType, String message, ButtonType button) {
        Alert alert = new Alert(alertType, message, button);
        alert.showAndWait();
    }
    
    /**
     * Permette l'uscita dall'applicazione.
     * @param actionEvent azione compiuta dall'interfaccia quando si vuole eseguire l'uscita dall'applicazione.
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
     * Restiutisce il contenuto del membro <code>stage</code>, che rappresenta la finestra.
     * @return finestra del Controller.
     */
    Stage getStage () {
        return stage;
    }
    
    /**
     * Restituisce il contenuto del membro <code>root</code>, il nodo genitore della scena.
     * @return nodo genitore del Controller.
     */
    Parent getRoot() {
        return root;
    }
    
    /**
     * Restituisce il contenuto del membro <code>scene</code>, avente le informazioni grafiche.
     * @return scena del Controller.
     */
    Scene getScene () {
        return scene;
    }
    
    /**
     * Avvalora l'attributo <code>root</code> con il parametro in input.
     * @param root nodo genitore da impostare.
     */
    void setRoot (Parent root) {
        this.root = root;
    }
    
    /**
     * Avvalora l'attributo <code>scene</code> con il parametro in input.
     * @param scene scena da impostare.
     */
    void setScene (Scene scene) {
        this.scene = scene;
    }

    /**
     * Avvalora l'attributo <code>stage</code> con il parametro in input.
     * @param stage stage da impostare.
     */
    void setStage (Stage stage) {
        this.stage = stage;
    }
    
}
