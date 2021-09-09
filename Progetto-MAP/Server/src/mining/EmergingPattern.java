package mining;

import java.io.Serializable;

/**
 * Classe che modella un pattern emergente; estende FrequentPattern
 */
class EmergingPattern extends FrequentPattern implements Serializable {
    private final float growrate;

    /**
     * Costruttore: chiama il costruttore della superclasse passandogli <code>fp</code> e inizializza il membro <code>growrate</code> con l’argomento del costruttore
     * @param fp pattern
     * @param growrate grow rate del pattern
     */
    EmergingPattern(FrequentPattern fp, float growrate) {
        super(fp);
        this.growrate = growrate;
    }

    /**
     * Restituisce il valore del membro growrate
     * @return grow rate del pattern
     */
    float getGrowRate() {
        return growrate;
    }

    /**
     * Override del metodo toString() di FrequentPattern. Si crea e restituisce la stringa che rappresenta il
     * pattern (usando il <code>toString()</code> di FrequentPattern), il suo supporto e il suo growrate.
     * @return stringa contenente il pattern emergente nella forma "pattern [supporto] [growrate]"
     */
    public String toString() {
        String value = super.toString();
        value += " [" + growrate + "]";
        return value;
    }
}
