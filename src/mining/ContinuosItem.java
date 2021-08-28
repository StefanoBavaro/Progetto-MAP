package mining;

import data.ContinuousAttribute;

class ContinuosItem extends Item{
	
	ContinuosItem(ContinuousAttribute attribute, Interval value) {
		super(attribute,value);
	}
	
	@Override
	boolean checkItemCondition(Object value) {
		Interval interval = (Interval) getValue();
		return interval.checkValueInclusion((float) value);
	}
	
	public String toString() {
		Interval interval =(Interval) getValue();
		return getAttribute().toString() + "in " + interval.toString();
	}
}
