package server;

import data.Data;
import data.EmptySetException;
import database.DatabaseConnectionException;
import database.NoValueException;
import mining.EmergingPatternMiner;
import mining.FrequentPatternMiner;
import utility.Constants;
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
                            out.writeObject(fpMiner.toString());
                            try {
                                EmergingPatternMiner epMiner = new EmergingPatternMiner(dataBackground, fpMiner, minGr);
                                try {
                                    epMiner.salva(Constants.EP_SAVE + targetName + Constants.BACK_SAVE + backgroundName + Constants.MIN_SUP_SAVE + minSup + Constants.MIN_GROW_RATE_SAVE + minGr + ".dat");
                                } catch (IOException e) {
                                    System.err.println(Constants.EMERGING_PATTERN_SAVE_FAILED + ": " + e.getMessage());
                                }
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
                        out.writeObject("");
                    } catch (SQLException e) {
                        // scrivo degli oggetti per poter far ripetere il ciclo
                        System.err.println(Constants.NOT_FOUND_TABLES);
                        out.writeObject(Constants.NOT_FOUND_TABLES);
                        out.writeObject(Constants.DEFAULT);
                    }
                } else if (opzione == 2) {
                    try {
                        FrequentPatternMiner fpMiner = FrequentPatternMiner.carica(
                                Constants.FP_SAVE + targetName + Constants.MIN_SUP_SAVE + minSup + ".dat");
                        out.writeObject(fpMiner.toString());
                        EmergingPatternMiner epMiner = EmergingPatternMiner.carica(
                                Constants.EP_SAVE + targetName + Constants.BACK_SAVE + backgroundName + Constants.MIN_SUP_SAVE + minSup + Constants.MIN_GROW_RATE_SAVE + minGr + ".dat");
                        out.writeObject(epMiner.toString());
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
