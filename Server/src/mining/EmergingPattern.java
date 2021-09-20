package mining;


/**
 * Classe che modella un pattern emergente; estende la classe <code>FrequentPattern</code>.
 */
class EmergingPattern extends FrequentPattern {
    /**
     *  Grow rate del pattern emergente.
     */
    private float growrate;

    /**
     * Costruttore: richiama il costruttore della superclasse passandogli <code>fp</code> e inizializza il membro <code>growrate</code> con il parametro in input.
     * @param fp frequent pattern.
     * @param growrate grow rate del pattern.
     */
    EmergingPattern(FrequentPattern fp, float growrate) {
        super(fp);
        this.growrate = growrate;
    }

    /**
     * Restituisce il valore del membro <code>growrate</code>.
     * @return grow rate del pattern
     */
    float getGrowRate() {
        return growrate;
    }

    /**
     * Override del metodo toString() di FrequentPattern.
     * Si concatena e restituisce la stringa che rappresenta il pattern, il suo supporto e il suo growrate.
     * @return stringa contenente il pattern emergente nella forma "pattern [supporto] [growrate]"
     */
    public String toString() {
        String value = super.toString();
        value += " [" + growrate + "]";
        return value;
    }
}
