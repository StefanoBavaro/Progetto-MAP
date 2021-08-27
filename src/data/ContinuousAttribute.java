package data;

public class ContinuousAttribute extends Attribute {

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


}
