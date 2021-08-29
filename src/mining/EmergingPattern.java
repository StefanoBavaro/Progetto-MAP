package mining;

import java.io.Serializable;

public class EmergingPattern extends FrequentPattern implements Serializable {
    private final float growrate;

    public EmergingPattern(FrequentPattern fp, float growrate) {
        super(fp);
        this.growrate = growrate;
    }

    public float getGrowRate() {
        return growrate;
    }

    public String toString() {
        String value = super.toString();
        value += " [" + growrate + "]";
        return value;
    }
}
