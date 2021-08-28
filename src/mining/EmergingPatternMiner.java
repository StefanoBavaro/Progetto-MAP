package mining;

import data.EmptySetException;
import data.Data;

import java.util.*;

public class EmergingPatternMiner implements Iterable<EmergingPattern> {
    private List<EmergingPattern> epList = new LinkedList<EmergingPattern>();

    public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) throws EmptySetException {
        if (dataBackground.getNumberOfExamples() == 0) {
            throw new EmptySetException("L'insieme di training è vuoto"); //non sono sicuro che il messaggio sia corretto
        }

        List<FrequentPattern> list = fpList.getOutputFP();
        Iterator e = list.iterator();
        while (e.hasNext()) {
            FrequentPattern toExamine = (FrequentPattern) e.next();
            try {
                epList.add(computeEmergingPattern(dataBackground, toExamine, minG));
            } catch (EmergingPatternException ex) {
                System.err.println(ex.getMessage());
            }
        }
        this.sort();
    }

    public float computeGrowRate(Data dataBackground, FrequentPattern fp) {
        return fp.getSupport() / fp.computeSupport(dataBackground);
    }

    public EmergingPattern computeEmergingPattern(Data dataBackgroun, FrequentPattern fp, float minGR) throws EmergingPatternException {
        float growRate = computeGrowRate(dataBackgroun, fp);
        if (growRate > minGR) {
            return new EmergingPattern(fp, growRate);
        } else {
            throw new EmergingPatternException("il pattern non soddisfa il minimo growrate");
        }
    }

    public Iterator<EmergingPattern> iterator() {
        return epList.iterator();
    }

    private void sort() {
        epList.sort(new ComparatorGrowRate());
    }

    public String toString() {
        String out = "";
        int i = 1;
        for (EmergingPattern e : epList) {
            out += i + ": " + e + "\n";
            i++;
        }
        return out;
    }
}
