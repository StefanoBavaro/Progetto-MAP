package utility;

/**
 * Classe che rappresenta le Costanti del Client Base.
 */
public class Constants {
	
	/**
	 * Costruttore privato per far si che la classe non venga mai istanziata
	 */
	private Constants() { }
	
	/**
	 * Indica il valore di conferma
	 */
	public static final char YES = 's';
	
	/**
	 * Indica il valore di risposta negativa
	 */
	public static final char NO = 'n';
	
	/**
	 * Indica l'opzione da scegliere 1 nuova ricerca 2 archivio
	 */
	public static final String OPTION = "Scegli una opzione:";
	
	/**
	 * Indica l'opzione per la nuova ricerca
	 */
	public static final String DISCOVERY = "1:Nuova scoperta";
	
	/**
	 * Indica l'ozpione per i risultati in archivio
	 */
	public static final String ARCHIVE = "2: Risultati in archivio";
	
	/**
	 * Indica come inserie il valore di minimo supporto
	 */
	public static final String INSERT_SUPPORT = "Inserire valore minimo supporto (minsup>0 e minsup<=1):";
	
	/**
	 * Indica come inserire il valore di grow rate
	 */
	public static final String INSERT_GROW_RATE = "Inserire valore minimo grow rate (minGr>0):";
	
	/**
	 * Indica la tabella target
	 */
	public static final String TARGET_TABLE = "Tabella target:";
	
	/**
	 * Indica la tabella di background
	 */
	public static final String BACKGROUND_TABLE = "Tabella background:";
	
	/**
	 * Indica se si vuole effetture una nuova ricerca
	 */
	public static final String REPEAT = "Vuoi ripetere?(s/n)";
	
	/**
	 * Indica l'address per la connessione
	 */
	public static final String ADDR = "addr = ";
	
	/**
	 * Indica la porta per la connessione
	 */
	public static final String PORT = "\nport=";
	
	/**
	 * Indica la stringa di Default usata per individuare un eventuale errore trasmesso dal Client
	 */
	public static final String DEFAULT = "E";
	
	/**
	 * Indica che le tabelle non sono state trovate dal Server
	 */
	public static final String ERROR_TABLES = "Tabelle non trovate";
	
	/**
	 * Indica un errore generico
	 */
	public static final String ERROR = "Errore";
}
