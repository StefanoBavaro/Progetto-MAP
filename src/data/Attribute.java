package data;

import java.io.Serializable;

public abstract class Attribute implements Serializable {

    private String name;
    private int index;

    public Attribute(final String name, final int index) {
        this.name = new String(name);
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public String toString() {
        return name;
    }
}
