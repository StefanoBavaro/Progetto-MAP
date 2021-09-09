package mining;

import data.DiscreteAttribute;
import java.io.Serializable;

/**
 * Classe concreta che modella una coppia <Attributo discreto - valore discreto> ; estende Item.
 */
class DiscreteItem extends Item implements Serializable{
    /**
     * Invoca il costruttore della classe madre per avvalorarne i membri
     * @param attribute attributo discreto
     * @param value valore dell'attributo discreto
     */
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    /**
     * Verifica che il membro value sia uguale (nello stato) all’argomento passato come parametro della funzione
     * @param value valore di cui verificare l'uguaglianza con il membro <code>value</code>
     * @return <code>true</code> se le stringhe (parametro value e membro value) siano uguali, <code>false</code> altrimenti.
     */
    boolean checkItemCondition(Object value) {
        String s = (String) value;
        String h = (String) getValue();
        return s.equals(h);
    }
}
