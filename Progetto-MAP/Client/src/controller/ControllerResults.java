package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utility.Constants;

import java.io.IOException;

/**
 * Classe che modella il Controller per la stampa dei pattern trovati.
 */
public class ControllerResults extends Controller{
	
	/**
	 * Valore del minimo supporto immesso per la ricerca dei pattern.
	 */
	@FXML
	private Label support;
	
	/**
	 * Valore del grow rate immesso per la ricerca dei pattern.
	 */
	@FXML
	private Label growRate;
	
	/**
	 * Tabella target immessa per la ricerca dei pattern.
	 */
	@FXML
	private Label target;
	
	/**
	 * Tabella di background immessa per la ricerca dei pattern.
	 */
	@FXML
	private Label background;
	
	/**
	 * Area di testo dove stampare i pattern trovati.
	 */
	@FXML
	private TextArea area;
	
	/**
	 * Scrive i label nei vari campi e scrive i pattern trovati nell'apposita area.
	 * @param frequentPattern pattern frequenti trovati.
	 * @param emergingPattern pattern emergenti trovati.
	 * @param minsup minimo supporto immesso per la ricerca dei pattern.
	 * @param rate grow rate immesso per la ricerca dei pattern.
	 * @param targetName nome della tabella target sulla quale ricercare i pattern.
	 * @param backgroundName nome della tabella background sulla quale ricercare i pattern.
	 */
	void printResults (String frequentPattern, String emergingPattern, float minsup, float rate, String targetName, String backgroundName) {
		support.setText(String.valueOf(minsup));
		growRate.setText(String.valueOf(rate));
		target.setText(targetName);
		background.setText(backgroundName);
		if (frequentPattern.isEmpty()) {
			area.setText(Constants.FREQUENT_PATTERN_EMPTY + Constants.EMERGING_PATTERN_PRINT + emergingPattern);
		} else if (emergingPattern.isEmpty()) {
			area.setText(Constants.FREQUENT_PATTERN_PRINT + frequentPattern + "\nEmerging pattern vuoto");
		} else {
			area.setText(
					Constants.FREQUENT_PATTERN_PRINT + frequentPattern + Constants.EMERGING_PATTERN_PRINT + emergingPattern);
			}
		}
	
	/**
	 * Chiude la finestra e apre una nuova finestra relativa all'inserimento di nuovi parametri.
	 * @param actionEvent azione eseguita nel momento in cui si preme il bottone "Nuova Ricerca".
	 */
	@FXML
	public void newSearch(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.INSERT_PARAMETERS));
			setRoot(loader.load());
			setStage(new Stage());
			getStage().setTitle(Constants.TITLE);
			setScene(new Scene(getRoot()));
			getStage().setScene(getScene());
			getStage().show();
			getStage().setMinHeight(Constants.MIN_WIDTH_MAIN);
			getStage().setMinWidth(Constants.MIN_WIDTH_P);
			((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, Constants.ERROR_LOADING_PAGE, ButtonType.OK);
		}
	}
}
