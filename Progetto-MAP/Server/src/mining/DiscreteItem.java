package mining;

import data.DiscreteAttribute;

/**
 * Classe concreta che modella una coppia &#60;Attributo discreto - valore discreto&#62; ; estende e realizza la classe astratta <code>Item</code>.
 */
class DiscreteItem extends Item {
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
     * @return booleano:
     *	<ul>
     * 		<li> vero se il valore passato in input è uguale al valore dell'item; </li>
     * 		<li> falso altrimenti.</li>
     * 	</ul>
     */
    boolean checkItemCondition(Object value) {
        String s = (String) value;
        String h = (String) getValue();
        return s.equals(h);
    }
}
