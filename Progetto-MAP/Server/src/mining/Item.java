package mining;

import data.Attribute;

import java.io.Serializable;

/**
 * Classe astratta che modella un generico item (coppia attributo-valore)
 */
abstract class Item implements Serializable{
    /**
     * Attributo coinvolto nell'item
     */
    private Attribute attribute;
    /**
     * Valore assegnato all'attributo
     */
    private Object value;

    /**
     * Costruttore: inizializza i valori dei membri con i parametri passati come argomento al costruttore
     * @param attribute riferimento ad attributo con cui inizializzare il campo <code>attribute</code>
     * @param value riferimento a valore con cui inizializzare il campo <code>value</code>
     */
    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce il membro <code>attribute</code>
     * @return attributo membro dell'item
     */
    Attribute getAttribute() {
        return attribute;
    }

    /**
     * Restituisce il membro <code>value</code>
     * @return valore coinvolto nell'item
     */
    Object getValue() {
        return value;
    }

    /** !!!!!!!!!!!!!!!!!!!!!!!!!!!11dire cosa fa param e reutrn
     * Metodo astratto da realizzare nelle sottoclassi
     * @param value
     * @return
     */
    abstract boolean checkItemCondition(Object value);

    /**
     * Restituisce una stringa nella forma &ltattribute&gt=&ltvalue&gt
     * @return stringa che rappresenta una coppia attributo-valore
     */
    public String toString() {
        return attribute.toString() + "=" + value.toString();
    }
}
