package com.company;

 class DiscreteAttribute extends Attribute {

    private String values[];

    DiscreteAttribute(final String name, final int index, final String values[]) {
        super(name, index);
    }

    int getNumberOfDistinctValues() {
        return values.length;
    }

    String getValue(final int index) {
        return values[index];
    }


}
