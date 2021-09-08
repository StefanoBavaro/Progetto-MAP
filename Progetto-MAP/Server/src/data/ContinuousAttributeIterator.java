package data;

import java.util.Iterator;

/**
 * Classe che modella l’iteratore che itera sugli elementi della sequenza composta da numValues valori
 * reali equidistanti tra di loro (cut points) compresi tra min e max ottenuti per mezzo di discretizzazione.
 * La classe implementa i metodi della interfaccia generica Iterator<T> tipizzata con Float.
 */
class ContinuousAttributeIterator implements Iterator<Float> {

	/**
	 * Estremo sinistro dell'intervallo
	 */
	private float min;

	/**
	 * Estremo destro dell'intervallo
	 */
	private float max;

	/**
	 * Posizione dell’iteratore nella collezione di cut point generati per [min, max[ tramite discretizzazione
	 */
	private int j = 0;

	/**
	 * Numero di intervalli di discretizzazione
	 */
	private int numValues;

	/**
	 * Costruttore: avvalora i membri attributo della classe con i parametri passati
	 * @param min estremo sinistro dell'intervallo
	 * @param max estremo destro dell'intervallo
	 * @param numValues numero di intervalli di discretizzazione
	 */
	ContinuousAttributeIterator(float min, float max, int numValues){
		this.min = min;
		this.max = max;
		this.numValues = numValues;
	}

	/**
	 * Restituisce true se la posizione dell'iteratore è inferiore al numero di intervalli di discretizzazione, falso altrimenti
	 * @return booleano che indica se j<=numValues
	 */
	@Override
	public boolean hasNext() {
		return (j <= numValues);
	}

	/**
	 * Incrementa j e restituisce il cut point in posizione min + ((max-min) / numValues) * (j - 1).
	 * @return
	 */
	public Float next() {
		j++;
		return min + ((max-min) / numValues) * (j - 1);
	}

	/**
	 * Override del metodo remove di Iterator
	 */
	public void remove() {

	}

}
