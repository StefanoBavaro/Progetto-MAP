package mining;

import data.EmptySetException;
import utility.LinkList;
import utility.Puntatore;
import data.Data;

public class EmergingPatternMiner {
	private LinkList epList; // lista di EmergingPattern
	
	public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) throws EmptySetException {
		if(dataBackground.getNumberOfExamples()==0){
			throw new EmptySetException("L'insieme di training è vuoto"); //non sono sicuro che il messaggio sia corretto
		}

		LinkList list = fpList.getOutputFP();
		epList = new LinkList();
		Puntatore p = list.firstList();
		while (!list.endList(p)) {
			FrequentPattern toExamine = (FrequentPattern) list.readList(p);
			try {
				epList.add(computeEmergingPattern(dataBackground, toExamine, minG));
			}catch(EmergingPatternException e){
				System.err.println(e.getMessage());
			}
			p = list.succ(p);
		}
	}
	
	public float computeGrowRate(Data dataBackground, FrequentPattern fp) {
		return fp.getSupport() / fp.computeSupport(dataBackground);
	}
	
	public EmergingPattern computeEmergingPattern(Data dataBackgroun, FrequentPattern fp, float minGR) throws EmergingPatternException {
		float growRate = computeGrowRate(dataBackgroun, fp);
		if (growRate > minGR) {
			return new EmergingPattern(fp, growRate);
		}else{
			throw new EmergingPatternException("Il pattern non soddisfa il minimo grow rate.");
		}
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
