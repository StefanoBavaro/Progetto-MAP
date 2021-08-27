package data;

import java.util.*;

public class Data {

    private Object data[][];
    private int numberOfExamples;
    private List<Attribute> attributeSet = new LinkedList<>();

    public Data(boolean isTarget) {

        //data

        if (isTarget) {
            data = new Object[8][5];

            // numberOfExamples

            numberOfExamples = 8;
        } else {
            data = new Object[6][5];

            // numberOfExamples

            numberOfExamples = 6;
        }

        if (isTarget) {

            data[0][0] = new String("sunny");
            data[1][0] = new String("sunny");
            data[2][0] = new String("overcast");
            data[3][0] = new String("rain");
            data[4][0] = new String("rain");
            data[5][0] = new String("rain");
            data[6][0] = new String("overcast");
            data[7][0] = new String("sunny");
        } else {

            data[0][0] = new String("sunny");
            data[1][0] = new String("rain");
            data[2][0] = new String("sunny");
            data[3][0] = new String("overcast");
            data[4][0] = new String("overcast");
            data[5][0] = new String("rain");
        }
        if (isTarget) {
            data[0][1] = new String("hot");
            data[1][1] = new String("hot");
            data[2][1] = new String("hot");
            data[3][1] = new String("mild");
            data[4][1] = new String("cool");
            data[5][1] = new String("cool");
            data[6][1] = new String("cool");
            data[7][1] = new String("mild");
        } else {
            data[0][1] = new String("cool");
            data[1][1] = new String("mild");
            data[2][1] = new String("mild");
            data[3][1] = new String("mild");
            data[4][1] = new String("hot");
            data[5][1] = new String("mild");
        }
        if (isTarget) {
            data[0][2] = new String("high");
            data[1][2] = new String("high");
            data[2][2] = new String("high");
            data[3][2] = new String("high");
            data[4][2] = new String("normal");
            data[5][2] = new String("normal");
            data[6][2] = new String("normal");
            data[7][2] = new String("high");
        } else {
            data[0][2] = new String("normal");
            data[1][2] = new String("normal");
            data[2][2] = new String("normal");
            data[3][2] = new String("high");
            data[4][2] = new String("normal");
            data[5][2] = new String("high");
        }

        if (isTarget) {
            data[0][3] = new String("weak");
            data[1][3] = new String("strong");
            data[2][3] = new String("weak");
            data[3][3] = new String("weak");
            data[4][3] = new String("weak");
            data[5][3] = new String("strong");
            data[6][3] = new String("strong");
            data[7][3] = new String("weak");
        } else {
            data[0][3] = new String("weak");
            data[1][3] = new String("weak");
            data[2][3] = new String("strong");
            data[3][3] = new String("strong");
            data[4][3] = new String("weak");
            data[5][3] = new String("strong");
        }

        if (isTarget) {
            data[0][4] = new String("no");
            data[1][4] = new String("no");
            data[2][4] = new String("yes");
            data[3][4] = new String("yes");
            data[4][4] = new String("yes");
            data[5][4] = new String("no");
            data[6][4] = new String("yes");
            data[7][4] = new String("no");
        } else {
            data[0][4] = new String("yes");
            data[1][4] = new String("yes");
            data[2][4] = new String("yes");
            data[3][4] = new String("yes");
            data[4][4] = new String("yes");
            data[5][4] = new String("no");
        }


        //explanatory Set


        String outLookValues[] = new String[3];
        outLookValues[0] = "overcast";
        outLookValues[1] = "rain";
        outLookValues[2] = "sunny";
        attributeSet.add(new DiscreteAttribute("Outlook", 0, outLookValues));

        String temperatureValues[] = new String[3];
        temperatureValues[0] = "cool";
        temperatureValues[1] = "hot";
        temperatureValues[2] = "mild";
        attributeSet.add(new DiscreteAttribute("Temperature", 1, temperatureValues));

        String umidityValues[] = new String[2];
        umidityValues[0] = "high";
        umidityValues[1] = "normal";
        attributeSet.add(new DiscreteAttribute("Umidity", 2, umidityValues));

        String windValues[] = new String[2];
        windValues[0] = "strong";
        windValues[1] = "weak";
        attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));


        String playValues[] = new String[2];
        playValues[0] = "no";
        playValues[1] = "yes";
        attributeSet.add(new DiscreteAttribute("PlayTennis", 4, playValues));

    }

    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    public int getNumberOfAttributes() {
        return attributeSet.size();
    }

    public Object getAttributeValue(int exampleIndex, int attributeIndex) {
        return data[exampleIndex][attributeIndex];
    }

    public Attribute getAttribute(int attributeIndex) {
        return attributeSet.get(attributeIndex);
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < numberOfExamples; i++) {
            out = out + (i + 1) + ":";
            for (int j = 0; j < attributeSet.size() - 1; j++)
                out = out + data[i][j] + ",";

            out = out + data[i][attributeSet.size() - 1] + "\n";
        }
        return out;
    }

}
