package servidor;

import busca.BFS;
import database.connection.model.bean.Tag;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;


public class Servidor implements Runnable {
    private List<Tag> tags;
    private BFS bfs;
    private ServerSocket serverSocket;

    public Servidor(BFS bfs, List<Tag> tags) {
        this.bfs = bfs;
        this.tags = tags;
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
                new Thread(new SocketConexao(serverSocket.accept(), bfs, tags)).start();
            }

        } catch (IOException e) {
            System.out.println("SERVER_RUN"+ e.toString());
        }
    }
}