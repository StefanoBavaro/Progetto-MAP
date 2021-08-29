package data;

import java.io.Serializable;
import java.util.Iterator;

public class ContinuousAttribute extends Attribute implements Iterable<Float>, Serializable {

    private float max;
    private float min;

    public ContinuousAttribute(final String name, final int index, final float min, final float max) {
        super(name, index);
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
    
    
    @Override
    public Iterator<Float> iterator () {
        return new ContinuousAttributeIterator(min, max, 5);
    }
}
