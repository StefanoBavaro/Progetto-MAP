package data;

import java.io.Serializable;
import java.util.Iterator;
import utility.Constants;

/**
 * Classe che modella un attributo continuo (numerico) rappresentandone il dominio [min,max]; estende la classe Attribute.
 */
public class ContinuousAttribute extends Attribute implements Iterable<Float>, Serializable {

    /**
     * Estremo destro del dominio
     */
    private float max;

    /**
     * Estremo sinistro del dominio
     */
    private float min;

    /**
     * Costruttore: invoca il costruttore della classe madre e avvalora i membri
     * @param name nome dell’attributo
     * @param index identificativo numerico dell'attributo
     * @param min estremo sinistro dell’intervallo dominio
     * @param max estremo destro dell’intervallo dominio
     */
    public ContinuousAttribute(final String name, final int index, final float min, final float max) {
        super(name, index);
        this.min = min;
        this.max = max;
    }

    /**
     * Restituisce il valore del membro min
     * @return estremo inferiore dell'intervallo
     */
    public float getMin() {
        return min;
    }

    /**
     * Restituisce il valore del membro max
     * @return estremo superiore dell'intervallo
     */
    public float getMax() {
        return max;
    }

    /**
     * Istanzia e restituisce un riferimento ad oggetto di classe ContinuousAttributeIterator
     * con numero di intervalli di discretizzazione pari a 5.
     * @return riferimento a una istanza di ContinuousAttributeIterator
     */
    @Override
    public Iterator<Float> iterator () {
        return new ContinuousAttributeIterator(min, max, Constants.ITERATOR_VALUES);
    }
}
