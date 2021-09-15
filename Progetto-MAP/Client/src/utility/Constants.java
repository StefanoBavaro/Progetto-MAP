package utility;

/**
 * Classe che rappresenta le costanti del Client Esteso.
 */
public class Constants {
	//Stampe di errore
	
	/**
	 * Indica che si è verificato un errore durante la chiusura della connessione
	 */
	public static final String CLOSING_CONNECTION_ERROR = "Errore di chiusura della connessione";
	
	/**
	 * Indica che si è verificato un errore nell'invio dei dati al server
	 */
	public static final String ERROR_SENDING_DATA_SERVER = "Errore nell'invio dei dati al Server";
	
	/**
	 * Errore nei parametri localhost o port
	 */
	public static final String ERROR_PORT_LOC = "Localhost o port non validi";
	
	/**
	 * Errore valore non numeric nel parametro port
	 */
	public static final String ERROR_PORT = "In port inserire un valore numerico";
	
	/**
	 * Indica che si è verificato un errore nel caricamento della pagina
	 */
	public static final String ERROR_LOADING_PAGE = "Errore nel caricamento della pagina";
	
	//Risorse
	
	/**
	 * Indica la prima finestra dell'interfaccia grafica
	 */
	public static final String INSERT_PORT = "fxml/InsertPort.fxml";
	
	/** Indica la terza finestra dell'interfaccia grafica
	 *
	 */
	public static final String PRINT_RESULT = "/fxml/PrintResults.fxml";
	
	/**
	 * Indica la seconda finestra dell'interfaccia grafica
	 */
	public static final String INSERT_PARAMETERS = "/fxml/InsertParameters.fxml";
	
	//Valori errati
	
	/**
	 * Indica che si è verificato un errore nella selezione del parametro di ricerca
	 */
	public static final String NOT_SELECTED_SEARCH_OPTION = "Non è stato selezionato il criterio di ricerca";
	
	/**
	 * Indica che si è verificato un errore nell'inserimento della tabella target
	 */
	public static final String EMPTY_TARGET_FIELD ="Non è stato inserito nessuno valore in target";
	
	/**
	 * Indica che si è verificato un errore nell'inserimento della tabella background
	 */
	public static final String EMPTY_BACKGROUND_FIELD = "Non è stato inserito nessuno valore in backGround";
	
	/**
	 * Indica che si è verificato un errore nell'inserimento del valore di minimo support
	 */
	public static final String EMPTY_SUPPORT_FIELD = "Non è stato inserito nessuno valore in supporto";
	
	/**
	 * Indica che si è verificato un errore nell'inserimento dell valore di grow rate
	 */
	public static final String EMPTY_GROWRATE_FIELD = "Non è stato inserito nessuno valore in growRate";
	
	/**
	 * Indica che si è verificato un errore nell'inserimento della tabella target
	 */
	public static final String ERROR_SUPPORT_VALUE = "Il valore di support deve essere compreso tra 0 e 1";
	
	/**
	 * Indica che si è verificato un errore nell'inserimento del valore di grow rate
	 */
	public static final String ERROR_GROWRATE_VALUE = "Il valore di growRate deve essere maggiore di 0";
	
	/**
	 * Indica che non sono stati inseriti valori numerici come minimo supporto e grow rate
	 */
	public static final String ERROR_NUMBER = "In support e growRate devono essere inseriti valori numerici";
	
	/**
	 * Indica che non sono state trovate le tabelle desiderate
	 */
	public static final String ERROR_TABLES = "Tabelle non trovate";
	
	//VALORI
	
	/**
	 * Indica il valore zero
	 */
	public static final int VALUE_ZERO = 0;
	
	/**
	 * Indica il valore uno
	 */
	public static final int VALUE_ONE = 1;
	
	/**
	 * Indica il valore due
	 */
	public static final int VALUE_TWO = 2;

	//Stampe
	/**
	 * Indica il titolo del progetto
	 */
	public static final String TITLE = "EP MINER";
	
	/**
	 * Indica che il Frequent pattern è vuoto
	 */
	public static final String FREQUENT_PATTERN_EMPTY = "Frequent pattern vuoto\n";
	
	/**
	 * Indica che l'Emerging pattern è vuoto
	 */
	public static final String EMERGING_PATTERN_EMPTY = "\nEmerging pattern vuoto";
	
	/**
	 * Indica Frequent pattern
	 */
	public static final String FREQUENT_PATTERN_PRINT = "Frequent Pattern\n";
	
	/**
	 * Indica l'Emerging pattern
	 */
	public static final String EMERGING_PATTERN_PRINT ="\nEmerging Pattern \n";
	
	/**
	 * Indica l'Help del progetto
	 */
	public static final String HELP = "Progetto realizzato da: Jacopo D'Abramo, Lorenzo Cassano e Stefano Bavaro. \n" +
			                          "L'interfaccia grafica è stata realizzata tramite l'ausilio della libreria javaFX e del tool Scene Builder per la progettazione grafica. \n" +
										"Quest'ultima è suddivisa in 3 parti: \n" +
										"- La prima finestra, che si avvia all'apertura del software, richiede l'immissione dell'indirizzo del server e di una port Address per poter effettuare la connessione con il server. \n" +
										"Per esempio: IP Address: 127.0.0.1 Port: 8080 (connessione con il server locale)\n" +
										"- La seconda finestra, che viene eseguita solo se la connessione al server non ha prodotto errori, richiede la scelta del criterio di ricerca e l'immissione " +
										"dei dati necessari per la scoperta dei Frequent Pattern e degli Emerging Pattern \n" +
										"Per esempio: - Nuova Scoperta \n Tabella target:playtennistarget \n Taballa background: playtennisBackground \n Supporto: 0.3 \n GrowRate: 1 \n" +
										"- La terza finestra, che si avvia solo se i parametri inseriti risultano validi, restituisce l'output del programma ovvero i Frequent Pattern e gli Emerging Pattern trovati";
	
	//Valore di erorre di default
	/**
	 * Indica la stringa di Default usata per individuare un eventuale errore trasmesso dal Client
	 */
	public static final String DEFAULT = "E";
	
	//Limiti di altezza e ampiezza
	/**
	 * Indica la larghezza minima della finestra di ControllerPort
	 */
	public static int MIN_WIDTH_MAIN = 500;
	
	/**
	 * Indica l'altezza minima delle finestre
	 */
	public static int MIN_HEIGHT = 400;
	
	/**
	 * Indica la largheza minima della finestra di InsertParameters e PrintResults
	 */
	public static int MIN_WIDTH_P = 600;
}
