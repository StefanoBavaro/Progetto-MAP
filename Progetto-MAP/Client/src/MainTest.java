
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.Constants;

import java.io.IOException;

/**
 * Classe che rappresenta il main del Client
 *
 * @author Lorenzo Cassano, Jacopo D'Abramo, Stefano Bavaro
 */
public class MainTest extends Application {
	
	/**
	 * Metodo che occupa di aprire la prima finestra dell'applicazione
	 *
	 * @param primaryStage indica la finestra che deve essere visualizzata
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
	 * Main richiama launch in modo da poter caricare la prima finestra dell'applicazione
	 *
	 * @param args indica le stringhe in input
	 */
	public static void main(String[] args) {
		launch(args);
	}
}


