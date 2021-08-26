package com.company;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		String[] b = new String[2];
		b[0] = "a";
		b[1] = "b";
		DiscreteItem a = new DiscreteItem(new DiscreteAttribute("ciao",0,b),"lorenzo");
		
		System.out.println(a.checkItemCondition("lorenz"));
-------------------------------
*/

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

		/*
		String[] b = new String[2];
		b[0] = "a";
		b[1] = "b";
		DiscreteItem a = new DiscreteItem(new DiscreteAttribute("ciao",0,b),"lorenzo");
		FrequentPattern c = new FrequentPattern();
		c.addItem(a);
		a =  new DiscreteItem(new DiscreteAttribute("ok",1,b),"jacopo");
		c.addItem(a);
		System.out.println("Frequent pattern "+ c);
		float f = (float) 1.2;
		c.setSupport(f);
		f = (float) 1.4;
		EmergingPattern d = new EmergingPattern(c,f);
		System.out.println("Emerging Pattern " + d);
		Data dataTarget= new Data(true);
		FrequentPatternMiner h = new FrequentPatternMiner(dataTarget,0.3f);
		System.out.println(h.getOutputFP());
	*/
	}
	

}
