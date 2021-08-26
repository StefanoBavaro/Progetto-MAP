import data.Data;
import data.EmptySetException;
import keyboardinput.Keyboard;
import mining.EmergingPatternMiner;
import mining.FrequentPatternMiner;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Data dataTarget = new Data(true);
		System.out.println("Target data");
		System.out.println(dataTarget);
		Data dataBackground = new Data(false);

		System.out.println("Background data");
		System.out.println(dataBackground);

		float minSup;
		do {
			System.out.println("Inserire valore minimo supporto (minSup>0 e minSup<=1):");
			minSup = Keyboard.readFloat();
		} while (minSup <= 0 || minSup > 1);

		float minGr;
		do {
			System.out.println("Inserire valore minimo grow rate (minGr>0):");
			minGr = Keyboard.readFloat();
		} while (minGr <= 0);



		try {
			FrequentPatternMiner fpMiner = new FrequentPatternMiner(dataTarget, minSup);
			System.out.println("Frequent patterns");
			System.out.println(fpMiner);

			EmergingPatternMiner epMiner = new EmergingPatternMiner(dataBackground, fpMiner, minGr);

			System.out.println("Emerging Patterns");
			System.out.println(epMiner);
		} catch (EmptySetException e) {
			System.err.println(e.getMessage());
		}
	}
}
