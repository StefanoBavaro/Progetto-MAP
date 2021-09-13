package mining;

import java.util.Comparator;

/**
 * Classe che implementa l'interfaccia generica Comparator&lt T &gt e modella il confronto tra due emerging pattern rispetto al grow rate.
 */
public class ComparatorGrowRate implements Comparator<EmergingPattern> {
    /**
     * Implementazione del metodo compare() dell'interfaccia Comparator&lt T &gt;
     * restituisce 0 se i due parametri sono uguali, 1 se <code>e1</code> &gt <code>e2</code>, -1 altrimenti.
     * @param e1 primo EmergingPattern da confrontare.
     * @param e2 secondo EmergingPattern da confrontare.
     * @return risultato del confronto.
     */
    public int compare(EmergingPattern e1, EmergingPattern e2) {
        if (e1.getGrowRate() == e2.getGrowRate()) return 0;
        else if (e1.getGrowRate() > e2.getGrowRate()) return 1;
        else return -1;
    }

}
