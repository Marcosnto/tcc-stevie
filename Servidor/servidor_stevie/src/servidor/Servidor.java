package servidor;

import busca.BFS;
import busca.No;
import busca.Node;
import database.connection.model.bean.Tag;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Servidor implements Runnable {
    private List<Tag> tags;
    private BFS bfs;
    private String ip;

    public Servidor(BFS bfs, List<Tag> tags) {
        this.bfs = bfs;
        this.tags = tags;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("Servidor criado, esperando primeira conexão.");
            while (true) {
                Socket socketIn = serverSocket.accept();
                String command = (String) new SocketReceber().receive(socketIn);
                String[] commando = command.split(":");

                switch (commando[0]) {
                    case "destino":
                        System.out.println("Entrei, destino");
                        //pega a localização atual e envia para o método junto com o destino que foi passado
                        //pesquisar o destino commands[1] no banco de dados e pegar o ID da tag vinculada a esse local para realizar a rota
                        //realizar a busca em largura do destino passando a localização atual e o destino
                        String destino = buscarCodTag(commando[1]);
                        String origem = lerArq(); //Pega a localização atual, no caso a ultima tag lida
                        if (origem != null){
                            navegacao(bfs.resultado(bfs.buscaBFS(bfs.buscarTag(origem), bfs.buscarTag(destino))),destino);
                        }
                        //Ir para navegação em tempo real
                        //retornar a lista com os caminhos
                        break;
//                    case "rota":
////                        new Thread(new SocketEnviar("", socketIn.getInetAddress().toString(), 7500)).start();
//                        navegacao();
//                        break;
                    case "ip":
                        System.out.println("Entrei ip");
                        ip = commando[1];
                        break;
                    case "localizacaoAtual":
                        System.out.println("entrei");
                        String result = buscarDescricao(lerArq());
                        Thread.sleep(1000);
                        new SocketEnviar("localizacaoAtual:"+result, ip, 7500).send();
                        break;
                }
                socketIn.close();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("SERVER_RUN"+ e.toString());
        }
    }


    public String buscarCodTag(String nome) {
        if (nome != null) {
            for (int i = 0; i < tags.size(); i++) {
                if (nome.equals(tags.get(i).getNome())) {
                    return tags.get(i).getCod_tag();
                }
            }
        }
        return null;
    }

    public String buscarDescricao(String code) {
        if (code != null) {
            for (int i = 0; i < tags.size(); i++) {
                if (code.equals(tags.get(i).getCod_tag())) {
                    return tags.get(i).getDescricao();
                }
            }
        }
        return null;
    }

    public String buscarTipoTag(String code) {
        if (code != null) {
            for (int i = 0; i < tags.size(); i++) {
                if (code.equals(tags.get(i).getCod_tag())) {
                    return tags.get(i).getTipo_tag();
                }
            }
        }
        return null;
    }

    public void navegacao(No origem, String destino){
        No caminhoAtual = origem;
        No anterior = null;
        Boolean enviado = false;
        Boolean aviso = false;
        Boolean recalcular = false;
        while (caminhoAtual.getProximo() != null) {
            String tagAtual = lerArq();

            //Verifica se chegou no objetivo final
            if (caminhoAtual.getNode().getTag().equals(destino)){
                new SocketEnviar("direcao:Você chegou ao destino", ip, 7500).send();
                break;
            }
            //Verifica se a próxima tag é uma tag de alerta
            if(caminhoAtual.getProximo() != null){
                String alerta = buscarTipoTag(caminhoAtual.getProximo().getNode().getTag());
                if(alerta.equals("alerta") && aviso != true){
                    new SocketEnviar("direcao:Cuidado! Você está próximo de "+ buscarDescricao(caminhoAtual.getProximo().getNode().getTag()), ip, 7500).send();
                    aviso = true;
                }
            }

            if (tagAtual.equals(caminhoAtual.getNode().getTag())) {
                if(caminhoAtual.getProximo().getNode() == caminhoAtual.getNode().getNorte()){
                    new SocketEnviar("direcao:Siga para o Norte", ip, 7500).send();
                }else if(caminhoAtual.getProximo().getNode() == caminhoAtual.getNode().getSul()){
                    new SocketEnviar("direcao:Siga para o Sul", ip, 7500).send();
                }else if(caminhoAtual.getProximo().getNode() == caminhoAtual.getNode().getLeste()){
                    new SocketEnviar("direcao:Siga para o Leste", ip, 7500).send();
                }else if(caminhoAtual.getProximo().getNode() == caminhoAtual.getNode().getOeste()){
                    new SocketEnviar("direcao:Siga para o Oeste", ip, 7500).send();
                }
                anterior = caminhoAtual;
                caminhoAtual = caminhoAtual.getProximo();
            }else if(!tagAtual.equals(caminhoAtual.getProximo().getNode().getTag()) && !tagAtual.equals(caminhoAtual.getNode().getTag())
            &&  !tagAtual.equals(anterior.getNode().getTag())){
                System.out.println("Entrei e recalculei");
                recalcular = true;
                new SocketEnviar("direcao:Recalculando rota, por favor, aguarde!", ip, 7500).send();
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String localizacaoAtual = lerArq(); //Pega a localização atual, no caso a ultima tag lida
                if (origem != null){
                    caminhoAtual = bfs.resultado(bfs.buscaBFS(bfs.buscarTag(localizacaoAtual), bfs.buscarTag(destino)));
                    enviado = false;
                }
                System.out.println("Saindo");
            }

//            if(caminhoAtual.getNode() == null){
//                break;
//            }
//
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        new SocketEnviar("direcao:Você chegou ao destino", ip, 7500).send();
//        this.run();
    }

    public String lerArq() {
        String code = "";
        File lock = new File("c:\\stevie\\Lock.txt");
        try {
                FileReader arq = new FileReader("c:\\stevie\\LeituraTags.txt");
                BufferedReader lerArq = new BufferedReader(arq);

                String linha = lerArq.readLine();
                String[] cod_inteiro = linha.split(" ");
                code = cod_inteiro[cod_inteiro.length-1];

                arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return code;
    }
}