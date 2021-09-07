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
import utility.Costants;

import java.io.IOException;

public class ControllerResults extends Controller{
	
	@FXML
	private Label support;
	@FXML
	private Label growRate;
	@FXML
	private Label target;
	@FXML
	private Label background;
	@FXML
	private TextArea area;
	
	
	void printResults (String frequentPattern, String mergingPattern, float minsup, float rate, String targetName, String backgroundName) {
		support.setText(String.valueOf(minsup));
		growRate.setText(String.valueOf(rate));
		target.setText(targetName);
		background.setText(backgroundName);
		area.setText(Costants.FREQUENT_PATTERN_PRINT + frequentPattern + Costants.EMERGING_PATTERN_PRINT + mergingPattern);
	}
	
	@FXML
	public void newSearch(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Costants.INSERT_PARAMETERS));
			setRoot(loader.load());
			setStage(new Stage());
			getStage().setTitle(Costants.TITLE);
			setScene(new Scene(getRoot()));
			getStage().setScene(getScene());
			getStage().show();
			((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
		} catch (IOException e) {
			printAlert(Alert.AlertType.ERROR, Costants.ERROR_LOADING_PAGE, ButtonType.OK);
		}
	}
}
