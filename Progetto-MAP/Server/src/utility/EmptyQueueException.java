package utility;

/**
 * Classe che modella l’eccezione che occorre qualora si cerca di leggere/cancellare da una coda è vuota
 */
public class EmptyQueueException extends Exception {

    /**
     * Costruttore con messaggio di dettaglio
     * @param message messaggio di dettaglio dell'eccezione
     */
    public EmptyQueueException(final String message) {
        super(message);
    }


}
