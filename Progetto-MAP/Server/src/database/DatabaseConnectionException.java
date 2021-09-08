package database;

/**
 * Classe di eccezioni per modellare situazioni di errore di connessione ad un database.
 */
public class DatabaseConnectionException extends Exception {

	/**
	 * Costruttore con messaggio di dettaglio
	 * @param msg messaggio di dettaglio dell'eccezione
	 */
	DatabaseConnectionException(String msg){
		super(msg);
	}
}
