package data;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.List;
import database.*;
import database.TableData.TupleData;


/**
 * Classe che modella un insieme di transazioni (vettori attributo-valore).
 */
public class Data {
	
	/**
	 * Matrice di <code>Object</code> con numero di righe pari al numero di transazioni da memorizzare
	 * e numero di colonne pari al numero di attributi in ciascuna transazione.
	 */
	private Object data [][];

	/**
	 * Cardinalità dell’insieme di transazioni.
	 */
	private int numberOfExamples;

	/**
	 * Lista di attributi, che sono avvalorati in ciascuna transazione.
	 */
	private List<Attribute> attributeSet=new LinkedList<Attribute>();

	/**
	 * Costruttore: carica i dati di addestramento da una tabella della base di dati.
	 * @param tableName nome della tabella del db da cui caricare i dati.
	 * @throws DatabaseConnectionException se si verificano errori di connessione al database.
	 * @throws SQLException se si verifica un errore di accesso al database; se lo schema della tabella ha 0 come cardinalità degli attributi; se il metodo è richiamato con una connessione chiusa.
	 * @throws NoValueException se l'insieme dei risultati è vuoto o il valore calcolato è pari a <code>null</code>.
	 */
	public Data (String tableName) throws DatabaseConnectionException, SQLException, NoValueException{
		// open db connection
			DbAccess db= new DbAccess();
			db.initConnection();

			TableSchema tSchema;
			try {
				tSchema = new TableSchema(tableName, db.getConnection());
				TableData tableData=new TableData(db.getConnection());
				List<TupleData> transSet=tableData.getTransazioni(tableName);
				
				data= new Object[transSet.size()][tSchema.getNumberOfAttributes()];
				for(int i=0;i<transSet.size();i++)
					for(int j=0;j<tSchema.getNumberOfAttributes();j++){
						data[i][j]=transSet.get(i).tuple.get(j);
				}
				
				numberOfExamples=transSet.size();
						
				for(int i=0;i<tSchema.getNumberOfAttributes();i++)
				{
					if(tSchema.getColumn(i).isNumber())
						attributeSet.add(
							new ContinuousAttribute(
									tSchema.getColumn(i).getColumnName(),
									i,
									((Float)(tableData.getAggregateColumnValue(tableName, tSchema.getColumn(i), QUERY_TYPE.MIN))).floatValue(),
									((Float)(tableData.getAggregateColumnValue(tableName, tSchema.getColumn(i), QUERY_TYPE.MAX))).floatValue() + 0.01f
							)
							);
					else
					{
						// avvalora values con i valori distinti della colonna oridinati in maniera crescente
						List<Object> valueList=tableData.getDistinctColumnValues(tableName,tSchema.getColumn(i));
						String values[]=new String[valueList.size()];
						Iterator it= valueList.iterator();
						int ct=0;
						while(it.hasNext()){
							values[ct]=(String)it.next();
							ct++;
						}
						attributeSet.add(new DiscreteAttribute(tSchema.getColumn(i).getColumnName(),i,values));
					}
				}
			} 
			
			finally{
				db.closeConnection();
			}
	}

	/**
	 * Restituisce il valore del membro <code>numberOfExamples</code>
	 * @return cardinalità dell'insieme di transazioni
	 */
	public int getNumberOfExamples(){
		return numberOfExamples;
	}

	/**
	 * Restituisce la cardinalità del membro <code>attributeSet</code>
	 * @return cardinalità dell'insieme degli attributi
	 */
	public int getNumberOfAttributes(){
		return attributeSet.size();
	}

	/**
	 * Restituisce il valore dell' attributo attributeIndex per la transazione exampleIndex meomorizzata in data
	 * @param exampleIndex indice di riga per la matrice data[][] che corrisponde ad una specifica transazione
	 * @param attributeIndex indice di colonna per un attributo
	 * @return valore assunto dall’attributo identificato da attributeIndex nella transazione identificata da exampleIndex nel membro data.
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data[exampleIndex][attributeSet.get(attributeIndex).getIndex()];
	}

	/**
	 * Restituisce l’attributo in posizione index di attributeSet
	 * @param index indice di colonna per un attributo
	 * @return attributo con indice attributeIndex.
	 */
	public Attribute getAttribute(int index){
		return attributeSet.get(index);
	}

	/**
	 * Override del metodo <code>toString()</code> di <code>Object</code>.
	 * Per ogni transazione memorizzata in data, concatena i valori assunti dagli attributi nella transazione separati da virgole in una stringa.
	 * Le stringhe che rappresentano ogni transazione sono poi concatenate in un’unica stringa da restituire in output.
	 * @return unica stringa che rappresenta ogni transazione in data.
	 */
	public String toString(){
		String value="";
		for(int i=0;i<numberOfExamples;i++){
			value+=(i+1)+":";
			for(int j=0;j<attributeSet.size()-1;j++)
				value+=data[i][j]+",";
			
			value+=data[i][attributeSet.size()-1]+"\n";
		}
		return value;
		
		
	}

}
