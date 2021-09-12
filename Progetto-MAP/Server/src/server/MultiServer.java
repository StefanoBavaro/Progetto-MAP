package server;

import utility.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe che modella un server multithread in grado di accettare le richieste
 * trasmesse da un generico Client; istanzia un oggetto della classe <code>ServerOneClient</code>,
 * che si occuperà di servire le richieste del client in un thread dedicato.
 * Il server sarà registrato sulla porta predefinita 8080.
 *
 * @author Stefano Bavaro, Lorenzo Cassano, Jacopo D'Abramo.
 */
public class MultiServer {
    /**
     * <code>PORT</code> è la porta su cui sarà registrato il server.
     */
    private static final int PORT = 8080;

    /**
     * Crea un oggetto istanza di <code>MultiServer</code>.
     * @param args argomenti inseriti dalla Command Line.
     */
    public static void main(String[] args){
        new MultiServer();
    }

     /**
     * Invoca il metodo privato <code>run()</code>.
     */
    private MultiServer(){
        run();
    }

    /**
     * Assegna ad una variabile locale il riferimento ad una istanza della classe <code>ServerSocket</code> creata usando la porta <code>PORT</code>.
     * Il server si pone in attesa di richieste di connessione da parte di un client, in risposta alle quali viene restituito
     * l’oggetto <code>Socket</code> da passare come argomento al costruttore della classe <code>ServerOneClient</code>.
     * Se viene generata un'eccezione nella costruzione del thread, chiude la socket; altrimenti, questa verrò chiusa dal thread.
     * Infine, chiude sempre la socket del server.
     */
    private void run(){
        try {
            ServerSocket s = new ServerSocket(PORT);
            System.out.println("Server avviato");
            try {
                while (true) {
                    Socket socket = s.accept();
                    System.out.println(socket);
                    try {
                        new ServerOneClient(socket);
                        System.out.println("Nuovo client connesso");
                    } catch (IOException e) { // Se fallisce chiude la socket, altrimenti questa verrà chiusa dal thread
                        socket.close();
                    }
                }
            } finally {
                s.close();
            }
        }catch(IOException e){
            System.out.println(Constants.ERROR_SOCKET);
        }
    }
}

