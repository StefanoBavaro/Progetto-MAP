package mining;

import java.util.Comparator;

/**
 * Classe che implementa l'interfaccia generica Comparator&#60;T&#62; e modella il confronto tra due emerging pattern rispetto al grow rate.
 */
public class ComparatorGrowRate implements Comparator<EmergingPattern> {
    /**
     * Implementazione del metodo <code>compare()</code> dell'interfaccia <code>Comparator&#60;T&#62;</code>.
     * Restituisce un intero che indica l'esito del confronto tra due <code>EmergingPattern</code>, in base al loro growrate.
     * @param e1 primo pattern emergente da confrontare.
     * @param e2 secondo pattern emergente da confrontare.
     * @return intero:
     *	<ul>
     * 		<li> 0 se i grow rate dei due parametri sono uguali; </li>
     * 		<li> 1 se il grow rate di <code>e1</code> è minore del grow rate di <code>e2</code>; </li>
     * 	    <li> -1 altrimenti.</li>
     * 	</ul>
     */
    public int compare(EmergingPattern e1, EmergingPattern e2) {
        if (e1.getGrowRate() == e2.getGrowRate()) return 0;
        else if (e1.getGrowRate() > e2.getGrowRate()) return 1;
        else return -1;
    }

}
