package com.company;

class Attribute {

    private String name;
    private int index;

    Attribute(final String name, final int index) {
        this.name = name;
        this.index = index;
    }

    String getName() {
        return name;
    }

    int getIndex() {
        return index;
    }

    public String toString() {
        return name;
    }
}
