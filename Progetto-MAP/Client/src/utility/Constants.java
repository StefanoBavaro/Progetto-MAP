package utility;

/**
 * Classe che rappresenta le costanti del Client
 *
 * @author Lorenzo Cassano, Jacopo D'Abramo, Stefano Bavaro
 */
public class Constants {
	//print errors
	public static final String CLOSING_CONNECTION_ERROR = "Errore di chiusura della connessione";
	public static final String ERROR_SENDING_DATA_SERVER = "Errore nell'invio dei dati al Server";
	public static final String ERROR_PORT_LOC = "Localhost o port non validi";
	public static final String ERROR_PORT = "In port inserire un valore numerico";
	public static final String ERROR_LOADING_PAGE = "Errore nel caricamento della pagina";
	
	//resources
	public static final String INSERT_PORT = "fxml/InsertPort.fxml";
	public static final String PRINT_RESULT = "/fxml/PrintResults.fxml";
	public static final String INSERT_PARAMETERS = "/fxml/InsertParameters.fxml";
	
	//wrong values
	public static final String NOT_SELECTED_SEARCH_OPTION = "Non è stato selezionato il criterio  di ricerca";
	public static final String EMPTY_TARGET_FIELD ="Non è stato inserito nessuno valore in target";
	public static final String EMPTY_BACKGROUND_FIELD = "Non è stato inserito nessuno valore in backGround";
	public static final String EMPTY_SUPPORT_FIELD = "Non è stato inserito nessuno valore in supporto";
	public static final String EMPTY_GROWRATE_FIELD = "Non è stato inserito nessuno valore in growRate";
	public static final String ERROR_SUPPORT_VALUE = "Il valore di support deve essere compreso tra 0 e 1";
	public static final String ERROR_GROWRATE_VALUE = "Il valore di growRate deve essere maggiore di 0";
	public static final String ERROR_NUMBER = "In support e growRate devono essere inseriti valori numerici";
	public static final String ERROR_TABLES = "Tabelle non trovate";
	
	//VALUES
	public static final int VALUE_ZERO = 0;
	public static final int VALUE_ONE = 1;
	public static final int VALUE_TWO = 2;

	//prints
	public static final String TITLE = "EP MINER";
	public static final String FREQUENT_PATTERN_EMPTY = "Frequent pattern vuoto\n";
	public static final String EMERGING_PATTERN_EMPTY = "\nEmerging pattern vuoto";
	public static final String FREQUENT_PATTERN_PRINT = "Frequent Pattern\n";
	public static final String EMERGING_PATTERN_PRINT ="\nEmerging Pattern \n";
	public static final String HELP = "Progetto realizzato da: Jacopo D'Abramo, Lorenzo Cassano e Stefano Bavaro. \n" +
			                          "L'interfaccia grafica è stata realizzata tramite l'ausilio della libreria javaFX e del tool Scene Builder per la progettazione grafica. \n" +
										"Quest'ultima è suddivisa in 3 parti: \n" +
										"- La prima finestra, che si avvia all'apertura del software, richiede l'immissione dell'indirizzo del server e di una port Address per poter effettuare la connessione con il server. \n" +
										"Per esempio: IP Address: 127.0.0.1 Port: 8080 (connessione con il server locale)\n" +
										"- La seconda finestra, che viene eseguita solo se la connessione al server non ha prodotto errori, richiede la scelta del criterio di ricerca e l'immissione " +
										"dei dati necessari per la scoperta dei Frequent Pattern e degli Emerging Pattern \n" +
										"Per esempio: - Nuova Scoperta \n Tabella target:playtennistarget \n Taballa background: playtennisBackground \n Supporto: 0.3 \n GrowRate: 1 \n" +
										"- La terza finestra, che si avvia solo se i parametri inseriti risultano validi, restituisce l'output del programma ovvero i Frequent Pattern e gli Emerging Pattern trovati";
	
	// default error value
	public static final String DEFAULT = "E";
	
	// limit width and height
	public static int MIN_WIDTH_MAIN = 500;
	public static int MIN_HEIGHT = 400;
	public static int MIN_WIDTH_P = 600;
}
