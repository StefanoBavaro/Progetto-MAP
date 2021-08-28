package mining;

import data.DiscreteAttribute;
import java.io.Serializable;

class DiscreteItem extends Item implements Serializable{
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    boolean checkItemCondition(Object value) {
        String s = (String) value;
        String h = (String) getValue();
        return s.equals(h);
    }
}
