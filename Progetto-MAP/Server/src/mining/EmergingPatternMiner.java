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

/**
 * Classe che modella la scoperta di emerging pattern a partire dalla lista di FrequentPattern
 */
public class EmergingPatternMiner implements Iterable<EmergingPattern>, Serializable {
    /**
     * Lista che contiene riferimenti a oggetti istanza della classe EmergingPattern che definiscono il pattern
     */
    private List<EmergingPattern> epList = new LinkedList<EmergingPattern>();

    /**
     * Scandisce tutti i frequent pattern in <code>fpList</code>, per
     * ognuno di essi calcola il grow rate usando dataBackground e se tale
     * valore è maggiore uguale di <code>minG</code> allora il pattern è aggiunto ad <code>epList</code>.
     * La lista viene infine ordinata.
     * @param dataBackground dataset di background su cui calcolare il growrate dei pattern in fpList
     * @param fpList pattern da cui calcolare il growrate
     * @param minG minimo grow rate
     * @throws EmptySetException
     */
    public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) throws EmptySetException {
        if (dataBackground.getNumberOfExamples() == 0) {
            throw new EmptySetException(Constants.EMPTY_TRAININGSET);
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

    /**
     * Ricava da fp il suo supporto relativo al dataset target.
     * Calcola il supporto di <code>fp</code> relativo al dataset di background.
     * Calcola il grow rate come rapporto dei due supporti.
     * @param dataBackground insieme delle transazioni di background
     * @param fp pattern frequente (fp) di cui calcolare il growrat
     * @return grow rate di <code>fp</code>
     */
    private float computeGrowRate(Data dataBackground, FrequentPattern fp) {
        return fp.getSupport() / fp.computeSupport(dataBackground);
    }

    /**
     * Verifica che il gorw rate di fp sia maggiore di minGR; in caso affermativo crea un oggetto EmemrgingPattern da fp.
     * @param dataBackgroun insieme di transazioni di background
     * @param fp pattern frequente
     * @param minGR minimo grow rate
     * @return emerging pattern creato da fp se la condizione sul grow rate è soddisfatta
     * @throws EmergingPatternException se la condizione sul growrate non è soddisfatta
     */
    private EmergingPattern computeEmergingPattern(Data dataBackgroun, FrequentPattern fp, float minGR) throws EmergingPatternException {
        float growRate = computeGrowRate(dataBackgroun, fp);
        if (growRate > minGR) {
            return new EmergingPattern(fp, growRate);
        } else {
            throw new EmergingPatternException(Constants.GROWRATE_NOT_VALID);
        }
    }

    /**
     * Implementazione del metodo <code>iterator()</code> dell'interfaccia Iterator.
     * Restituisce l'iteratore del membro <code>epList</code>
     * @return riferimento all'iteratore della lista <code>epList</code>
     */
    public Iterator<EmergingPattern> iterator() {
        return epList.iterator();
    }

    /**
     * Ordina epList usando il comparatore associato alla classe EmergingPattern.
     */
    private void sort() {
        epList.sort(new ComparatorGrowRate());
    }

    /**
     * Scandisce epList al fine di concatenare in un'unica stringa le stringhe rappresentati i pattern emergenti letti.
     * @return stringa rappresentante il valore di epList
     */
    public String toString() {
        String out = "";
        int i = 1;
        for (EmergingPattern e : epList) {
            out += i + ": " + e + "\n";
            i++;
        }
        return out;
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
     * @param nomeFile nome del file in cui è memorizzato un oggetto EmergingPatternMiner
     * @return riferimento ad oggetto emergingPatternMiner salvato nel file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static EmergingPatternMiner carica(String nomeFile) throws FileNotFoundException,IOException,ClassNotFoundException{
        ObjectInputStream in =  new ObjectInputStream(new FileInputStream(nomeFile));
        EmergingPatternMiner obj = (EmergingPatternMiner) in.readObject();
        in.close();
        return obj;
    }
}
