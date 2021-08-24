package com.company;

 class ContinuousAttribute extends Attribute {

    private float max;
    private float min;

    ContinuousAttribute(final String name, final int index, final float min, final float max) {
        super(name, index);
        this.min = min;
        this.max = max;
    }

     float getMin() {
        return min;
    }

     float getMax() {
        return max;
    }


}
