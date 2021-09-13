package mining;

import data.DiscreteAttribute;
import java.io.Serializable;

/**
 * Classe concreta che modella una coppia &ltAttributo discreto - valore discreto&gt ; estende e realizza la classe astratta <code>Item</code>.
 */
class DiscreteItem extends Item implements Serializable{
    /**
     * Costruttore: invoca il costruttore della classe madre per avvalorarne i membri.
     * @param attribute attributo discreto.
     * @param value valore dell'attributo discreto.
     */
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    /**
     * Verifica che il membro <code>value</code> sia uguale (nello stato) all’argomento passato come parametro.
     * @param value valore di cui verificare l'uguaglianza con il membro <code>value</code>.
     * @return <code>true</code> se le stringhe (parametro e membro <code>value</code>) sono uguali, <code>false</code> altrimenti.
     */
    boolean checkItemCondition(Object value) {
        String s = (String) value;
        String h = (String) getValue();
        return s.equals(h);
    }
}
