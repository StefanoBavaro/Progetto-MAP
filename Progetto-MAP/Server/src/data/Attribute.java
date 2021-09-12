package data;

import java.io.Serializable;

/**
 * Classe astratta che modella un generico attributo discreto o continuo.
 */
public abstract class Attribute implements Serializable {
    /**
     * Nome simbolico dell'attributo.
     */
    private String name;

    /**
     * Identificativo numerico dell'attributo; indica la posizione della colonna che rappresenta l’attributo nella tabella di dati.
     */
    private int index;

    /**
     * Costruttore: inizializza i valori dei membri <code>name</code> e <code>index</code>.
     * @param name nome simbolico dell'attributo.
     * @param index identificativo numerico dell'attributo.
     */
    public Attribute(final String name, final int index) {
        this.name = new String(name);
        this.index = index;
    }

    /**
     * Restituisce il valore nel membro <code>name</code>.
     * @return nome dell'attributo.
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce il valore nel membro <code>index</code>.
     * @return identificativo numerico dell'attributo.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Override del metodo <code>toString()</code> di <code>Object</code>.
     * Restituisce il valore del membro <code>name</code>.
     * @return nome dell'attributo.
     */
    public String toString() {
        return name;
    }
}
