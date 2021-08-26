package com.company;

class FrequentPattern {

	/**
	 * @uml.property  name="fp"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private Item[] fp;
	/**
	 * @uml.property  name="support"
	 */
	private float support;
	
	
	FrequentPattern() {
		fp = new Item[0];
	}
	
	// costrutore per copia
	FrequentPattern(FrequentPattern FP) {
		int length = FP.getPatternLength();
		fp = new Item[length];
		for (int i = 0; i < length; i++) {
			fp[i] = FP.getItem(i);
		}
		support = FP.getSupport();
	}
	
	//aggiunge un nuovo item al pattern
	void addItem(Item item) {
		int length = fp.length;
		Item[] temp  = new Item[length + 1];
		System.arraycopy(fp, 0, temp, 0, length);
		temp[length] = item;
		fp = temp;
	}
	
	public Item getItem(int index) {
		return fp[index];
	}
	
	public float getSupport() {
		return support;
	}
	
	public int getPatternLength() {
		return fp.length;
	}
	
	public String toString() {
		String value = "";
		value += "(" + fp[0] + ")";
		for (int i = 0; i < fp.length - 1; i++) {
			value += " AND " + "(" + fp[i + 1] + ")";
		}
		value += " [" + support + "]";
		return value;
	}

	// Aggiorna il supporto
		 float computeSupport(Data data) {
			int suppCount = 0;
			// indice esempio
			for (int i = 0; i < data.getNumberOfExamples(); i++) {
				//indice item
				boolean isSupporting = true;
				for (int j = 0; j < this.getPatternLength(); j++) {
					//DiscreteItem
					DiscreteItem item = (DiscreteItem) this.getItem(j);
					DiscreteAttribute attribute = (DiscreteAttribute) item.getAttribute();
					//Extract the example value
					Object valueInExample = data.getAttributeValue(i, attribute.getIndex());
					if (!item.checkItemCondition(valueInExample)) {
						isSupporting = false;
						break; //the ith example does not satisfy fp
					}
					
				}
				if (isSupporting) {
					suppCount++;
				}
			}
			return ((float) suppCount) / (data.getNumberOfExamples());
		}
	
	public void setSupport(float support) {
		this.support = support;
	}
}
