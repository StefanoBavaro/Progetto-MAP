package mining;

import data.DiscreteAttribute;

class DiscreteItem extends Item {
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    boolean checkItemCondition(Object value) {
        String s = (String) value;
        String h = (String) getValue();
        return s.equals(h);
    }
}
