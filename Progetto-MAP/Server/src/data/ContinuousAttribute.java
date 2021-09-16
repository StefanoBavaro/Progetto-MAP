package data;

import java.util.Iterator;
import utility.Constants;


/**
 * Classe che modella un attributo continuo (numerico) rappresentandone il dominio [min,max].
 * Estende la classe Attribute.
 */
public class ContinuousAttribute extends Attribute implements Iterable<Float> {

    /**
     * Estremo destro del dominio.
     */
    private float max;

    /**
     * Estremo sinistro del dominio.
     */
    private float min;

    /**
     * Costruttore: invoca il costruttore della superclasse e ne avvalora i membri.
     * @param name nome dell’attributo.
     * @param index identificativo numerico dell'attributo.
     * @param min estremo sinistro dell’intervallo di dominio.
     * @param max estremo destro dell’intervallo di dominio.
     */
    ContinuousAttribute(String name, int index, float min, float max) {
        super(name, index);
        this.min = min;
        this.max = max;
    }

    /**
     * Restituisce il valore del membro <code>min</code>.
     * @return estremo inferiore dell'intervallo.
     */
    public float getMin() {
        return min;
    }

    /**
     * Restituisce il valore del membro <code>max</code>.
     * @return estremo superiore dell'intervallo.
     */
    public float getMax() {
        return max;
    }

    /**
     * Istanzia e restituisce un riferimento ad oggetto di classe <code>ContinuousAttributeIterator</code>
     * con numero di intervalli di discretizzazione pari a 5.
     * @return riferimento a una istanza di <code>ContinuousAttributeIterator</code>.
     */
    @Override
    public Iterator<Float> iterator () {
        return new ContinuousAttributeIterator(min, max, Constants.ITERATOR_VALUES);
    }
}
