package utility;

public class Constants {
	
	//Server messages
	public static final String OPTION = "Opzione";
	public static final String MIN_SUP = "minSup";
	public static final String MIN_GROW = "minGr";
	public static final String TARGET = "TargetName";
	public static final String BACKGROUND = "backgroundName";
	
	// print errors
	public static final String FREQUENT_PATTERN_SAVE_FAILED = "Impossibile salvare il file per i Frequent Pattern";
	public static final String EMERGING_PATTERN_SAVE_FAILED = "Impossibile salvare il file per gli Emerging Pattern";
	public static final String FREQUENT_PATTERN_ERROR_VALUE = "Frequent Pattern non trovati con questi valori di supporto e di growrate";
	public static final String NO_COMPATIBLE_DATA = "I dati della tabella di background non sono compatibili con quelli della tabella target";
	public static final String NOT_FOUND_DATA_PARAMETERS = "Non sono stati trovati dati che corrispondono a tali parametri";
	public static final String NOT_FOUND_DATA = "Nessun valore trovato nelle tabelle";
	public static final String ERROR_CONNECTION_DB = "Errore di connessione al Database ";
	public static final String NOT_FOUND_TABLES = "Tabelle non trovate nel database";
	public static final String NOT_FOUND_ARCHIVE_DATA = "Dati non trovati in archivio per i parametri inseriti";
	public static final String COMUNICATION_CLIENT_ERROR = "Errore di comunicazione con il client";
	public static final String CASTING_ERROR = "Errore di casting";
	public static final String STREAM_ERROR = "Errore nella chiusura dello stream";
	
	// save/load files
	public static final String FP_SAVE = "FP_";
	public static final String MIN_SUP_SAVE = "_minSup";
	public static final String EP_SAVE = "EP_targ_";
	public static final String BACK_SAVE = "_back_";
	public static final String MIN_GROW_RATE_SAVE = "_minGr";
}
