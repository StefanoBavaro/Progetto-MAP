package mining;

import data.EmptySetException;
import utility.EmptyQueueException;
import utility.LinkList;
import utility.Puntatore;
import utility.Queue;
import data.Attribute;
import data.Data;
import data.DiscreteAttribute;

public class FrequentPatternMiner {
	private LinkList outputFP = new LinkList(); //lista di frequent Pattern
	
	
	public FrequentPatternMiner(Data data, float minSup) throws EmptySetException {
		Queue fpQueue = new Queue();

		if (data.getNumberOfAttributes() == 0) {
			throw new EmptySetException("L'insieme di training � vuoto");  //non sono sicuro che il messaggio sia corretto
		}
	
		for (int i = 0; i < data.getNumberOfAttributes(); i++) {
			Attribute currentAttribute = data.getAttribute(i);
			for (int j = 0; j < ((DiscreteAttribute) currentAttribute).getNumberOfDistinctValues(); j++) {
				DiscreteItem item = new DiscreteItem(
						(DiscreteAttribute) currentAttribute,
						((DiscreteAttribute) currentAttribute).getValue(j));
				FrequentPattern fp = new FrequentPattern();
				fp.addItem(item);
				fp.setSupport(fp.computeSupport(data));
				if (fp.getSupport() >= minSup) { // 1-FP CANDIDATE
					fpQueue.enqueue(fp);
					//System.out.println(fp);
					outputFP.add(fp);
				}
				
			}
			
		}
		
		outputFP = expandFrequentPatterns(data, minSup, fpQueue, outputFP);
	}
	
	 private LinkList expandFrequentPatterns(Data data, float minSup, Queue fpQueue, LinkList outputFP) {
		 try {
			 while (!fpQueue.isEmpty()) {
				 FrequentPattern fp = (FrequentPattern) fpQueue.first(); //fp to be refined
				 fpQueue.dequeue();
				 for (int i = 0; i < data.getNumberOfAttributes(); i++) {
					 boolean found = false;
					 for (int j = 0; j < fp.getPatternLength(); j++) //the new item should involve an attribute different form attributes already involved into the items of fp
						 if (fp.getItem(j).getAttribute().equals(data.getAttribute(i))) {
							 found = true;
							 break;
						 }
					 if (!found) { //data.getAttribute(i) is not involve into an item of fp
						 for (int j = 0; j < ((DiscreteAttribute) data.getAttribute(i)).getNumberOfDistinctValues(); j++) {
							 DiscreteItem item = new DiscreteItem(
									 (DiscreteAttribute) data.getAttribute(i),
									 ((DiscreteAttribute) (data.getAttribute(i))).getValue(j)
							 );
							 FrequentPattern newFP = refineFrequentPattern(fp, item); //generate refinement
							 newFP.setSupport(newFP.computeSupport(data));
							 if (newFP.getSupport() >= minSup) {
								 fpQueue.enqueue(newFP);
								 //System.out.println(newFP);
								 outputFP.add(newFP);
							 }
						 }
					 }
				 }
			 }
		 } catch (EmptyQueueException e) {
			 System.err.println(e.getMessage());
		 }
		 return outputFP;
	}
	
	public FrequentPattern refineFrequentPattern(FrequentPattern FP, Item item) {
		FrequentPattern out = new FrequentPattern(FP);
		out.addItem(item);
		return out;
	
	}
	
	
	public String toString() {
		String ou = "";
		Puntatore p = outputFP.firstList();
		int i = 1;
		while (!outputFP.endList(p)) {
			ou += i + ": ";
			ou += outputFP.readList(p) + "\n";
			p = outputFP.succ(p);
			i++;
		}
		return ou;
	}
	// metodo creato da Lorenzo usato per il costruttore di FrequentPatternMiner
	public LinkList getOutputFP() {
		return outputFP;
	}
	
}
	

