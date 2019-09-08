package servidor;

import busca.BFS;

import java.io.IOException;
import java.net.Socket;


class SocketConexao implements Runnable {

    private BFS bfs;
    private Socket socketIn;
    private String ip;

    public SocketConexao(Socket socket, BFS bfs) {
        this.bfs = bfs;
        this.socketIn = socket;
    }

    @Override
    public void run() {

        try {
            String command = (String) new SocketReceber().receive(socketIn);
            String[] commando = command.split(" ");

            switch (commando[0]) {
                case "destino":
                    //pega a localização atual e envia para o método junto com o destino que foi passado
                    //pesquisar o destino commands[1] no banco de dados e pegar o ID da tag vinculada a esse local para realizar a rota
                    //realizar a busca em largura do destino passando a localização atual e o destino
                    //retornar a lista com os caminhos
                    break;
                case "rota":
                    new Thread(new SocketEnviar("", socketIn.getInetAddress().toString(), 7500)).start();
                    break;
                case "ip":
                    ip = commando[1];
                    break;
            }
            socketIn.close();
        } catch (IOException e) {
            System.out.println("SERVER_RUN"+ e.toString());
        }
    }
}