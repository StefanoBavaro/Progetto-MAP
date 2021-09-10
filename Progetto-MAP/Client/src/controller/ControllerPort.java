package controller;

import connection.ManagerConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.stage.Stage;
import utility.Constants;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Classe che gestisce il Controller per la connessione al Server
 *
 * @author Lorenzo, Jacopo, Stefano
 */
public class ControllerPort extends Controller {
	
	/**
	 * <code> address </code> rappresenta l'indirizzo del Server inserito all'interno della finestra
	 *
	 */
	@FXML
	private TextField address;
	
	/**
	 * <code> port </code> rappresenta la porta del Server inserito all'interno della finestra
	 *
	 */
	@FXML
	private TextField port;
	
	/**
	 * Metodo che connette il Client e il Server e apre la finestra per l'inserimento dei parametri
	 *
	 * @param actionEvent indica l'azione eseguita nel momento in cui si preme il bottone "Connetti"
	 */
	@FXML
	public void clientConnection (ActionEvent actionEvent) {
		try {
			String addr = address.getText();
			String p = port.getText();
			ManagerConnection.getManagerConnection().initConnection(addr,p);
			loadingClient(actionEvent);
		} catch (LoadException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_LOADING_PAGE, ButtonType.OK);
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_PORT_LOC, ButtonType.OK);
		} catch (NumberFormatException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_PORT, ButtonType.OK);
		}
	}
	
	/**
	 * Metodo che stampa un alert contenente l'help eseguito nel momento in cui si preme il bottone "Esci"
	 *
	 */
	@FXML
	public void helpCommand() {
		printAlert(Alert.AlertType.NONE, Constants.HELP, ButtonType.OK);
	}
	
	/**
	 * Metodo che si occupa del caricamento e di mostrare a video la nuova finestra per l'inserimento dei parametri
	 *
	 * @param actionEvent indica l'azione eseguita nel momento in cui si preme il bottone "Connetti"
	 *
	 * @throws IOException sollevata in caso di errore di caricamento della nuva finestra
	 */
	private void loadingClient(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.INSERT_PARAMETERS));
		setRoot(loader.load());
		setStage((Stage)((Node) actionEvent.getSource()).getScene().getWindow());
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().show();
		getStage().setMinHeight(Constants.MIN_WIDTH_MAIN);
		getStage().setMinWidth(Constants.MIN_WIDTH_P);
	}
}
