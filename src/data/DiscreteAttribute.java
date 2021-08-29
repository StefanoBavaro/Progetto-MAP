package data;

import java.io.Serializable;

public class DiscreteAttribute extends Attribute implements Serializable {

    private String[] values;

    public DiscreteAttribute(final String name, final int index, final String[] values) {
        super(name, index);
        this.values = new String[values.length];
        this.values = values;
    }

    public int getNumberOfDistinctValues() {
        return values.length;
    }

    public String getValue(final int index) {
        return values[index];
    }

}
