package com.company;

public class DiscreteItem extends Item {
	public DiscreteItem(DiscreteAttribute attribute, String value) {
		super(attribute, value);
	}
	public boolean checkItemCondition(Object value) {
		String s = (String) value;
		String h = (String) getValue();
		return s.equals(h);
	}
}
