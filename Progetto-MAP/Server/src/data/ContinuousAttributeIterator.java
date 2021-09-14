package data;

import java.util.Iterator;

/**
 * Classe che modella l’iteratore che itera sugli elementi della sequenza composta da <code>numValues</code> valori
 * reali equidistanti tra di loro (cut points) compresi tra <code>min</code> e <code>max</code> ottenuti per mezzo di discretizzazione.
 * La classe implementa i metodi della interfaccia generica <code>Iterator&#60;T&#62;</code> tipizzata con <code>Float</code>.
 */
class ContinuousAttributeIterator implements Iterator<Float> {

	/**
	 * Estremo sinistro dell'intervallo.
	 */
	private float min;

	/**
	 * Estremo destro dell'intervallo.
	 */
	private float max;

	/**
	 * Posizione dell’iteratore nella collezione di cut point generati per [<code>min</code>, <code>max</code>[ tramite discretizzazione.
	 */
	private int j = 0;

	/**
	 * Numero di intervalli di discretizzazione.
	 */
	private int numValues;

	/**
	 * Costruttore: avvalora i membri attributo della classe con i parametri passati.
	 * @param min estremo sinistro dell'intervallo.
	 * @param max estremo destro dell'intervallo.
	 * @param numValues numero di intervalli di discretizzazione.
	 */
	ContinuousAttributeIterator(float min, float max, int numValues){
		this.min = min;
		this.max = max;
		this.numValues = numValues;
	}

	/**
	 * Restituisce <code>true</code> se la posizione dell'iteratore è inferiore al numero di intervalli di discretizzazione, <code>false</code> altrimenti.
	 * @return booleano che indica se <code>j&#60;=numValues</code>.
	 */
	@Override
	public boolean hasNext() {
		return (j <= numValues);
	}

	/**
	 * Incrementa il membro <code>j</code>e restituisce il cut point in posizione min+((max-min)/numValues)*(j-1).
	 * @return cut point successivo.
	 */
	public Float next() {
		j++;
		return min + ((max-min) / numValues) * (j - 1);
	}

	/**
	 * Override del metodo <code>remove</code> dell'interfaccia <code>Iterable</code>.
	 */
	public void remove() {
	}

}
