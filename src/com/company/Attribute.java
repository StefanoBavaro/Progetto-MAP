package com.company;

public abstract class Attribute {

    private String name;
    private int index;

    public Attribute(final String name, final int index) {
        this.name = name;
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
