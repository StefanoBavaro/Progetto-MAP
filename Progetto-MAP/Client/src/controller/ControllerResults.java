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
 * Classe Controller per la gestione della stampa dei pattern trovati
 *
 *
 * @author Lorenzo Cassano, Jacopo D'Abramo, Stefano Bavaro
 */
public class ControllerResults extends Controller{
	
	/**
	 * <code> support </code> indica il valore del minimo Supporto immesso per la ricerca dei pattern
	 */
	@FXML
	private Label support;
	
	/**
	 * <code> growRate </code> indica il valore del growRate immesso per la ricerca dei pattern
	 */
	@FXML
	private Label growRate;
	
	/**
	 * <code> target </code> indica la tabella target immessa per la ricerca dei pattern
	 */
	@FXML
	private Label target;
	
	/**
	 * <code> background </code> indica la tabella background immessa per la ricerca dei pattern
	 */
	@FXML
	private Label background;
	
	/**
	 * <code> area </code> indica l'area di testo dove verranno stampati i pattern trovati
	 */
	@FXML
	private TextArea area;
	
	
	/**
	 * Metodo che scrive i label nei vari campi e scrive i pattern trovati nell'apposita area
	 *
	 * @param frequentPattern indica i frequentPattren trovati
	 *
	 * @param mergingPattern indica gli emergingPattern trovati
	 *
	 * @param minsup indica il minimo supporto immesso per la ricerca dei pattern
	 *
	 * @param rate indica il growRate immesso per la ricerca dei pattern
	 *
	 * @param targetName indica il nome della tabella target sulla quale ricercare i pattern
	 *
	 * @param backgroundName indica il nome della tabella background sulla quale ricercare i pattern
	 *
	 */
	void printResults (String frequentPattern, String mergingPattern, float minsup, float rate, String targetName, String backgroundName) {
		support.setText(String.valueOf(minsup));
		growRate.setText(String.valueOf(rate));
		target.setText(targetName);
		background.setText(backgroundName);
		if (frequentPattern.isEmpty()) {
			area.setText(Constants.FREQUENT_PATTERN_EMPTY + Constants.EMERGING_PATTERN_PRINT + mergingPattern);
		} else if (mergingPattern.isEmpty()) {
			area.setText(Constants.FREQUENT_PATTERN_PRINT + frequentPattern + "\nEmerging pattern vuoto");
		} else {
			area.setText(
					Constants.FREQUENT_PATTERN_PRINT + frequentPattern + Constants.EMERGING_PATTERN_PRINT + mergingPattern);
			}
		}
	
	/**
	 * Metodo che chiude la finestra e apre una nuova finestra relativa all'inserimento di nuovi parametri
	 *
	 * @param actionEvent indica l'azione eseguita nel momento in cui si preme il bottone "Nuova Ricerca"
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
