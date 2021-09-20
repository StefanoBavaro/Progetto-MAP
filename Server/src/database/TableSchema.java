package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Classe che modella lo schema di una tabella nel database relazionale.
 */
public class TableSchema {

	/**
	 * Sessione di connessione al database.
	 */
	private Connection connection;

	/**
	 * Costruttore: inizializza il membro <code>connection</code> con il parametro in input e modella lo schema della tabella in base alla tabella di nome <code>tableName</code>.
	 * @param tableName nome della tabella su cui si basa lo schema.
	 * @param connection sessione attuale di connessione al database.
	 * @throws SQLException se si verifica un errore di accesso al database; se si richiama il metodo con una connessione chiusa.
	 */
	public TableSchema(String tableName, Connection connection) throws SQLException{
		this.connection=connection;
		HashMap<String,String> mapSQL_JAVATypes=new HashMap<String, String>();
		//http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR","string");
		mapSQL_JAVATypes.put("VARCHAR","string");
		mapSQL_JAVATypes.put("LONGVARCHAR","string");
		mapSQL_JAVATypes.put("BIT","string");
		mapSQL_JAVATypes.put("SHORT","number");
		mapSQL_JAVATypes.put("INT","number");
		mapSQL_JAVATypes.put("LONG","number");
		mapSQL_JAVATypes.put("FLOAT","number");
		mapSQL_JAVATypes.put("DOUBLE","number");
		
		 DatabaseMetaData meta = connection.getMetaData();
	     ResultSet res = meta.getColumns(null, null, tableName, null);
	     while (res.next()) {
	         if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
	        		 tableSchema.add(new Column(
	        				 res.getString("COLUMN_NAME"),
	        				 mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
	        				 );
	      }
	      res.close();
	}

	/**
	 * Inner class che modella lo schema di una colonna del database relazionale.
	 */
	public class Column{
		/**
		 * Nome della colonna.
		 */
		private String name;
		/**
		 * Tipo dei valori della colonna
		 */
		private String type;

		/**
		 * Costruttore: inizializza i membri <code>name</code> e <code>type</code> con i due parametri in input.
		 * @param name nome della colonna.
		 * @param type tipo della colonna.
		 */
		Column(String name,String type){
			this.name=name;
			this.type=type;
		}

		/**
		 * Restituisce il valore del membro <code>name</code>.
		 * @return nome della colonna.
		 */
		public String getColumnName(){
			return name;
		}

		/**
		 * Restituisce <code>true</code> se il membro <code>type</code> è uguale alla stringa "number".
		 * @return booleano che indica se il tipo della colonna è numerico.
		 */
		public boolean isNumber(){
			return type.equals("number");
		}

		/**
		 * Override del metodo <code>toString()</code> di <code>Object</code>.
		 * Restituisce una stringa che concatena il nome e il tipo della tabella, separati da ":".
		 * @return stringa che rappresenta lo schema di una tabella.
		 */
		public String toString(){
			return name+":"+type;
		}
	}

	/**
	 *  Lista di colonne dello schema della tabella.
	 */
	List<Column> tableSchema=new ArrayList<Column>();

	/**
	 * Restituisce la cardinalità del membro <code>tableSchema</code>.
	 * @return il numero di attributi della tabella.
	 */
	public int getNumberOfAttributes(){
		return tableSchema.size();
	}

	/**
	 * Restituisce l'elemento in posizione <code>index</code> del membro <code>tableSchema</code>.
	 * @param index indice della colonna.
	 * @return colonna in posizione <code>index</code> dello schema della tabella.
	 */
	public Column getColumn(int index){
		return tableSchema.get(index);
	}

}

		     


