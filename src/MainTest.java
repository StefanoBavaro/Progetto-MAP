import data.Data;
import data.EmptySetException;
import mining.EmergingPatternException;
import mining.EmergingPatternMiner;
import mining.FrequentPatternMiner;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Data dataTarget= new Data(true);
		System.out.println("Target data");
		System.out.println(dataTarget);
		Data dataBackground= new Data(false);
		
		System.out.println("Background data");
		System.out.println(dataBackground);
		try{
			FrequentPatternMiner fpMiner = new FrequentPatternMiner(dataTarget, 0.3f);
			System.out.println("Frequent patterns");
			System.out.println(fpMiner);

			EmergingPatternMiner epMiner=new EmergingPatternMiner(dataBackground, fpMiner, 1f);

			System.out.println("Emerging Patterns");
			System.out.println(epMiner);
		}catch(EmptySetException e){
			System.err.println(e.getMessage());
		}
	}
}
