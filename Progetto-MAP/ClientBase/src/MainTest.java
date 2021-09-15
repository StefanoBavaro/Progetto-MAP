import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import keyboardinput.Keyboard;
import utility.Constants;

/**
 * Classe che modella il main del Progetto Base.
 */
public class MainTest {
	
	/**
	 * Main del progetto base che si occupa di collegarsi al Server ed inviare i dati
	 *
	 * @param args array di stringhe che rappresentano l'address e la porta da inserire per avviare l'applicazione
	 *
	 * @throws IOException sollevata in caso di errori di comunicazione con il Socket
	 */
	public static void main(String[] args) throws IOException {
		
		InetAddress addr = InetAddress.getByName(args[0]);
		System.out.println(Constants.ADDR + addr + Constants.PORT + args[1]);
		Socket socket = new Socket(addr, new Integer(args[1]));
		System.out.println(socket);
		
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());	; // stream con richieste del client
		
		
		char risp= Constants.YES;
		do{
			System.out.println(Constants.OPTION);
			int opzione;
			do{
				System.out.println(Constants.DISCOVERY);
				System.out.println(Constants.ARCHIVE);
				opzione=Keyboard.readInt();
			}while(opzione != 1 && opzione != 2);
			
			float minsup= 0f;
			float minGr= 0f;
			do{
				System.out.println(Constants.INSERT_SUPPORT);
				minsup=Keyboard.readFloat();
			}while(minsup <= 0 || minsup > 1);
			
			do{
				System.out.println(Constants.INSERT_GROW_RATE);
				minGr=Keyboard.readFloat();
			}while(minGr <= 0);
			
			System.out.println(Constants.TARGET_TABLE);
			String targetName=Keyboard.readString();
			System.out.println(Constants.BACKGROUND_TABLE);
			String backgroundName=Keyboard.readString();
			String nameFile = targetName + "_" + backgroundName;
			try {
				out.writeObject(opzione);
				out.writeObject(minsup);
				out.writeObject(minGr);
				out.writeObject(targetName);
				out.writeObject(backgroundName);
				String fpMiner=(String)(in.readObject());
				String epMiner=(String)(in.readObject());
				// rilevazione e stampa di errore
				if (epMiner.equals(Constants.DEFAULT)) {
					System.out.println(fpMiner);
				} else if(epMiner.isEmpty() && fpMiner.isEmpty()) {
					System.out.println(Constants.ERROR_TABLES);
				} else { // non sono stati trovati errori
					System.out.println(fpMiner);
					System.out.println(epMiner);
				}
			}
			catch(IOException | ClassNotFoundException e){
				System.out.println(Constants.ERROR);
				e.printStackTrace();
			}
			
			System.out.println(Constants.REPEAT);
			risp=Keyboard.readChar();
		}while(risp != Constants.NO);
		
	}
}
