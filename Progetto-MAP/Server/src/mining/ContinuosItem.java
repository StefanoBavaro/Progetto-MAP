package mining;

import data.ContinuousAttribute;

import java.io.Serializable;

/**
 * Classe che modella la coppia &#60;Attributo continuo - Intervallo di valori&#62;; estende e realizza la classe astratta <code>Item</code>.
 */
class ContinuosItem extends Item implements Serializable {

	/**
	 * Costruttore: chiama il costruttore della superclasse passandogli come argomenti <code>attribute</code> e <code>value</code>.
	 * @param attribute attributo continuo.
	 * @param value intervallo.
	 */
	ContinuosItem(ContinuousAttribute attribute, Interval value) {
		super(attribute,value);
	}

	/**
	 * Verifica che il parametro <code>value</code> rappresenti un numero reale incluso tra gli estremi dell’intervallo associato all'item.
	 * @param value valore di cui si vuole verficare l'appartenenza all'intervallo.
	 * @return booleano:
	 *	<ul>
	 * 		<li> vero se il valore è contenuto nell'intervallo; </li>
	 * 		<li> falso altrimenti.</li>
	 * 	</ul>
	 */
	@Override
	boolean checkItemCondition(Object value) {
		Interval interval = (Interval) getValue();
		float v = (float) value;
		return interval.checkValueInclusion(v);
	}

	/**
	 * Override del metodo <code>toString()</code> di <code>Object</code>.
	 * Avvalora la stringa che rappresenta lo stato dell’oggetto e ne restituisce il riferimento.
	 * @return stringa che rappresenta lo stato dell’oggetto nella forma &#60;nome attributo&#62; in [inf,sup[.
	 */
	public String toString() {
		Interval interval =(Interval) getValue();
		return getAttribute().toString() + " in " + interval.toString();
	}
}
