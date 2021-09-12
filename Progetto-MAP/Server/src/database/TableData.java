package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import database.TableSchema.Column;

/**
 * Classe che modella l’insieme di tuple collezionate in una tabella.
 */
public class TableData {

	/**
	 * Sessione di connessione al database.
	 */
	private Connection connection;

	/**
	 * Costruttore: inizializza il membro <code>connection</code> con parametro in input.
	 * @param connection sessione attuale di connessione al database.
	 */
	public TableData(Connection connection){this.connection=connection;}

	/**
	 * Inner class che modella una singola tupla.
	 */
	public class TupleData{

		/**
		 * Lista degli elementi della tupla.
		 */
		public List<Object> tuple=new ArrayList<Object>();

		/**
		 * Concatena gli attributi di una tupla.
		 * Override del toString() di Object.
		 * @return stringa che rappresenta una tupla.
		 */
		public String toString(){
			String value="";
			Iterator<Object> it= tuple.iterator();
			while(it.hasNext())
				value+= (it.next().toString() + " ");
			
			return value;
		}
	}

	/**
	 * Ricava lo schema della tabella di nome <code>table</code>. Esegue una interrogazione per estrarre le tuple da tale tabella.
	 * Per ogni tupla dell'insieme dei risultati, crea un oggetto, istanza della classe Tupla, il cui riferimento va incluso nella lista da restituire.
	 * In particolare, per la tupla corrente nel resultset, estrae i valori dei singoli campi (usando <code>getFloat()</code> o <code>getString()</code>),
	 * e li aggiunge all’oggetto istanza della classe <code>TupleData</code> che si sta costruendo.
	 * @param table nome della tabella nel database.
	 * @return lista di tuple memorizzate nella tabella.
	 * @throws SQLException se si verifica un errore di accesso al database; se lo schema della tabella ha 0 come cardinalità degli attributi; se il metodo è richiamato con una connessione chiusa.
	 */
	public List<TupleData> getTransazioni(String table) throws SQLException{
		LinkedList<TupleData> transSet = new LinkedList<TupleData>();
		Statement statement;
		TableSchema tSchema=new TableSchema(table,connection);

		String query="select ";
		
		for(int i=0;i<tSchema.getNumberOfAttributes();i++){
			Column c=tSchema.getColumn(i);
			if(i>0)
				query+=",";
			query += c.getColumnName();
		}
		if(tSchema.getNumberOfAttributes()==0)
			throw new SQLException();
		query += (" FROM "+table);
		
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			TupleData currentTuple=new TupleData();
			for(int i=0;i<tSchema.getNumberOfAttributes();i++)
				if(tSchema.getColumn(i).isNumber())
					currentTuple.tuple.add(rs.getFloat(i+1));
				else
					currentTuple.tuple.add(rs.getString(i+1));
			transSet.add(currentTuple);
		}
		rs.close();
		statement.close();

		return transSet;

	}

	/**
	 * Formula ed esegue una interrogazione SQL per estrarre i valori distinti ordinati di <code>column</code> e popolare una lista da restituire.
	 * @param table  nome della tabella.
	 * @param column nome della colonna nella tabella.
	 * @return Lista di valori distinti ordinati in modo ascendente che l’attributo di nome column assume nella tabella di nome table.
	 * @throws SQLException se si verifica un errore di accesso al database; se si richiama il metodo con una connessione chiusa.
	 */
	public  List<Object>getDistinctColumnValues(String table,Column column) throws SQLException{
		LinkedList<Object> valueSet = new LinkedList<Object>();
		Statement statement;
		TableSchema tSchema=new TableSchema(table,connection);
		
		
		String query="select distinct ";
		
		query+= column.getColumnName();
		
		query += (" FROM "+table);
		
		query += (" ORDER BY " +column.getColumnName());

		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
				if(column.isNumber())
					valueSet.add(rs.getFloat(1));
				else
					valueSet.add(rs.getString(1));
			
		}
		rs.close();
		statement.close();
		
		return valueSet;

	}

	/**
	 * Formula ed esegue una interrogazione SQL per estrarre il valore aggregato cercato nella colonna di nome <code>column</code> della tabella di nome <code>table</code>.
	 * @param table nome della tabella.
	 * @param column nome della colonna.
	 * @param aggregate operatore SQL di aggregazione.
	 * @return Aggregato cercato.
	 * @throws SQLException se si verifica un errore di accesso al database; se si richiama il metodo con una connessione chisua.
	 * @throws NoValueException se l'insieme dei risultati è vuoto o il valore calcolato è pari a <code>null</code>.
	 */
	public  Object getAggregateColumnValue(String table,Column column,QUERY_TYPE aggregate) throws SQLException, NoValueException{
		Statement statement;
		TableSchema tSchema=new TableSchema(table,connection);
		Object value=null;
		String aggregateOp="";
		
		String query="select ";
		if(aggregate==QUERY_TYPE.MAX)
			aggregateOp+="max";
		else
			aggregateOp+="min";
		query+=aggregateOp+"("+column.getColumnName()+ ") FROM "+table;
		
		
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
				if(column.isNumber())
					value=rs.getFloat(1);
				else
					value=rs.getString(1);
			
		}
		rs.close();
		statement.close();
		if(value==null)
			throw new NoValueException("No " + aggregateOp+ " on "+ column.getColumnName());
			
		return value;

	}

}
