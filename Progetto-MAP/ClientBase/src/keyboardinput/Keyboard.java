package keyboardinput;


import java.io.*;
import java.util.*;

/**
 *  Classe che offre una serie di metodi statici utili per gestire gli input da tastiera
 *
 */
public class Keyboard {
	
	/**
	 * attributo che indica se gli errore possono essere stampati o no
	 */
	private static boolean printErrors = true;
	
	/**
	 * Indica il numero di errori
	 */
	private static int errorCount = 0;
	
	/**
	 * Metodo che restituisce il nuemero di errori
	 *
	 * @return numero di errori
	 */
	public static int getErrorCount() {
		return errorCount;
	}
	
	/**
	 * resetta errorCount a 0
	 *
	 * @param count rappresenta il contatore degli errori
	 */
	
	public static void resetErrorCount(int count) {
		errorCount = 0;
	}
	
	/**
	 * Metodo che restituisce il valore di printErrors
	 *
	 * @return valore di printErrors
	 */
	public static boolean getPrintErrors() {
		return printErrors;
	}
	
	/**
	 * Metodo che stabilisce un valore a printErrors
	 *
	 * @param flag valore da inserire in printErrors
	 */
	public static void setPrintErrors(boolean flag) {
		printErrors = flag;
	}
	
	/**
	* Metodo che si occupa di incrementare il numero di errori e stampa il messaggio quando printErrors vale true
	 * @param str messaggio di errore da stampare
	 */
	private static void error(String str) {
		errorCount++;
		if (printErrors)
			System.out.println(str);
	}
	
	/**
	 * Attributo che identifica il token attuale
	 *
	 */
	
	private static String current_token = null;
	
	/**
	 * Attributo che identifica il tokenizer
	 *
	 */
	
	private static StringTokenizer reader;
	
	/**
	 * Attributo che identifica lo stream di input
	 *
	 */
	
	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));
	
	/**
	 * Metodo che restituisce il token successivo richiamando il metodo di nextToken passandogli
	 * come valore booleano: true
	 *
	 * @return il successivo token
	 */
	private static String getNextToken() {
		return getNextToken(true);
	}
	
	/**
	 * Metodo che verifica se il token successivo è stato già letto
	 *
	 * @param skip il valore è vero se il token è già stato letto, falso altrimenti
	 *
	 * @return il successivo token
	 */
	private static String getNextToken(boolean skip) {
		String token;
		
		if (current_token == null)
			token = getNextInputToken(skip);
		else {
			token = current_token;
			current_token = null;
		}
		
		return token;
	}
	
	/**
	 * Metodo per ottenere il token successivo
	 *
	 * @param skip vero se il token è già stato letto, falso altrimenti
	 *
	 * @return il token successivo
	 */
	private static String getNextInputToken(boolean skip) {
		final String delimiters = " \t\n\r\f";
		String token = null;
		
		try {
			if (reader == null)
				reader = new StringTokenizer(in.readLine(), delimiters, true);
			
			while (token == null || ((delimiters.indexOf(token) >= 0) && skip)) {
				while (!reader.hasMoreTokens())
					reader = new StringTokenizer(in.readLine(), delimiters,
							true);
				
				token = reader.nextToken();
			}
		} catch (Exception exception) {
			token = null;
		}
		
		return token;
	}
	
	/**
	 * Metodo che ci dice se ci sono altri token da leggere
	 *
	 * @return booleano:
	 * <ul>
	 *     <li>vero se ci sono altri token </li>
	 *     <li>falso altrimenti </li>
	 * </ul>
	 */
	public static boolean endOfLine() {
		return !reader.hasMoreTokens();
	}
	
	/**
	 * Metodo che legge una stringa
	 *
	 * @return una stringa letta dallo standard input
	 */
	public static String readString() {
		String str;
		
		try {
			str = getNextToken(false);
			while (!endOfLine()) {
				str = str + getNextToken(false);
			}
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			str = null;
		}
		return str;
	}
	
	/**
	 * Metodo che legge una parola
	 *
	 * @return una parola
	 */
	public static String readWord() {
		String token;
		try {
			token = getNextToken();
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			token = null;
		}
		return token;
	}
	
	/**
	 * Metodo che legge un boolean
	 *
	 * @return un boolean letto da standard input
	 */
	public static boolean readBoolean() {
		String token = getNextToken();
		boolean bool;
		try {
			if (token.toLowerCase().equals("true"))
				bool = true;
			else if (token.toLowerCase().equals("false"))
				bool = false;
			else {
				error("Error reading boolean data, false value returned.");
				bool = false;
			}
		} catch (Exception exception) {
			error("Error reading boolean data, false value returned.");
			bool = false;
		}
		return bool;
	}
	
	/**
	 * Metodo che legge un char
	 *
	 * @return un char letto da standard input
	 */
	public static char readChar() {
		String token = getNextToken(false);
		char value;
		try {
			if (token.length() > 1) {
				current_token = token.substring(1, token.length());
			} else
				current_token = null;
			value = token.charAt(0);
		} catch (Exception exception) {
			error("Error reading char data, MIN_VALUE value returned.");
			value = Character.MIN_VALUE;
		}
		
		return value;
	}
	
	/**
	 * Metodo che legge un un int
	 *
	 * @return un int letto da standard input
	 */
	public static int readInt() {
		String token = getNextToken();
		int value;
		try {
			value = Integer.parseInt(token);
		} catch (Exception exception) {
			error("Error reading int data, MIN_VALUE value returned.");
			value = Integer.MIN_VALUE;
		}
		return value;
	}
	
	/**
	 * Metodo che legge un long
	 *
	 * @return un long letto da standard input
	 */
	public static long readLong() {
		String token = getNextToken();
		long value;
		try {
			value = Long.parseLong(token);
		} catch (Exception exception) {
			error("Error reading long data, MIN_VALUE value returned.");
			value = Long.MIN_VALUE;
		}
		return value;
	}
	
	/**
	 * Metodo che legge un float
	 *
	 * @return un float letto da standard input
	 */
	public static float readFloat() {
		String token = getNextToken();
		float value;
		try {
			value = (new Float(token)).floatValue();
		} catch (Exception exception) {
			error("Error reading float data, NaN value returned.");
			value = Float.NaN;
		}
		return value;
	}
	
	/**
	 * Metodo che legge un double
	 *
	 * @return un double letto da standard input
	 */
	public static double readDouble() {
		String token = getNextToken();
		double value;
		try {
			value = (new Double(token)).doubleValue();
		} catch (Exception exception) {
			error("Error reading double data, NaN value returned.");
			value = Double.NaN;
		}
		return value;
	}
}
