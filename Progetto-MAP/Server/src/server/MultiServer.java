package server;

import utility.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        new MultiServer();
    }

    private MultiServer() throws IOException {
        run();
    }

    private void run() throws IOException {
        try {
            ServerSocket s = new ServerSocket(PORT);
            try {
                while (true) {
                    Socket socket = s.accept();
                    try {
                        new ServerOneClient(socket);
                        System.out.println(Constants.NEW_CLIENT);
                    } catch (IOException e) { // Se fallisce chiude la socket, altrimenti il thread la chiudera'
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

