package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Classe che rappresenta la gestione della connessione tra il Client e il Server
 *
 * @author Lorenzo Cassano, Jacopo D'Abramo, Stefano Bavaro
 */

public class ManagerConnection {
	
	/**
	 * <code> socket </code> usato per creare la connessione con il Server
	 *
	 */
	private Socket socket;
	
	/**
	 * <code> ou </code> rappresenta l'oggetto di OutputStream per scrivere i dati al Server
	 *
	 */
	private ObjectOutputStream ou;
	
	/**
	 * <code> in </code> rappresenta l'oggetto di InputStream per leggere i dati dal Server
	 *
	 */
	private ObjectInputStream in;
	
	/**
	 * <code> SINGLETON </code> rappresenta il singoletto istanziato al momentodella creazione
	 * di una connessione con il Server
	 *
	 */
	private static final ManagerConnection SINGLETON = new ManagerConnection();
	
	/**
	 * Costruttore della classe ManagerConnection reso privato in modo da poter avere una classe singoletto
	 *
	 */
	private ManagerConnection() {}
	
	/**
	 * Metodo che restituisce l'oggetto per la gestione della connessione
	 *
	 *
	 * @return il valore dell'attributo SINGLETON
	 */
	public static ManagerConnection getManagerConnection() {
		return SINGLETON;
	}
	
	/**
	 * Metodo che inizializza la connessione con il Server
	 *
	 * @param address rappresenta l'indirizzo alla quale collegarsi
	 *
	 * @param port rappresenta la porta alla quale collegarsi
	 *
	 *
	 * @throws IOException sollevata in caso ci siano errori nell'inizializzare della connessione
	 */
	public void initConnection(String address, String port) throws IOException {
		InetAddress addr = InetAddress.getByName(address);
		socket = new Socket(addr, new Integer(port));
		openStreams();
	}
	
	/**
	 * Metodo che istanzia gli oggetti di Input e Output Stream
	 *
	 *
	 * @throws IOException sollevata in caso di errori nell'apertura degli Stream
	 */
	private void openStreams() throws IOException {
		ou = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	/**
	 * Metodo che si occupa di chiudere gli Stream di Input e Output
	 *
	 *
	 * @throws IOException sollevata in caso di errore nella chiusura degli Stream
	 */
	private void closeStreams() throws IOException {
		ou.close();
		in.close();
	}
	
	/**
	 * Metodo che si occupa di chiudere la connessione
	 *
	 *
	 * @throws IOException sollevata in caso di errore nella chiusura della connessione
	 */
	public void closeConnection() throws IOException {
		closeStreams();
		socket.close();
	}
	
	/**
	 * Metodo che restituisce l'oggetto InputStream
	 *
	 *
	 * @return l'oggetto che rappresenta l'InputStream
	 */
	public ObjectInputStream getInputStream() {
		return in;
	}
	
	/**
	 * Metodo che restituisce l'oggetto OutputStream
	 *
	 *
	 * @return l'oggetto che rappresenta l'OutputStream
	 */
	public ObjectOutputStream getOutputStream() {
		return ou;
	}
	
	/**
	 * Metodo che si occupa di scrivere al Server le informazioni utili per la ricerca dei pattern
	 *
	 * @param op rappresenta l'opzione della ricerca
	 *
	 * @param sup rappresenta il minimo supporto
	 *
	 * @param rate rappresenta il growRate
	 *
	 * @param target rappresenta la tabella Target su cui andare a effettuare la ricerca
	 *
	 * @param back rappresenta la tabella Background su cui andare ad effettuare la ricerca
	 *
	 *
	 * @throws IOException sollevata in caso di errori di scrittura dei dati al Server
	 */
	public void ServerComunication(int op, float sup, float rate, String target, String back) throws IOException {
		getOutputStream().writeObject(op);
		getOutputStream().writeObject(sup);
		getOutputStream().writeObject(rate);
		getOutputStream().writeObject(target);
		getOutputStream().writeObject(back);
	}
	
	/**
	 * Metodo che restituisce l'oggetto letto dal Server
	 *
	 * @return l'oggetto letto dal Server che è una Stringa
	 *
	 * @throws IOException sollevata in caso di errore di lettura dell'oggetto
	 *
	 * @throws ClassNotFoundException sollevata in caso non venga riconosciuto la classe dell'oggetto letto
	 */
	public String readString() throws IOException, ClassNotFoundException {
		return (String) in.readObject();
	}
}
