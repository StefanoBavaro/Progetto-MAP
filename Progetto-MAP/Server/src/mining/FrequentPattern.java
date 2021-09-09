package mining;

import data.Data;
import data.Attribute;

import java.io.Serializable;
import java.util.*;

/**
 * Classe che modella un pattern frequente
 */
class FrequentPattern implements Iterable<Item>, Comparable<FrequentPattern>, Serializable {

    /**
     * Lista che contiene riferimenti a oggetti istanza della classe <code>Item</code> che definiscono il pattern
     */
    private List<Item> fp;

    /**
     * Valore di supporto calcolato per il pattern <code>fp</code>
     */
    private float support;

    /**
     *
     * @param FP
     * @return
     */
    public int compareTo(FrequentPattern FP) {
        if (this.support == FP.getSupport()) return 0;
        else if (FP.getSupport() < this.support) return 1;
        else return -1;
    }

    /**
     * Costruttore: istanzia un oggetto <code>LinkedList&ltItem&gt</code> salvandone il riferimento nel membro <code>fp</code>
     */
    FrequentPattern() {
        fp = new LinkedList<Item>();
    }

    /**
     * Costruttore che alloca fp e support come copia del frequent pattern passato come parametro
     * @param FP frequent pattern di cui copiare le informazioni
     */
    FrequentPattern(FrequentPattern FP) {
        fp = new LinkedList<Item>();
        Iterator e = FP.fp.iterator();
        while (e.hasNext()) {
            fp.add((Item) e.next());
        }
        this.support = FP.getSupport();
    }

    /**
     * Utilizza il metodo <code>add()</code> di <code>LinkedList</code> per aggiungere al pattern l'<code>Item</code> passato come parametro
     * @param item oggetto Item da aggiungere al pattern
     */
    void addItem(Item item) {
        fp.add(item);
    }

    /**
     * Restituisce l'item in posizione <code>index</code> di <code>fp</code>
     * @param index posizione in <code>fp</code>
     * @return <code>Item</code> che occupa la posizione indicata in <code>fp</code>
     */
    Item getItem(int index) {
        return fp.get(index);
    }

    /**
     * Restituisce il membro <code>support</code>
     * @return valore di supporto del pattern
     */
    float getSupport() {
        return support;
    }

    /**
     * Restituisce la dimensione (lunghezza) di <code>fp</code>
     * @return lunghezza del pattern
     */
    int getPatternLength() {
        return fp.size();
    }

    /**
     * Override del <code>toString()</code> di Object.
     * Scandisce <code>fp</code> al fine di concatenare in una stringa la rappresentazione degli item; infine concatena il supporto
     * @return stringa rappresentante l'itemset e il suo supporto
     */
    public String toString() {
        String value = "";
        Iterator<Item> it = fp.iterator();
        value += "(" + it.next() + ")";
        if (fp.size() > 1) {
            while (it.hasNext()) {
                value += " AND " + "(" + it.next() + ")";
            }
        }
        value += "[" + support + "]";
        return value;
    }

    /**
     * Calcola il supporto del pattern rappresentato dall'oggetto this rispetto al dataset <code>data</code> passato come argomento
     * @param data valore di supporto del pattern nel dataset data
     * @return supporto del pattern rispetto ad un dataset
     */
    float computeSupport(Data data) {
        int suppCount = 0;
        // indice esempio
        for (int i = 0; i < data.getNumberOfExamples(); i++) {
            //indice item
            boolean isSupporting = true;
            for (Item p: this) {
                //Item
                Attribute attribute =  p.getAttribute();
                //Extract the example value
                Object valueInExample = data.getAttributeValue(i, attribute.getIndex());
                if (!p.checkItemCondition(valueInExample)) {
                    isSupporting = false;
                    break; //the ith example does not satisfy fp
                }
            }
            if (isSupporting) {
                suppCount++;
            }
        }
        return ((float) suppCount) / (data.getNumberOfExamples());
    }


    /**
     * Implementazione del metodo <code>iterator()</code> dell'interfaccia <code>Iterable</code>.
     * Restituisce l'iteratore del membro <code>fp</code>
     * @return riferimento all'iteratore della lista di item
     */
    public Iterator<Item> iterator() {
        return fp.iterator();
    }

    /**
     * Assegna al membro <code>support</code> il parametro della procedura
     * @param support valore di supporto del pattern
     */
    void setSupport(float support) {
        this.support = support;
    }
}
