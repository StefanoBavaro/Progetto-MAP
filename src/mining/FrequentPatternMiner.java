package mining;

import data.ContinuousAttribute;
import data.EmptySetException;
import utility.EmptyQueueException;
import utility.Queue;
import data.Data;
import data.DiscreteAttribute;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.*;
import java.util.Collections;

public class FrequentPatternMiner implements Iterable<FrequentPattern>, Serializable{
    private List<FrequentPattern> outputFP = new LinkedList<FrequentPattern>(); //lista di frequent Pattern


    public FrequentPatternMiner(Data data, float minSup) throws EmptySetException {
        Queue<FrequentPattern> fpQueue = new Queue<FrequentPattern>();

        if (data.getNumberOfAttributes() == 0) {
            throw new EmptySetException("L'insieme di training è vuoto");  //non sono sicuro che il messaggio sia corretto
        }

        for (int i = 0; i < data.getNumberOfAttributes(); i++) {
            if (data.getAttribute(i) instanceof DiscreteAttribute) { // RTTI
                DiscreteAttribute currentAttribute = (DiscreteAttribute) data.getAttribute(i);
                for (int j = 0; j < currentAttribute.getNumberOfDistinctValues(); j++) {
                    DiscreteItem item = new DiscreteItem(currentAttribute, currentAttribute.getValue(j));
                    FrequentPattern fp = new FrequentPattern();
                    fp.addItem(item);
                    fp.setSupport(fp.computeSupport(data));
                    if (fp.getSupport() >= minSup) { // 1-FP CANDIDATE
                        fpQueue.enqueue(fp);
                        //System.out.println(fp);
                        outputFP.add(fp);
                    }
                }
            } else { // is a ContinuosAttribute
                ContinuousAttribute currentAttribute = (ContinuousAttribute) data.getAttribute(i);
                Iterator<Float> p = currentAttribute.iterator();
                Float min = p.next();
                while (p.hasNext()) {
                    Float max = p.next();
                    ContinuosItem item = new ContinuosItem(currentAttribute, new Interval(min, max));
                    min = max;
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
        }
        outputFP = expandFrequentPatterns(data, minSup, fpQueue, outputFP);
        this.sort();
    }

    private List expandFrequentPatterns(Data data, float minSup, Queue fpQueue, List outputFP) {
        //Con queue<FrequentPattern> non funziona
        // nel primo while stava !fpQueue.isEmpty()
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
                        if (data.getAttribute(i) instanceof DiscreteAttribute) {
                            DiscreteAttribute currentAttribute = (DiscreteAttribute) data.getAttribute(i);
                            for (int j = 0; j < currentAttribute.getNumberOfDistinctValues();
                               j++) {
                                DiscreteItem item = new DiscreteItem(currentAttribute, currentAttribute.getValue(j));
                                FrequentPattern newFP = refineFrequentPattern(fp, item); //generate refinement
                                newFP.setSupport(newFP.computeSupport(data));
                                if (newFP.getSupport() >= minSup) {
                                    fpQueue.enqueue(newFP);
                                    outputFP.add(newFP);
                                    }
                                }
                            } else { // is a ContinuosAttribute
                                ContinuousAttribute currentAttribute = (ContinuousAttribute) data.getAttribute(i);
                                Iterator<Float> p =  currentAttribute.iterator();
                                Float min = p.next();
                                while (p.hasNext()){
                                    Float max = p.next();
                                    ContinuosItem item = new ContinuosItem(currentAttribute, new Interval(min, max));
                                    min = max;
                                    FrequentPattern newFP = refineFrequentPattern(fp, item); //generate refinement
                                    newFP.setSupport(newFP.computeSupport(data));
                                    if (newFP.getSupport() >= minSup) {
                                        fpQueue.enqueue(newFP);
                                        outputFP.add(newFP);
                                    }
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
    
    public void salva(String nomeFile) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream(nomeFile);
        ObjectOutputStream ou = new ObjectOutputStream(file);
        ou.writeObject(this);
        file.close();
        ou.close();
    }
    
    public static FrequentPatternMiner carica(String nomeFile) throws FileNotFoundException,IOException,ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile));
        FrequentPatternMiner obj = (FrequentPatternMiner) in.readObject();
        System.out.println(obj);
        in.close();
        return obj;
    }
    
    // metodo creato da Lorenzo usato per il costruttore di FrequentPatternMiner
    public List getOutputFP() {
        return outputFP;
    }

}
	


