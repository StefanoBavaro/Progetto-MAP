package mining;

import data.ContinuousAttribute;

import java.io.Serializable;

/**
 * Classe che modella la coppia &ltAttributo continuo - Intervallo di valori&gt; estende e realizza la classe astratta <code>Item</code>.
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
	 * Override del metodo toString() di Object.
	 * Avvalora la stringa che rappresenta lo stato dell’oggetto e ne restituisce il riferimento.
	 * @return stringa che rappresenta lo stato dell’oggetto nella forma &ltnome attributo&gt in [< inf>,<sup>[.
	 */
	public String toString() {
		Interval interval =(Interval) getValue();
		return getAttribute().toString() + " in " + interval.toString();
	}
}
