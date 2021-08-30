package mining;

import data.Data;
import data.Attribute;

import java.io.Serializable;
import java.util.*;

public class FrequentPattern implements Iterable<Item>, Comparable<FrequentPattern>, Serializable {


    private List<Item> fp;
    private float support;

    public int compareTo(FrequentPattern FP) {
        if (this.support == FP.getSupport()) return 0;
        else if (FP.getSupport() < this.support) return 1;
        else return -1;
    }

    public FrequentPattern() {
        fp = new LinkedList<Item>();
    }

    // costrutore per copia
    public FrequentPattern(FrequentPattern FP) {
        fp = new LinkedList<Item>();
        Iterator e = FP.fp.iterator();
        while (e.hasNext()) {
            fp.add((Item) e.next());
        }
        this.support = FP.getSupport();
    }

    //aggiunge un nuovo item al pattern
    public void addItem(Item item) {
        fp.add(item);
    }

    public Item getItem(int index) {
        return fp.get(index);
    }

    public float getSupport() {
        return support;
    }

    public int getPatternLength() {
        return fp.size();
    }

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

    // Aggiorna il supporto
    public float computeSupport(Data data) {
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

    public Iterator<Item> iterator() {
        return fp.iterator();
    }

    public void setSupport(float support) {
        this.support = support;
    }
}
