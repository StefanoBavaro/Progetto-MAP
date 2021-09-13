package controller;

import connection.ManagerConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.Constants;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Classe che modella il Controller per la gestione dell'immissione dei parametri da inviare al Server.
 */
public class ControllerParameters extends Controller{
	
	/**
	 * Bottone per l'inserimento del tipo di ricerca "Nuova scoperta".
	 */
	@FXML
	private RadioButton discovery;
	
	/**
	 * Bottone per l'inserimento del tipo di ricerca "Archivio".
	 */
	@FXML
	private RadioButton archive;
	
	/**
	 * Testo dove inserire il minimo supporto per la ricerca dei pattern.
	 */
	@FXML
	private TextField minSup;
	
	/**
	 * Testo dove inserire il grow rate per la ricerca dei pattern.
	 */
	@FXML
	private TextField growRate;
	
	/**
	 * Testo dove inserire il nome della tabella target per la ricerca dei pattern.
	 */
	@FXML
	private TextField targ;
	
	/**
	 * Testo dove inserire il nome della tabella di background per la ricerca dei pattern.
	 */
	@FXML
	private TextField back;
	
	/**
	 * Carica e restituisce il controller della nuova finestra.
	 * @return controller della nuova finestra.
	 * @throws IOException se si verificano errori di caricamento della finestra.
	 */
	public ControllerResults loadingController() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.PRINT_RESULT));
		setRoot(loader.load());
		setStage(new Stage());
		getStage().setTitle(Constants.TITLE);
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().show();
		getStage().setMinWidth(500);
		getStage().setMinHeight(600);
		return loader.getController();
	}
	
	/**
	 * Metodo che controlla i parametri non inseriti per la ricerca di un nuovo pattern
	 *
	 * @throws ControllerException sollevata in caso in cui ci sono parametri vuoti, richiamando il costruttore con
	 * la stringa contente una descrizione sui parametri non inseriti
	 *
	 */
	private void checkEmptyParameters() throws ControllerException {
		String error = new String();
		if (!discovery.isSelected() && !archive.isSelected()) {
			error += Constants.NOT_SELECTED_SEARCH_OPTION + "\n";
		}
		if (targ.getText().isEmpty()) {
			error += Constants.EMPTY_TARGET_FIELD + "\n";
		}
		if (back.getText().isEmpty()) {
			error += Constants.EMPTY_BACKGROUND_FIELD + "\n";
		}
		if (minSup.getText().isEmpty()) {
			error += Constants.EMPTY_SUPPORT_FIELD + "\n";
		}
		if (growRate.getText().isEmpty()) {
			error += Constants.EMPTY_GROWRATE_FIELD;
		}
		if (!error.isEmpty()) {
			throw new ControllerException(error);
		}
	}
	
	/**
	 * Metodo che si occupa di controllare che i valori del minimo supporto e del growRate siano corretti
	 *
	 * @param sup indica il minimo supporto
	 *
	 * @param rate indica il growRate
	 *
	 * @throws ControllerException sollevata nel momento in cui il valore del minimo supporto sia maggiore di 1 o minore di 0
	 * oppure nel momento in cui il valore del growRate è minore di 0
	 */
	private void checkNumber(float sup, float rate) throws ControllerException{
		if (sup <= Constants.VALUE_ZERO || sup > Constants.VALUE_ONE) {
			throw new ControllerException(Constants.ERROR_SUPPORT_VALUE);
		}
		if(rate <= Constants.VALUE_ZERO){
			throw new ControllerException(Constants.ERROR_GROWRATE_VALUE);
		}
	}
	
	/**
	 * Metodo che si occupa di pulire i campi per la ricerca dei pattern
	 *
	 * @param actionEvent azione compiuta dall'interfaccia nel momento in cui si preme il bottone "Pulisci"
	 */
	@FXML
	public void clear(ActionEvent actionEvent) {
		discovery.setSelected(false);
		archive.setSelected(false);
		minSup.clear();
		targ.clear();
		growRate.clear();
		back.clear();
	}
	
	/**
	 * Metodo che restituisce un valore numerico in base al criterio di ricerca selezionato
	 *
	 * @return un intero:
	 * 		<ul>
	 * 		 	<li> 2 se è stato selezionato come criteri di ricerca "Archivio"</li>
	 *			<li> 1 altrimenti </li>
	 * 		</ul>
	 */
	private int optionValue() {
		return (archive.isSelected() ? Constants.VALUE_TWO : Constants.VALUE_ONE);
	}
	
	/**
	 * Metodo che controlla che non ci siano stati errori nella ricerca dei pattern
	 *
	 * @param freqPattern indica i frequentPattern trovati dal Server
	 *
	 * @param emergPattern indica gli EmergingPattern trovati dal Server
	 *
	 * @throws ControllerException sollevata nel momento in cui negli emergingPattern si trova il valore di default
	 * che indica che ci sono stati degli errori nella ricerca oppure quando i pattern sono vuoti
	 *
	 */
	private void checkError(String freqPattern, String emergPattern) throws ControllerException {
		if (emergPattern.equals(Constants.DEFAULT)) {
			throw new ControllerException(freqPattern);
		} else if (freqPattern.isEmpty() && emergPattern.isEmpty()) {
			throw new ControllerException(Constants.ERROR_TABLES);
		}
	}
	
	/**
	 * Metodo che si occupa di inviare i dati al Server e in caso non ci siano errori mostrare la nuova finestra dove
	 * si trovano i risultati
	 *
	 * @param actionEvent azione compiuta dall'interfaccia nel momento in cui si preme il bottone "Invia"
	 */
	@FXML
	public void patternMining (ActionEvent actionEvent) {
		try {
			checkEmptyParameters();
			int option = optionValue();
			float sup = new Float(minSup.getText());
			float rate = new Float(growRate.getText());
			checkNumber(sup, rate);
			String target = targ.getText();
			String background = back.getText();
			
			ManagerConnection.getManagerConnection().ServerComunication(option, sup, rate, target, background);
			String freqPattern = ManagerConnection.getManagerConnection().readString();
			String emergPattern = ManagerConnection.getManagerConnection().readString();
			
			checkError(freqPattern, emergPattern);
			ControllerResults controllerResults = loadingController();
			controllerResults.printResults(freqPattern, emergPattern, sup, rate, target, background);
			((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
		} catch (IOException  | ClassNotFoundException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_SENDING_DATA_SERVER, ButtonType.OK);
		} catch (ControllerException e) {
			printAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
		} catch (IllegalArgumentException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_NUMBER, ButtonType.OK);
		}
	}
}
