package mining;

import java.io.Serializable;

class EmergingPattern extends FrequentPattern implements Serializable {
    private final float growrate;

    EmergingPattern(FrequentPattern fp, float growrate) {
        super(fp);
        this.growrate = growrate;
    }

    float getGrowRate() {
        return growrate;
    }

    public String toString() {
        String value = super.toString();
        value += " [" + growrate + "]";
        return value;
    }
}
