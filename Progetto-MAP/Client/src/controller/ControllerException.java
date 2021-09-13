package controller;

/**
 * Classe che rappresenta le eccezioni sollevate in caso di errori nella gestione dei Controller
 */
class ControllerException extends Exception {
	
	/**
	 * Costruttore per la classe ControllerException.
	 *
	 * @param controller Messaggio che spiega il motivo per cui l'eccezione  è stata sollevata
	 */
	ControllerException(String controller) {
		super(controller);
	}
}
