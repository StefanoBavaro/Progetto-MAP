
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.Constants;

import java.io.IOException;

/**
 * Classe che modella il main del Client.
 */
public class MainTest extends Application {
	
	/**
	 * Apre la prima finestra dell'applicazione.
	 * @param primaryStage finestra che deve essere visualizzata.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Constants.INSERT_PORT));
			primaryStage.setTitle(Constants.TITLE);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setMinWidth(Constants.MIN_WIDTH_MAIN);
			primaryStage.setMinHeight(Constants.MIN_HEIGHT);
		}catch (IOException e){
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		
	}
	
	/**
	 * Richiama <code>launch()</code> in modo da poter caricare la prima finestra dell'applicazione.
	 * @param args stringhe inserite al lancio del programma.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}


