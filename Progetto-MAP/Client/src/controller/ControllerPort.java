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
 * Classe che modella il Controller per la connessione al Server.
 */
public class ControllerPort extends Controller {
	
	/**
	 * Indirizzo del Server inserito dall'utente nella finestra.
	 */
	@FXML
	private TextField address;
	
	/**
	 * Porta del Server inserita dall'utente nella finestra.
	 *
	 */
	@FXML
	private TextField port;
	
	/**
	 * Connette il Client e il Server e apre la finestra per l'inserimento dei parametri.
	 * @param actionEvent azione eseguita nel momento in cui si preme il bottone "Connetti".
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
	 * Stampa un alert contenente il messaggio di aiuto; eseguito nel momento in cui si preme il bottone "Help".
	 */
	@FXML
	public void helpCommand() {
		printAlert(Alert.AlertType.NONE, Constants.HELP, ButtonType.OK);
	}
	
	/**
	 * Carica e mostra a video la nuova finestra per l'inserimento dei parametri.
	 * @param actionEvent azione eseguita nel momento in cui si preme il bottone "Connetti".
	 * @throws IOException se si verificano errori di caricamento della nuova finestra.
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
