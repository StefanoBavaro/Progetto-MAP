package mining;

import data.EmptySetException;
import utility.EmptyQueueException;
import utility.Queue;
import data.Attribute;
import data.Data;
import data.DiscreteAttribute;

import java.util.*;
import java.util.Collections;

public class FrequentPatternMiner implements Iterable<FrequentPattern> {
    private List<FrequentPattern> outputFP = new LinkedList<FrequentPattern>(); //lista di frequent Pattern


    public FrequentPatternMiner(Data data, float minSup) throws EmptySetException {
        Queue<FrequentPattern> fpQueue = new Queue<>();

        if (data.getNumberOfAttributes() == 0) {
            throw new EmptySetException("L'insieme di training è vuoto");  //non sono sicuro che il messaggio sia corretto
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
        this.sort();
    }

    private List expandFrequentPatterns(Data data, float minSup, Queue fpQueue, List outputFP) {
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
        String out = "";
        int i = 1;
        for (FrequentPattern fp : outputFP) {
            out += i + ": " + fp + "\n";
            i++;

        }
        return out;
    }

    private void sort() {
        Collections.sort(outputFP);
    }

    public Iterator<FrequentPattern> iterator() {
        return outputFP.iterator();
    }

    // metodo creato da Lorenzo usato per il costruttore di FrequentPatternMiner
    public List getOutputFP() {
        return outputFP;
    }

}
	


