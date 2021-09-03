package mining;

import java.util.Comparator;

public class ComparatorGrowRate implements Comparator<EmergingPattern> {
    public int compare(EmergingPattern e1, EmergingPattern e2) {
        if (e1.getGrowRate() == e2.getGrowRate()) return 0;
        else if (e1.getGrowRate() > e2.getGrowRate()) return 1;
        else return -1;
    }

}
