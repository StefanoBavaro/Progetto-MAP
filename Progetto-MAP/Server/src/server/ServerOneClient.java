package server;

import data.Data;
import data.EmptySetException;
import database.DatabaseConnectionException;
import database.NoValueException;
import mining.EmergingPatternMiner;
import mining.FrequentPatternMiner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ServerOneClient extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerOneClient(Socket s) throws IOException {
        socket = s;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
        start();
    }

    public void run() {
        try {
            while (true) {
                int opzione = (int) in.readObject();
                float minSup = (float) in.readObject();
                float minGr = (float) in.readObject();
                String targetName = (String) in.readObject();
                String backgroundName = (String) in.readObject();
                System.out.println("Opzione: " + opzione +
                                   "\nminSup: " + minSup +
                                   "\nminGr: " + minGr +
                                   "\ntargetName: " + targetName +
                                   "\nbackgroundName: " + backgroundName);
                if (opzione == 1) {
                    try {
                        Data dataTarget = new Data(targetName);
                        Data dataBackground = new Data(backgroundName);
                        try {
                            FrequentPatternMiner fpMiner = new FrequentPatternMiner(dataTarget, minSup);
                            try {
                                fpMiner.salva("FP_" + targetName + "_minSup" + minSup + ".dat");
                            } catch (IOException e) {
                                System.err.println("Impossibile salvare il file per i Frequent Pattern: " + e.getMessage());
                            }
                            out.writeObject(fpMiner.toString());
                            try {
                                EmergingPatternMiner epMiner = new EmergingPatternMiner(dataBackground, fpMiner, minGr);
                                try {
                                    epMiner.salva("EP_targ_" + targetName + "_back_" + backgroundName + "_minSup" + minSup + "_minGr" + minGr + ".dat");
                                } catch (IOException e) {
                                    System.err.println("Impossibile salvare il file per gli Emerging Pattern: " + e.getMessage());
                                }
                                out.writeObject(epMiner.toString());
                            } catch (EmptySetException e) {
                                System.err.println(e.getMessage());
                                out.writeObject("Frequent Pattern non trovati con questi valori di supporto e di growrate");
                            } catch (ClassCastException e) {
                                System.err.println(e.getMessage());
                                out.writeObject("I dati della tabella di background non sono compatibili con quelli della tabella target");
                            }

                        } catch (EmptySetException e) {
                            System.err.println(e.getMessage());
                            out.writeObject("Non sono stati trovati dati che corrispondono a tali parametri");
                            out.writeObject("");
                        }
                    } catch (NoValueException e) {
                        System.err.println(e.getMessage());
                        out.writeObject("Nessun valore trovato nelle tabelle");
                        out.writeObject("");
                    } catch (DatabaseConnectionException e) {
                        System.err.println("Errore di connessione al Database " + e.getMessage());
                        out.writeObject("Errore di connessione al Database");
                        out.writeObject("");
                    } catch (SQLException e) {
                        // scrivo degli oggetti per poter far ripetere il ciclo
                        System.err.println("Tabelle non trovate nel database");
                        out.writeObject("Tabelle non trovate nel database");
                        out.writeObject("");
                    }
                } else if (opzione == 2) {
                    try {
                        FrequentPatternMiner fpMiner = FrequentPatternMiner.carica("FP_" + targetName + "_minSup" + minSup + ".dat");
                        out.writeObject(fpMiner.toString());
                        EmergingPatternMiner epMiner = EmergingPatternMiner.carica("EP_targ_" + targetName + "_back_" + backgroundName + "_minSup" + minSup + "_minGr" + minGr + ".dat");
                        out.writeObject(epMiner.toString());
                    } catch (ClassNotFoundException | IOException e) {
                        // invio messaggio al Server
                        System.err.println(e.getMessage());
                        out.writeObject("Dati non trovati in archivio per i parametri inseriti");
                        out.writeObject("");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Errore di comunicazione con il client: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Errore di casting: " + e.getMessage());
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                System.err.println("Errore nella chiusura dello stream");
            }
        }
    }

}
