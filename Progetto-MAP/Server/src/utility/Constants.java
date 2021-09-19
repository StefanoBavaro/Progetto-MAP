package utility;


/**
 * Classe di costanti del Server
 */
public class Constants {
	
	/**
	 * Costruttore privato per far si che la classe non venga mai istanziata
	 */
	private Constants() { }
	
	//Messaggi del server
	
	/**
	 * Indica l'opzione di ricerca
	 */
	public static final String OPTION = "Opzione";
	
	/**
	 * minimo support scelto
	 */
	public static final String MIN_SUP = "minSup";
	
	/**
	 *  grow rate scelto
	 */
	public static final String MIN_GROW = "minGr";
	
	/**
	 * tabella di target scelta
	 */
	public static final String TARGET = "TargetName";
	
	/**
	 * tabella di background scelta
	 */
	public static final String BACKGROUND = "backgroundName";
	
	/**
	 * Indica che il server è stato avviato
	 */
	public static final String SERVER_START = "Server avviato";
	
	/**
	 * Indica la connessione di un nuovo client
	 */
	public static final String NEW_CLIENT = "Nuovo client connesso";
	
	//Stampe di errori
	
	/**
	 * Indica che si è verificato un errore nel salvataggio dei Frequent Pattern nei file
	 */
	public static final String FREQUENT_PATTERN_SAVE_FAILED = "Impossibile salvare il file per i Frequent Pattern";
	
	/**
	 * Indica che si è verificato un errore nel salvataggio degli Emerging Pattern nei file
	 */
	public static final String EMERGING_PATTERN_SAVE_FAILED = "Impossibile salvare il file per gli Emerging Pattern";
	
	/**
	 * Indica che si è verificato un errore nella ricerca dei frequentPattern
	 */
	public static final String FREQUENT_PATTERN_ERROR_VALUE = "Frequent Pattern non trovati con questi valori di supporto e di growrate";
	
	/**
	 * Indica l'incompatibilità tra tabella target e tabella di background
	 */
	public static final String NO_COMPATIBLE_DATA = "I dati della tabella di background non sono compatibili con quelli della tabella target";
	
	/**
	 * Indica che non sono stati trovati dati con i parametri scelti
	 */
	public static final String NOT_FOUND_DATA_PARAMETERS = "Non sono stati trovati dati che corrispondono a tali parametri";
	
	/**
	 * Indica che non sono stati trovati valori nelle tabelle
	 */
	public static final String NOT_FOUND_DATA = "Nessun valore trovato nelle tabelle";
	
	 /**
	  * Indica che si è verificato un errore durante la connessione al database
	  */
	public static final String ERROR_CONNECTION_DB = "Errore di connessione al Database ";
	
	/**
	 * Indica che tabelle desiderate non sono state trovate nel database
	 */
	public static final String NOT_FOUND_TABLES = "Tabelle non trovate nel database";
	
	/**
	 * Indica che si è verificato un errore nella ricerca con i parametri inseriti
	 */
	public static final String NOT_FOUND_ARCHIVE_DATA = "Dati non trovati in archivio per i parametri inseriti";
	
	/**
	 * Indica che si è verificato un errore nella comunicazione con il client
	 */
	public static final String COMUNICATION_CLIENT_ERROR = "Errore di comunicazione con il client";
	
	/**
	 * Indica che si è verificato un errore di casting
	 */
	public static final String CASTING_ERROR = "Errore di casting";
	
	/**
	 * Indica che si è verificato un errore nella chiusura degli stream
	 */
	public static final String STREAM_ERROR = "Errore nella chiusura dello stream";

	
	//Salva/carica file
	
	/**
	 * Indica che non sono stati trovati file
	 */
	public static final String FILE_NOT_FOUND = "File non trovati";
	
	/**
	 * frequent pattern da salvare
	 */
	public static final String FP_SAVE = "FP_";
	
	/**
	 * minimo supporto da salvare
	 */
	public static final String MIN_SUP_SAVE = "_minSup";
	
	/**
	 * emerging pattern da salvare
	 */
	public static final String EP_SAVE = "EP_targ_";
	
	/**
	 * tabella background da salvare
	 */
	public static final String BACK_SAVE = "_back_";
	
	/**
	 * minimo grow rate da salvare
	 */
	public static final String MIN_GROW_RATE_SAVE = "_minGr";
	
	/**
	 * valori di iterator
	 */
	public static final int ITERATOR_VALUES = 5;
	
	/**
	 * Indica che si è verificato un errore nel trovare il driver
	 */
	public static final String DRIVER_NOT_FOUND = "Impossibile trovare il Driver: ";
	
	/**
	 * Errore nella connessione al database
	 */
	public static final String ERROR_DB_CONNECTION = "Impossibile connettersi al DB";
	
	/**
	 * Errore nella chiusura della connessione
	 */
	public static final String ERROR_CLOSING_CONNECTION = "Impossibile chiudere la connessione";
	
	/**
	 * Indica che si è verificato un errore nella creazione del socket
	 */
	public static final String ERROR_SOCKET = "Errore nella creazione del socket";
	
	/**
	 * Training set vuoto
	 */
	public static final String EMPTY_TRAININGSET = "L'insieme di training è vuoto";
	
	/**
	 * Indica che i pattern non soddisfano il valore di grow rate scelto
	 */
	public static final String GROWRATE_NOT_VALID = "Il pattern non soddisfa il minimo grow rate";

	//Valore di errore
	
	/**
	 * Indica il valore di default
 	 */
	public static final String DEFAULT = "E";
}
