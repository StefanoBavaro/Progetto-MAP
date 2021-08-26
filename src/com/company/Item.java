package com.company;

public abstract class Item {
	private Attribute attribute;
	private Object value;
	
	public Item(Attribute attribute, Object value){
		this.attribute = attribute;
		this.value = value;
	}
	
	public Attribute getAttribute(){
		return attribute;
	}
	
	public Object getValue () {
		return value;
	}
	
	public abstract boolean checkItemCondition(Object value);
	
	public String toString() {
		return attribute.toString() + "=" + value.toString();
	}
}
