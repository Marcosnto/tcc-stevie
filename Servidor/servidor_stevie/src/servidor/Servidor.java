package servidor;

import busca.BFS;
import java.io.IOException;
import java.net.ServerSocket;


public class Servidor implements Runnable {
    private BFS bfs;
    private ServerSocket serverSocket;

    public Servidor(BFS bfs) {
        this.bfs = bfs;
        try {
            this.serverSocket = new ServerSocket(7000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            while (true) {
                new Thread(new SocketConexao(serverSocket.accept(), bfs)).start();
            }

        } catch (IOException e) {
            System.out.println("SERVER_RUN"+ e.toString());
        }
    }
}