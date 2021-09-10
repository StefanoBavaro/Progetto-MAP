package utility;

/**
 * Classe che rappresenta le eccezioni sollevate in caso di errori nella gestione dei Controller
 *
 * @author Lorenzo Cassano, Jacopo D'Abramo, Stefano Bavaro
 */
public class ControllerException extends Exception {
	
	/**
	 * Costruttore per la classe ControllerException.
	 *
	 * @param controller Messaggio che spiega il motivo per cui l'eccezione  è stata sollevata
	 */
	public ControllerException(String controller) {
		super(controller);
	}
}
