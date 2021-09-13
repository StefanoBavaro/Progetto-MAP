package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Classe singoletto che modella la gestione della connessione tra il Client e il Server.
 */

public class ManagerConnection {
	
	/**
	 * Terminale lato client della connessione client-server.
	 */
	private Socket socket;
	
	/**
	 * Stream di output per la comunicazione con il server.
	 */
	private ObjectOutputStream ou;
	
	/**
	 * Stream di input per la comunicazione con il server.
	 */
	private ObjectInputStream in;
	
	/**
	 * Unico oggetto <code>ManagerConnection</code> istanziato al momento della creazione di una connessione con il Server.
	 */
	private static final ManagerConnection SINGLETON = new ManagerConnection();
	
	/**
	 * Costruttore: reso privato per poter avere una classe singoletto.
	 */
	private ManagerConnection() {}
	
	/**
	 * Restituisce il contenuto del membro <code>SINGLETON</code>.
	 * @return l'unica istanza di <code>ManagerConnection</code> per la gestione della connessione
	 */
	public static ManagerConnection getManagerConnection() {
		return SINGLETON;
	}
	
	/**
	 * Inizializza la connessione con il Server.
	 * @param address indirizzo alla quale collegarsi.
	 * @param port porta alla quale collegarsi.
	 * @throws IOException se si verificano errori di I/O nel momento della creazione della socket o dell'apertura degli stream di I/O.
	 */
	public void initConnection(String address, String port) throws IOException {
		InetAddress addr = InetAddress.getByName(address);
		socket = new Socket(addr, new Integer(port));
		openStreams();
	}
	
	/**
	 * Inizializza i membri <code>ou</code> e <code>in</code> con riferimenti agli stream rispettivamente di Input e Output.
	 * @throws IOException se si verificano errori nell'apertura degli stream.
	 */
	private void openStreams() throws IOException {
		ou = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	/**
	 * Chiude gli stream di Input e Output.
	 * @throws IOException se si verificano errori nella chiusura degli Stream.
	 */
	private void closeStreams() throws IOException {
		ou.close();
		in.close();
	}
	
	/**
	 * Chiude la connessione con il server.
	 * @throws IOException se si verificano errori nella chiusura degli stream di I/O o nella chiusura della socket.
	 */
	public void closeConnection() throws IOException{
		closeStreams();
		socket.close();
	}
	
	/**
	 * Restituisce il contenuto del membro <code>in</code>.
	 * @return l'oggetto che rappresenta l'InputStream.
	 */
	public ObjectInputStream getInputStream() {
		return in;
	}
	
	/**
	 * Restituisce il contenuto del membro <code>ou</code>.
	 * @return l'oggetto che rappresenta l'OutputStream.
	 */
	public ObjectOutputStream getOutputStream() {
		return ou;
	}
	
	/**
	 * Invia al Server le informazioni utili per la ricerca dei pattern
	 * @param op opzione della ricerca.
	 * @param sup minimo supporto.
	 * @param rate grow rate.
	 * @param target tabella target su cui effettuare la ricerca.
	 * @param back tabella di background su cui andare ad effettuare la ricerca.
	 * @throws IOException se si verificano errori in fase di scrittura dei dati al server.
	 */
	public void ServerComunication(int op, float sup, float rate, String target, String back) throws IOException {
		getOutputStream().writeObject(op);
		getOutputStream().writeObject(sup);
		getOutputStream().writeObject(rate);
		getOutputStream().writeObject(target);
		getOutputStream().writeObject(back);
	}
	
	/**
	 * Restituisce l'oggetto letto dal Server castato a <code>String</code>.
	 * @return oggetto letto dal Server sotto forma di stringa.
	 * @throws IOException se si verificano errori in fase di lettura dell'oggetto.
	 * @throws ClassNotFoundException se la classe dell'oggetto serializzato letto non viene riconosciuta.
	 */
	public String readString() throws IOException, ClassNotFoundException {
		return (String) in.readObject();
	}
}
