package mining;

import data.Attribute;

import java.io.Serializable;

/**
 * Classe astratta che modella un generico item (coppia attributo-valore).
 */
abstract class Item implements Serializable{
    /**
     * Attributo coinvolto nell'item.
     */
    private Attribute attribute;
    /**
     * Valore assegnato all'attributo.
     */
    private Object value;

    /**
     * Costruttore: inizializza i membri <code>attribute</code> e <code>value</code> con i parametri passati come argomento.
     * @param attribute attributo dell'item.
     * @param value valore dell'item.
     */
    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce il membro <code>attribute</code>.
     * @return attributo dell'item.
     */
    Attribute getAttribute() {
        return attribute;
    }

    /**
     * Restituisce il membro <code>value</code>.
     * @return valore dell'item.
     */
    Object getValue() {
        return value;
    }

    /**
     * Metodo astratto da realizzare nelle sottoclassi.
     * @param value valore con cui si effettua la verifica delle condizioni dell'item.
     * @return booleano che indica l'esito della verifica.
     */
    abstract boolean checkItemCondition(Object value);

    /**
     * Override del metodo toString() di Object.
     * Concatena in una stringa l'attributo e il valore dell'item, separati da "=".
     * @return stringa che rappresenta una coppia attributo-valore.
     */
    public String toString() {
        return attribute.toString() + "=" + value.toString();
    }
}
