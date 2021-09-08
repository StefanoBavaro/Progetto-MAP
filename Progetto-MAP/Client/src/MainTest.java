
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.Constants;

import java.io.IOException;


public class MainTest extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
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
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

	/*
		InetAddress addr = InetAddress.getByName(args[0]);
		System.out.println("addr = " + addr + "\nport=" + args[1]);
		Socket socket = new Socket(addr, new Integer(args[1]));
		System.out.println(socket);
		
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());	; // stream con richieste del client
		
		
		char risp='s';
		do{
			System.out.println("Scegli una opzione:");
			int opzione;
			do{
				System.out.println("1:Nuova scoperta");
				System.out.println("2: Risultati in archivio");
				opzione=Keyboard.readInt();
			}while(opzione!=1 && opzione!=2);
			
			float minsup=0f;float minGr=0f;
			do{
				System.out.println("Inserire valore minimo supporto (minsup>0 e minsup<=1):");
				minsup=Keyboard.readFloat();
			}while(minsup<=0 || minsup>1);
			
			do{
				System.out.println("Inserire valore minimo grow rate (minGr>0):");
				minGr=Keyboard.readFloat();
			}while(minGr<=0);
				
			System.out.println("Tabella target:");
			String targetName=Keyboard.readString();
			System.out.println("Tabella background:");
			String backgroundName=Keyboard.readString();
			String nameFile =targetName+"_"+backgroundName;
			try{
				out.writeObject(opzione);
			out.writeObject(minsup);
			out.writeObject(minGr);
			out.writeObject(targetName);
			out.writeObject(backgroundName);
			String fpMiner=(String)(in.readObject());
			System.out.println(fpMiner);
			String epMiner=(String)(in.readObject());
				
			System.out.println(epMiner);
			}
			catch(IOException | ClassNotFoundException e){
				System.out.println("Errore");
				e.printStackTrace();
			}

			System.out.println("Vuoi ripetere?(s/n)");
			risp=Keyboard.readChar();
		}while(risp!='n');
		
		
		
	}
*/

