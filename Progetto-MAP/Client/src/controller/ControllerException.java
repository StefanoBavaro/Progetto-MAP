package controller;

/**
 * Classe che modella le eccezioni sollevate in caso di errori nella gestione dei Controller.
 */
class ControllerException extends Exception {

	/**
	 * Costruttore con messaggio di dettaglio.
	 * @param controller messaggio di dettaglio dell'eccezione.
	 */
	ControllerException(String controller) {
		super(controller);
	}
}
