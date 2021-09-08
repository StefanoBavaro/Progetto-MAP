package mining;

import data.EmptySetException;
import data.Data;
import utility.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class EmergingPatternMiner implements Iterable<EmergingPattern>, Serializable {
    private List<EmergingPattern> epList = new LinkedList<EmergingPattern>();

    public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) throws EmptySetException {
        if (dataBackground.getNumberOfExamples() == 0) {
            throw new EmptySetException(Constants.EMPTY_TRAININGSET); //non sono sicuro che il messaggio sia corretto
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

    private float computeGrowRate(Data dataBackground, FrequentPattern fp) {
        return fp.getSupport() / fp.computeSupport(dataBackground);
    }

    private EmergingPattern computeEmergingPattern(Data dataBackgroun, FrequentPattern fp, float minGR) throws EmergingPatternException {
        float growRate = computeGrowRate(dataBackgroun, fp);
        if (growRate > minGR) {
            return new EmergingPattern(fp, growRate);
        } else {
            throw new EmergingPatternException(Constants.GROWRATE_NOT_VALID);
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
    
    public void salva(String nomeFile) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream(nomeFile);
        ObjectOutputStream ou = new ObjectOutputStream(file);
        ou.writeObject(this);
        file.close();
        ou.close();
    }
    
    public static EmergingPatternMiner carica(String nomeFile) throws FileNotFoundException,IOException,ClassNotFoundException{
        ObjectInputStream in =  new ObjectInputStream(new FileInputStream(nomeFile));
        EmergingPatternMiner obj = (EmergingPatternMiner) in.readObject();
        in.close();
        return obj;
    }
}
