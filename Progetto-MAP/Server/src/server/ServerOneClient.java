package server;

import data.Data;
import data.EmptySetException;
import database.DatabaseConnectionException;
import database.NoValueException;
import mining.EmergingPatternMiner;
import mining.FrequentPatternMiner;
import utility.Constants;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Classe che modella la comunicazione con un singolo client su un thread separato.
 */
public class ServerOneClient extends Thread {
    /**
     * Terminale lato server del canale tramite cui avviene lo scambio di oggetti client-server.
     */
    private Socket socket;

    /**
     * Flusso di oggetti in input al server.
     */
    private ObjectInputStream in;

    /**
     * Flusso di oggetti in output dal server al client.
     */
    private ObjectOutputStream out;

    /**
     * Costruttore della classe; inizializza l'attributo this.socket con il parametro.
     * Inizializza in e out e poi avvia il thread invocando il metodo start() (ereditato dalla classe Thread).
     * @param s terminale lato server
     * @throws IOException  se si verifica un errore di I/O quando si creano gli stream; se la socket è chiusa o non connessa.
     */
    public ServerOneClient(Socket s) throws IOException{
        socket = s;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
        start();
    }

    /**
     * Override del metodo <code>run()</code> della classe <code>Thread</code>, richiamato dal metodo <code>start()</code>.
     * Gestisce le richieste del client, in particolare:
     * <ul>
     *     <li> Se l'opzione scelta è quella di "Nuova scoperta", apprende i pattern e li salva in un file.
     *     <li> Se l'opzione scelta è quella di "Ricerca in archivio", carica i pattern salvati nei file in base ai parametri. </li>
     * </ul>
     * Trasmette quindi al client i pattern che soddisfano i criteri di ricerca passati dall'utente.
     */
    public void run() {
        try {
            while (true) {
                int opzione = (int) in.readObject();
                float minSup = (float) in.readObject();
                float minGr = (float) in.readObject();
                String targetName = (String) in.readObject();
                String backgroundName = (String) in.readObject();
                System.out.println(Constants.OPTION + ": " + opzione + "\n" +
                                   Constants.MIN_SUP + ": " + minSup + "\n" +
                                   Constants.MIN_GROW + ": " + minGr + "\n" +
                                   Constants.TARGET + ": " + targetName + "\n" +
                                   Constants.BACKGROUND + ": " + backgroundName);
                if (opzione == 1) {
                    try {
                        Data dataTarget = new Data(targetName);
                        Data dataBackground = new Data(backgroundName);
                        try {
                            FrequentPatternMiner fpMiner = new FrequentPatternMiner(dataTarget, minSup);
                            try {
                                fpMiner.salva(Constants.FP_SAVE + targetName + Constants.MIN_SUP_SAVE + minSup + ".dat");
                            } catch (IOException e) {
                                System.err.println(Constants.FREQUENT_PATTERN_SAVE_FAILED + ": " + e.getMessage());
                            }
                            try {
                                EmergingPatternMiner epMiner = new EmergingPatternMiner(dataBackground, fpMiner, minGr);
                                try {
                                    epMiner.salva(Constants.EP_SAVE + targetName + Constants.BACK_SAVE + backgroundName + Constants.MIN_SUP_SAVE + minSup + Constants.MIN_GROW_RATE_SAVE + minGr + ".dat");
                                } catch (IOException e) {
                                    System.err.println(Constants.EMERGING_PATTERN_SAVE_FAILED + ": " + e.getMessage());
                                }
                                out.writeObject(fpMiner.toString());
                                out.writeObject(epMiner.toString());
                            } catch (EmptySetException e) {
                                System.err.println(e.getMessage());
                                out.writeObject(Constants.FREQUENT_PATTERN_ERROR_VALUE);
                                out.writeObject(Constants.DEFAULT);
                            } catch (ClassCastException e) {
                                System.err.println(e.getMessage());
                                out.writeObject(Constants.NO_COMPATIBLE_DATA);
                                out.writeObject(Constants.DEFAULT);
                            }
                        } catch (EmptySetException e) {
                            System.err.println(e.getMessage());
                            out.writeObject(Constants.NOT_FOUND_DATA_PARAMETERS);
                            out.writeObject(Constants.DEFAULT);
                        }
                    } catch (NoValueException e) {
                        System.err.println(e.getMessage());
                        out.writeObject(Constants.NOT_FOUND_DATA);
                        out.writeObject(Constants.DEFAULT);
                    } catch (DatabaseConnectionException e) {
                        System.err.println(Constants.ERROR_CONNECTION_DB + e.getMessage());
                        out.writeObject(Constants.ERROR_CONNECTION_DB);
                        out.writeObject(Constants.DEFAULT);
                    } catch (SQLException e) {
                        System.err.println(Constants.NOT_FOUND_TABLES);
                        out.writeObject(Constants.NOT_FOUND_TABLES);
                        out.writeObject(Constants.DEFAULT);
                    }
                } else if (opzione == 2) {
                    try {
                        FrequentPatternMiner fpMiner = FrequentPatternMiner.carica(
                                Constants.FP_SAVE + targetName + Constants.MIN_SUP_SAVE + minSup + ".dat");
                        EmergingPatternMiner epMiner = EmergingPatternMiner.carica(
                                Constants.EP_SAVE + targetName + Constants.BACK_SAVE + backgroundName + Constants.MIN_SUP_SAVE + minSup + Constants.MIN_GROW_RATE_SAVE + minGr + ".dat");
                        out.writeObject(fpMiner.toString());
                        out.writeObject(epMiner.toString());
                    } catch(FileNotFoundException e) {
                        System.err.println(e.getMessage());
                        out.writeObject(Constants.FILE_NOT_FOUND);
                        out.writeObject(Constants.DEFAULT);
                    } catch (ClassNotFoundException | IOException e) {
                        // invio messaggio al Server
                        System.err.println(e.getMessage());
                        out.writeObject(Constants.NOT_FOUND_ARCHIVE_DATA);
                        out.writeObject(Constants.DEFAULT);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(Constants.COMUNICATION_CLIENT_ERROR + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(Constants.CASTING_ERROR + ": " + e.getMessage());
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                System.err.println(Constants.STREAM_ERROR);
            }
        }
    }

}
