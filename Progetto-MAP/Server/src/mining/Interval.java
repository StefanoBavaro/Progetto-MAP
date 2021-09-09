package mining;

import java.io.Serializable;

/**
 * Classe che modella un intervallo reale [inf ,sup[
 */
class Interval implements Serializable {
	/**
	 * estremo inferiore dell'intervallo
	 */
	private float inf;
	/**
	 * estremo superiore dell'intervallo
	 */
	private float sup;

	/**
	 * Costruttore: avvalora i due attributi inf e sup con i parametri forniti
	 * @param inf estremo inferiore dell'intervallo
	 * @param sup estremo superiore dell'intervallo
	 */
	Interval(float inf,float sup) {
		this.inf = inf;
		this.sup = sup;
	}

	/**
	 * Avvalora inf con il parametro passato
	 * @param inf estremo inferiore
	 */
	void setInf(float inf) {
		this.inf = inf;
	}

	/**
	 * Avvalora sup con il parametro passato
	 * @param sup estremo superiore
	 */
	void setSup(float sup) {
		this.sup = sup;
	}

	/**
	 * Restituisce il membro <code>inf</code>
	 * @return estremo inferiore
	 */
	float getInf() {
		return inf;
	}

	/**
	 * Restituisce il membro <code>sup</code>
	 * @return estremo superiore
	 */
	float getSup() {
		return sup;
	}

	/**
	 * Restituisce <code>true</code> se il parametro è maggiore uguale di inf e minore di sup, <code>false</code> altrimenti
	 * @param value valore assunto da una attributo continuo per il quale verificare l'appartenenza all’intervallo
	 * @return esito della verifica
	 */
	boolean checkValueInclusion(float value) {
		return value >= inf && value < sup;
	}

	/**
	 * Override del metodo toString() di Object.
	 * Rappresenta in una stringa gli estremi dell’intervallo e restituisce tale stringa
	 * @return riferimento ad una stringa in cui si rappresenta l’intervallo [inf,sup[
	 */
	@Override
	public String toString() {
		return "["+  inf + ";" + sup + "[";
	}
}
