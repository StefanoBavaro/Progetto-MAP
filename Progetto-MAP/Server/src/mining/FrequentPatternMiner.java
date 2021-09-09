package mining;

import data.ContinuousAttribute;
import data.EmptySetException;
import utility.EmptyQueueException;
import utility.Queue;
import data.Data;
import data.DiscreteAttribute;
import utility.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.*;
import java.util.Collections;

/**
 * Classe che include i metodi per la scoperta di pattern frequenti con algoritmo APRIORI
 */
public class FrequentPatternMiner implements Iterable<FrequentPattern>, Serializable{
    /**
     * Lista che contiene riferimenti a oggetti istanza della classe FrequentPattern che definiscono il pattern
     */
    private List<FrequentPattern> outputFP = new LinkedList<FrequentPattern>();

    /**
     * Costruttore: genera tutti i pattern k=1 frequenti e per ognuno di questi genera quelli con k>1 richiamando <code>expandFrequentPatterns()</code>.
     * I pattern sono memorizzati nel membro <code>OutputFP</code>
     * @param data insieme delle transazioni
     * @param minSup minimo supporto
     * @throws EmptySetException
     */
    public FrequentPatternMiner(Data data, float minSup) throws EmptySetException {
        Queue<FrequentPattern> fpQueue = new Queue<FrequentPattern>();

        if (data.getNumberOfAttributes() == 0) {
                throw new EmptySetException(Constants.EMPTY_TRAININGSET);  //non sono sicuro che il messaggio sia corretto
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
                        outputFP.add(fp);
                    }
                }
            }
        }
        outputFP = expandFrequentPatterns(data, minSup, fpQueue, outputFP);
        this.sort();
    }

    /**
     * Finché <code>fpQueue</code> contiene elementi, estrae un elemento e genera i raffinamenti per questo (aggiungendo un nuovo item non incluso).
     * Per ogni raffinamento si verifica se è frequente e, in caso affermativo, lo si aggiunge sia ad <code>fpQueue</code> sia ad <code>outputFP</code>
     * @param data insieme delle transazioni
     * @param minSup minimo supporto
     * @param fpQueue coda contente i pattern da valutare
     * @param outputFP lista dei pattern frequenti già estratti
     * @return  lista popolata con pattern frequenti a k>1
     */
    private List expandFrequentPatterns(Data data, float minSup, Queue<FrequentPattern> fpQueue, List outputFP) {
        try {
            while (!fpQueue.isEmpty()) {
                FrequentPattern fp =  fpQueue.first(); //fp to be refined
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

    /**
     * Crea un nuovo pattern a cui aggiunge tutti gli item di <code>FP</code> e il parametro <code>item</code>
     * @param FP pattern da raffinare
     * @param item item da aggiungere al frequent pattern
     * @return nuovo pattern ottenuto per effetto del raffinamento
     */
    private FrequentPattern refineFrequentPattern(FrequentPattern FP, Item item) {
        FrequentPattern out = new FrequentPattern(FP);
        out.addItem(item);
        return out;

    }

    /**
     * Scandisce <code>OutputFp</code> al fine di concatenare in un'unica stringa i pattern frequenti letti
     * @return stringa rappresentante il valore di <code>OutputFP</code>
     */
    public String toString() {
        String out = "";
        int i = 1;
        for (FrequentPattern fp : outputFP) {
            out += i + ": " + fp + "\n";
            i++;

        }
        return out;
    }

    /**
     * Ordina <code>outputFP</code> usando il comparatore associato alla classe FrequentPattern
     */
    private void sort() {
        Collections.sort(outputFP);
    }

    /**
     * Implementazione del metodo <code>iterator()</code> dell'interfaccia <code>Iterable</code>.
     * Restituisce l'iteratore del membro <code>outputFP</code>
     * @return riferimento all'iteratore della lista di frequent pattern
     */
    public Iterator<FrequentPattern> iterator() {
        return outputFP.iterator();
    }

    /**
     * Serializza l’oggetto riferito da <code>this</code> nel file il cui nome è passato come parametro
     * @param nomeFile nome del file in cui serializzare l'oggetto
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void salva(String nomeFile) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream(nomeFile);
        ObjectOutputStream ou = new ObjectOutputStream(file);
        ou.writeObject(this);
        file.close();
        ou.close();
    }

    /**
     * Legge e restituire l’oggetto che è memorizzato nel file il cui nome è passato come parametro
     * @param nomeFile nome del file in cui è memorizzato un oggetto <code>FrequentPatternMiner</code>
     * @return riferimento ad oggetto <code>FrequentPatternMiner</code> salvato nel file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static FrequentPatternMiner carica(String nomeFile) throws FileNotFoundException,IOException,ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile));
        FrequentPatternMiner obj = (FrequentPatternMiner) in.readObject();
        in.close();
        return obj;
    }

    /**
     * Restituisce il riferimento al membro <code>outputFP</code>
     * @return lista di pattern frequenti
     */
    List getOutputFP() {
        return outputFP;
    }
}
	


