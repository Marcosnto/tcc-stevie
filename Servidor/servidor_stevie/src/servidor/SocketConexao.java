package servidor;

import busca.BFS;
import busca.No;
import database.connection.model.bean.Tag;

import java.io.IOException;
import java.net.Socket;
import java.util.List;


class SocketConexao implements Runnable {
    private List<Tag> tags;
    private BFS bfs;
    private Socket socketIn;
    private String ip;

    OuvirArquivo ouvirArquivo = new OuvirArquivo();

    public SocketConexao(Socket socket, BFS bfs, List<Tag> tags) {
        this.bfs = bfs;
        this.tags = tags;
        this.socketIn = socket;

        Thread ouvirArq = new Thread(ouvirArquivo);
        ouvirArq.start();
    }

    public String buscarNomeTag(String nome) {
        if (nome != null) {
            for (int i = 0; i < tags.size(); i++) {
                if (nome.equals(tags.get(i).getNome())) {
                    return tags.get(i).getCod_tag();
                }
            }
        }
        return null;
    }

    @Override
    public void run() {

        try {
            String command = (String) new SocketReceber().receive(socketIn);
            String[] commando = command.split(":");

            switch (commando[0]) {
                case "destino":
                    System.out.println("Entrei, destino");
                    //pega a localização atual e envia para o método junto com o destino que foi passado
                    //pesquisar o destino commands[1] no banco de dados e pegar o ID da tag vinculada a esse local para realizar a rota
                    //realizar a busca em largura do destino passando a localização atual e o destino
                    String destino = buscarNomeTag(commando[1]);
                    String origem = ouvirArquivo.lerArq(); //Pega a localização atual, no caso a ultima tag lida
                    if (origem != null){
                        List<No> resultado = bfs.buscaBFS(bfs.buscarTag(origem), bfs.buscarTag(destino));
                        for (int i = 0; i < resultado.size(); i++) {
                            System.out.println(resultado.get(i).getNo().getTag() + " ");
                        }
                    }
                    //Ir para navegação em tempo real
                    //retornar a lista com os caminhos
                    break;
                case "rota":
                    new Thread(new SocketEnviar("", socketIn.getInetAddress().toString(), 7500)).start();
                    break;
                case "ip":
                    System.out.println("Entrei ip");
                    ip = commando[1];
                    break;
            }
            socketIn.close();
        } catch (IOException e) {
            System.out.println("SERVER_RUN"+ e.toString());
        }
    }
}