package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;


public class ControllerParameters extends Controller{
	
	@FXML
	RadioButton discovery;
	@FXML
	RadioButton archive;
	@FXML
	TextField minSup;
	@FXML
	TextField growRate;
	@FXML
	TextField targ;
	@FXML
	TextField back;
	
	
	@FXML
	public void exit(ActionEvent actionEvent) {
		try {
			((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
			ManagerConnection.getManagerConnection().closeConnection();
		}catch(IOException e){
			printAlert(Alert.AlertType.ERROR, "Errore di chiusura della connessione", ButtonType.OK);
		}
	}
	
	void checkEmptyParameters() {
		//target background supporto growrate
		if (!discovery.isSelected() && !archive.isSelected()) {
			throw new IllegalArgumentException("Non è stato selezionato il criterio  di ricerca");
		} else if (targ.getText() == "") {
			throw new IllegalArgumentException("Non è stato inserito nessuno valore in target");
		} else if (back.getText() == "") {
			throw new IllegalArgumentException("Non è stato inserito nessuno valore in backGround");
		} else if (minSup.getText() == "") {
			throw new IllegalArgumentException("Non è stato inserito nessuno valore in supporto");
		} else if (growRate.getText() == "") {
			throw new IllegalArgumentException("Non è stato inserito nessuno valore in growRate");
		}
	}
	
	void checkNumber(float sup, float rate) {
		if (sup <= 0 || sup > 1) {
			throw new IllegalArgumentException("Il valore di support deve essere compreso tra 0 e 1");
		}
		if(rate <= 0){
			throw new IllegalArgumentException("Il valore di growRate deve essere maggiore di 0");
		}
	}
	@FXML
	public void clear(ActionEvent actionEvent) {
		discovery.setSelected(false);
		archive.setSelected(false);
		minSup.clear();
		targ.clear();
		growRate.clear();
		back.clear();
	}
	
	// verrà richiamata sempre dopo il controllo dei parametri vuoti
	int optionValue() {
		return (archive.isSelected() ? 2 : 1);
	}
	
	
	public void patternMining (ActionEvent actionEvent) {
		try {
			System.out.println(targ.getText());
			checkEmptyParameters();
			int option = optionValue();
			float sup = new Float(minSup.getText());
			float rate = new Float(growRate.getText());
			checkNumber(sup, rate);
			String target = targ.getText();
			String background = back.getText();
			try {
				ManagerConnection.getManagerConnection().ServerComunication(option, sup, rate, target, background);
			} catch (IOException e) {
				printAlert(Alert.AlertType.ERROR, "Errore nell'invio dei dati al Server", ButtonType.OK);
			}
			loading(actionEvent, "PrintResult");
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, "Errore nel Caricamento della pagina", ButtonType.OK);
		} /*catch (NumberFormatException e) {
			printAlert(Alert.AlertType.ERROR, "Inserire valori numerici in support e growrate", ButtonType.OK);
		} */catch (IllegalArgumentException e) {
			printAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
		}
	}
}
