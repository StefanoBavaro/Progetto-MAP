package com.company;


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
		FrequentPatternMiner fpMiner = new FrequentPatternMiner(dataTarget, 0.3f);

		System.out.println("Frequent patterns");
		System.out.println(fpMiner);
		
		EmergingPatternMiner epMiner=new EmergingPatternMiner(dataBackground, fpMiner, 1f);
		
		System.out.println("Emerging Patterns");
		System.out.println(epMiner);
	}
}
