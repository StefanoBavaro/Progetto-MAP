package com.company;

public class EmergingPatternMiner {
	private LinkList epList; // lista di EmergingPattern
	
	public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) {
		LinkList list = fpList.getOutputFP();
		epList = new LinkList();
		Puntatore p = list.firstList();
		while (!list.endList(p)) {
			FrequentPattern toExamine = (FrequentPattern) list.readList(p);
			EmergingPattern toInsert = computeEmergingPattern(dataBackground, toExamine,minG);
			if (toInsert != null) {
				epList.add(toInsert);
			}
			p = list.succ(p);
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
		Puntatore p = epList.firstList();
		int i = 1;
		while (!epList.endList(p)) {
			ou += i + ": " + epList.readList(p) + "\n";
			p = epList.succ(p);
			i++;
		}
		return ou;
	}
}
