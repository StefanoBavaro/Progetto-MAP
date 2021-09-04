package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ControllerResults extends Controller{
	
	@FXML
	Label support;
	@FXML
	Label growRate;
	@FXML
	Label target;
	@FXML
	Label background;
	@FXML
	TextArea area;
	
	public void printResults (String frequentPattern, String mergingPattern, float minsup, float rate, String targetName, String backgroundName) {
		support.setText(String.valueOf(minsup));
		growRate.setText(String.valueOf(rate));
		target.setText(targetName);
		background.setText(backgroundName);
		area.setText("Frequent Pattern\n" + frequentPattern + "\nEmerging Pattern \n" + mergingPattern);
	}
}
