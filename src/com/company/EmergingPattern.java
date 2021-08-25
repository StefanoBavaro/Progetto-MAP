package com.company;

public class EmergingPattern extends FrequentPattern {
	private final float growrate;

	public EmergingPattern(FrequentPattern fp, float growrate) {
		super(fp);
		this.growrate = growrate;
	}
	
	public float getGrowRate() {
		return growrate;
	}
	
	public String toString() {
		String value = "";
		for (int i = 0; i < getPatternLength() - 1; i++) {
			value += getItem(i) + " AND ";
			if (getPatternLength() > 0) {
				value += getItem(getPatternLength() - 1);
				value += "[" + getSupport() + "]";
				value += "[" + getGrowRate() + "]";
			}
		}
		return value;
	}
}
