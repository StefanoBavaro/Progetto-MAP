package database;

import utility.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che gestisce l'accesso al DB per la lettura dei dati di training.
 */
public class DbAccess {

	/**
	 * Nome del driver del DBMS al quale ci si vuole collegare.
	 */
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

	/**
	 * Protocollo per aprire il database.
	 */
	private final String DBMS = "jdbc:mysql";

	/**
	 * Identificativo del server su cui risiede la base di dati.
	 */
	private final String SERVER = "localhost";

	/**
	 * La porta su cui il DBMS accetta le connessioni.
	 */
	private final int PORT = 3306;

	/**
	 * Nome della base di dati.
	 */
	private final String DATABASE = "Map";

	/**
	 * Nome dell’utente per l’accesso alla base di dati.
	 */
	private final String USER_ID = "Student";

	/**
	 * Password di autenticazione per l’utente identificato da <code>USER_ID</code>.
	 */
	private final String PASSWORD = "map";

	/**
	 * Sessione di connessione al database.
	 */
	private Connection conn;

	/**
	 * Impartisce al Class Loader l’ordine di caricare il driver MySQL;
	 * Inizializza la connessione al db riferita da <code>conn</code>.
	 * @throws DatabaseConnectionException in caso di errori di connessione al database.
	 */
	public void initConnection() throws DatabaseConnectionException{
		String connectionString =  DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE
				+ "?user=" + USER_ID + "&password=" + PASSWORD + "&serverTimezone=UTC";
		try {
			
				Class.forName(DRIVER_CLASS_NAME).newInstance();
			} 
		catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DatabaseConnectionException(e.toString());
			}
		catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new DatabaseConnectionException(e.toString());
			} 
		catch (ClassNotFoundException e) {
			System.out.println(Constants.DRIVER_NOT_FOUND + DRIVER_CLASS_NAME);
			throw new DatabaseConnectionException(e.toString());
		}
		try {
			conn = DriverManager.getConnection(connectionString, USER_ID, PASSWORD);
		} catch (SQLException e) {
			System.out.println(Constants.ERROR_CONNECTION_DB);
			e.printStackTrace();
			throw new DatabaseConnectionException(e.toString());
		}
		
	}

	/**
	 * Restituisce il membro <code>conn</code>.
	 * @return riferimento all'oggetto Connection che gestisce la connessione.
	 */
	public Connection getConnection(){
		return conn;
	}

	/**
	 * Chiude la connessione <code>conn</code>.
	 */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(Constants.ERROR_CLOSING_CONNECTION);
		}
	}

}
