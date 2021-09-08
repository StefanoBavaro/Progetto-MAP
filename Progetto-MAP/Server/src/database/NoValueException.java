package database;

/**
 *  Classe di eccezioni per modellare l’assenza di un valore all’interno di un resultset
 */
public class NoValueException extends Exception {
	/**
	 * Costruttore con messaggio di dettaglio
	 * @param msg messaggio di dettaglio dell'eccezione
	 */
	public NoValueException(String msg) {
		super(msg);
	}

}
