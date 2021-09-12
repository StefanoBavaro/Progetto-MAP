package data;

import java.io.Serializable;

/**
 * Classe che modella un attributo discreto rappresentando l’insieme di valori distinti del relativo dominio.
 * Estende la classe Attribute.
 */
public class DiscreteAttribute extends Attribute implements Serializable {

    /**
     * Array di stringhe, una per ciascun valore discreto, che rappresenta il domino dell’attributo.
     */
    private String[] values;

    /**
     * Costruttore: invoca il costruttore della classe madre e avvalora l'array <code>values</code> con i valori discreti in input.
     * @param name nome simbolico dell'attributo.
     * @param index identificativo numerico dell'attributo.
     * @param values valori discreti che costituiscono il dominio dell'attributo.
     */
    DiscreteAttribute(final String name, final int index, final String[] values) {
        super(name, index);
        this.values = new String[values.length];
        this.values = values;
    }

    /**
     * Restituisce la cardinalità del membro <code>values</code>.
     * @return numero di valori discreti dell'attributo.
     */
    public int getNumberOfDistinctValues() {
        return values.length;
    }

    /**
     * Restituisce il valore in posizione index del membro <code>values</code>.
     * @param index indice di tipo intero.
     * @return valore nel dominio dell’attributo.
     */
    public String getValue(final int index) {
        return values[index];
    }

}
