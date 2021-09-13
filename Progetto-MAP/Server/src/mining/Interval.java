package mining;

import java.io.Serializable;

/**
 * Classe che modella un intervallo reale [inf ,sup[.
 */
class Interval implements Serializable {
	/**
	 * Estremo inferiore dell'intervallo.
	 */
	private float inf;
	/**
	 * Estremo superiore dell'intervallo.
	 */
	private float sup;

	/**
	 * Costruttore: avvalora i due membri <code>inf</code> e <code>sup</code> con i parametri in input.
	 * @param inf estremo inferiore dell'intervallo.
	 * @param sup estremo superiore dell'intervallo.
	 */
	Interval(float inf,float sup) {
		this.inf = inf;
		this.sup = sup;
	}

	/**
	 * Avvalora <code>inf</code> con il parametro passato.
	 * @param inf estremo inferiore.
	 */
	void setInf(float inf) {
		this.inf = inf;
	}

	/**
	 * Avvalora <code>sup</code> con il parametro passato.
	 * @param sup estremo superiore.
	 */
	void setSup(float sup) {
		this.sup = sup;
	}

	/**
	 * Restituisce il membro <code>inf</code>.
	 * @return estremo inferiore.
	 */
	float getInf() {
		return inf;
	}

	/**
	 * Restituisce il membro <code>sup</code>.
	 * @return estremo superiore.
	 */
	float getSup() {
		return sup;
	}

	/**
	 * Restituisce <code>true</code> se il parametro è maggiore uguale di <code>inf</code> e minore di <code>sup</code>, <code>false</code> altrimenti.
	 * @param value valore assunto da una attributo continuo per il quale verificare l'appartenenza all’intervallo.
	 * @return esito della verifica.
	 */
	boolean checkValueInclusion(float value) {
		return value >= inf && value < sup;
	}

	/**
	 * Override del metodo toString() di Object.
	 * Concatena in una stringa gli estremi dell’intervallo nel formato "[inf;sup[".
	 * @return riferimento ad una stringa in cui si rappresenta l’intervallo [inf,sup[.
	 */
	@Override
	public String toString() {
		return "["+  inf + ";" + sup + "[";
	}
}
