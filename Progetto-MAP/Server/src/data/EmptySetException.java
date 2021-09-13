package data;

/**
 * Classe che modella l’eccezione che occorre qualora l'insieme di training sia vuoto (non contiene transazioni/esempi).
 */
public class EmptySetException extends Exception {

    /**
     * Costruttore con messaggio di dettaglio.
     * @param message messaggio di dettaglio dell'eccezione.
     */
    public EmptySetException(String message) {
        super(message);
    }

}
