package data;

import java.io.Serializable;

/**
 * Classe astratta che modella un generico attributo discreto o continuo.
 */
public abstract class Attribute implements Serializable {
    /**
     * Nome simbolico dell'attributo
     */
    private String name;

    /**
     * Identificativo numerico dell'attributo; indica la posizione della colonna che rappresenta l’attributo nella tabella di dati
     */
    private int index;

    /**
     * Costruttore: inizializza i valori dei membri name e index
     * @param name nome simbolico dell'attributo
     * @param index identificativo numerico dell'attributo
     */
    public Attribute(final String name, final int index) {
        this.name = new String(name);
        this.index = index;
    }

    /**
     * Restituisce il valore nel membro name
     * @return nome dell'attributo
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce il valore nel membro index;
     * @return identificativo numerico dell'attributo
     */
    public int getIndex() {
        return index;
    }

    /**
     * Override del toString di Object; restituisce il valore del membro name.
     * @return nome dell'attributo
     */
    public String toString() {
        return name;
    }
}
