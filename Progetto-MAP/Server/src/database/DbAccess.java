package database;

import utility.Costants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.InstanceNotFoundException;

/**
 * Classe che gestisce l'accesso al DB per la lettura dei dati di training
 * @author Map Tutor
 */
public class DbAccess {

	/**
	 * Nome del driver del DBMS al quale ci si vuole collegare
	 */
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

	/**
	 * Protocollo per aprire il db
	 */
	private final String DBMS = "jdbc:mysql";

	/**
	 * Identificativo del server su cui risiede la base di dat
	 */
	private final String SERVER = "localhost";

	/**
	 * La porta su cui il DBMS accetta le connessioni
	 */
	private final int PORT = 3306;

	/**
	 * Nome della base di dati
	 */
	private final String DATABASE = "Map";

	/**
	 * Nome dell’utente per l’accesso alla base di dati
	 */
	private final String USER_ID = "Student";

	/**
	 * Password di autenticazione per l’utente identificato da USER_ID
	 */
	private final String PASSWORD = "map";

	/**
	 * Gestisce una connessione al db
	 */
	private Connection conn;

	/**
	 * Impartisce al class loader l’ordine di caricare il driver mysql;
	 * inizializza la connessione al db riferita da conn.
	 * @throws DatabaseConnectionException
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
			System.out.println(Costants.DRIVER_NOT_FOUND + DRIVER_CLASS_NAME);
			throw new DatabaseConnectionException(e.toString());
		}
		
		try {
			conn = DriverManager.getConnection(connectionString, USER_ID, PASSWORD);
			
		} catch (SQLException e) {
			System.out.println(Costants.ERROR_CONNECTION_DB);
			e.printStackTrace();
			throw new DatabaseConnectionException(e.toString());
		}
		
	}

	/**
	 * @return il valore dell'attributo conn.
	 */
	public  Connection getConnection(){
		return conn;
	}

	/**
	 * Chiude la connessione conn
	 */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(Costants.ERROR_CLOSING_CONNECTION);
		}
	}

}
