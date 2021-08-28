package mining;

import data.Attribute;

abstract class Item {
    private Attribute attribute;
    private Object value;

    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    Attribute getAttribute() {
        return attribute;
    }

    Object getValue() {
        return value;
    }

    abstract boolean checkItemCondition(Object value);

    public String toString() {
        return attribute.toString() + "=" + value.toString();
    }
}
