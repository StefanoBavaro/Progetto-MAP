package com.company;

public class EmergingPatternMiner {
	private LinkList[] epList;
	
	public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) {
		/*
		Comportamento: Si scandiscono tutti i frequent pattern in fpList , per
	ognuno di essi si calcola il grow rate usando dataBackground e se tale
	valore è maggiore uguale di minG allora il pattern è aggiunto ad epList
	(fare uso del metodo computeEmergingPattern).
		 */
		LinkList list = fpList.getOutputFP();
		int i = 0;
		epList = new LinkList[i];
		Puntatore p = list.firstList();
		while (!list.endList(p)) {
			FrequentPattern toExamine = (FrequentPattern) list.readList(p);
			float growRate = toExamine.computeSupport(dataBackground);
			if (growRate >= minG) {
				i++;
				epList = new LinkList[i];
				EmergingPattern toInsert = computeEmergingPattern(dataBackground, toExamine,growRate);
				epList[i].add(toInsert);
			}
		}
	}
	
	public float computeGrowRate(Data dataBackground, FrequentPattern fp) {
		return fp.getSupport() / fp.computeSupport(dataBackground);
	}
	
	
	public EmergingPattern computeEmergingPattern(Data dataBackgroun, FrequentPattern fp, float minGR) {
		float growRate = computeGrowRate(dataBackgroun, fp);
		if (growRate > minGR) {
			return new EmergingPattern(fp,growRate);
		}
		return null;
	}
	
	public String toString() {
		String ou = "";
		for (int i = 0; i < epList.length; i++){
			ou += epList[i];
		}
		return ou;
	}
	
	
}
