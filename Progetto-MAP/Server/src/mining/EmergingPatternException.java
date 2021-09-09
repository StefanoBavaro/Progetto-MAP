package mining;

/**
 * Classe di eccezione che modella la situazione in cui il pattern corrente non soddisfa la condizione di minimo grow rate
 */
class EmergingPatternException extends Exception {
    /**
     * Costruttore con messaggio di dettaglio
     * @param message messaggio di dettaglio dell'eccezione
     */
    EmergingPatternException(final String message) {
        super(message);
    }

}