import java.io.FileNotFoundException;
import java.io.IOException;

import keyboardinput.Keyboard;
import data.Data;
import data.EmptySetException;
import mining.*;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		char risp='s';
		do{
			System.out.println("Scegli una opzione:");
			int opzione;
			do{
				System.out.println("1:Nuova scoperta");
				System.out.println("2: Risultati in archivio");
				opzione=Keyboard.readInt();
			}while(opzione!=1 && opzione!=2);
			
			float minsup=0f;float minGr=0f;
			do{
				System.out.println("Inserire valore minimo supporto (minsup>0 e minsup<=1):");
				minsup=Keyboard.readFloat();
			}while(minsup<=0 || minsup>1);
			
			do{
				System.out.println("Inserire valore minimo grow rate (minGr>0):");
				minGr=Keyboard.readFloat();
			}while(minGr<=0);
				
			if(opzione==1)
			
			{
				Data dataTarget= new Data(true);
				System.out.println("Target data");
				System.out.println(dataTarget);
				Data dataBackground= new Data(false);
				System.out.println("Background data");
				System.out.println(dataBackground);
				
				
				
				try{
					FrequentPatternMiner fpMiner=new FrequentPatternMiner(dataTarget, minsup);	
					try {
						fpMiner.salva("FP_playtennis_minSup"+minsup+".dat");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
					System.out.println(fpMiner);
					try{
					EmergingPatternMiner epMiner=new EmergingPatternMiner(dataBackground, fpMiner, minGr);
					try {
						epMiner.salva("EP_playtennis_minSup"+minsup+"_minGr"+minGr+".dat");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(epMiner);
					}
					catch(EmptySetException e){
						System.out.println(e);
						
					}
				}
				catch (EmptySetException e) {
					System.out.println(e);
				}
			}
			else
			{
				try {
					FrequentPatternMiner fpMiner=FrequentPatternMiner.carica("FP_playtennis_minSup"+minsup+".dat");
					System.out.println(fpMiner);
					EmergingPatternMiner epMiner=EmergingPatternMiner.carica("EP_playtennis_minSup"+minsup+"_minGr"+minGr+".dat");
					System.out.println(epMiner);
				} catch ( ClassNotFoundException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Vuoi ripetere?(s/n)");
			risp=Keyboard.readChar();
		}while(risp!='n');
		
		
		
	}

}
