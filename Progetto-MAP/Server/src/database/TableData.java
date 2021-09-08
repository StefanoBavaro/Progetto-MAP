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

	private Connection connection;

	/**
	 * Costruttore con parametro
	 * @param connection
	 */
	public TableData(Connection connection){this.connection=connection;}

	/**
	 * Inner class che modella una singola tupla
	 */
	public class TupleData{

		/**
		 * Lista degli elementi della tupla
		 */
		public List<Object> tuple=new ArrayList<Object>();

		/**
		 * Override del toString() di Object
		 * @return stringa che rappresenta una tupla (concatenazione di attributi)
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
	 * Ricava lo schema della tabella di nome table. Esegue una interrogazione per estrarre le tuple da tale tabella.
	 * Per ogni tupla dell'insieme dei risultati,  si crea un oggetto, istanza della classe Tupla, il cui riferimento
	 * va incluso nella lista da restituire. In particolare, per la tupla corrente nel resultset, si estraggono i valori
	 * dei singoli campi (usando getFloat() o getString()),e li si aggiungono all’oggetto istanza della classe Tupla che si sta costruendo.
	 * @param table nome della tabella nel database
	 * @return lista di tuple memorizzate nella tabella
	 * @throws SQLException
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
	 * Formula ed esegue una interrogazione SQL per estrarre i valori distinti ordinati di column e popolare una lista da restituire.
	 * @param table  nome della tabella
	 * @param column nome della colonna nella tabella
	 * @return Lista di valori distinti ordinati in modo ascendente che l’attributo di nome column assume nella tabella di nome table
	 * @throws SQLException
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
	 * Formula ed esegue una interrogazione SQL per estrarre il valore aggregato cercato nella colonna di nome column della tabella di nome table.
	 * Il metodo solleva e propaga una NoValueException se il resultset è vuoto o il valore calcolato è pari a null.
	 * @param table nome di tabella
	 * @param column nome di colonna
	 * @param aggregate operatore SQL di aggregazione
	 * @return Aggregato cercato.
	 * @throws SQLException
	 * @throws NoValueException
	 */
	public  Object getAggregateColumnValue(String table,Column column,QUERY_TYPE aggregate) throws SQLException,NoValueException{
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
