package com.example.stevie;

import java.io.*;
import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.stevie.connection.model.bean.Departamento;
import com.example.stevie.connection.model.bean.Laboratorio;
import com.example.stevie.connection.model.bean.Ponto_Interesse;
import com.example.stevie.connection.model.bean.Tag;
import com.example.stevie.connection.model.dao.DepartamentoDAO;
import com.example.stevie.connection.model.dao.LaboratorioDAO;
import com.example.stevie.connection.model.dao.Ponto_InteresseDAO;
import com.example.stevie.connection.model.dao.TagDAO;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;

public class Main {
    static ArrayList<Tag> tags_banco = new ArrayList<>();

    static TagDAO tDAO = new TagDAO();
    static DepartamentoDAO dDAO = new DepartamentoDAO();
    static LaboratorioDAO lDAO = new LaboratorioDAO();
    static Ponto_InteresseDAO pDAO = new Ponto_InteresseDAO();

    //lista onde serão carregados os dados do banco assim que o servidor
    //for iniciado
    static List<Tag> tags = new ArrayList<>();
    static List<Departamento> departamentos = new ArrayList<>();
    static List<Laboratorio> laboratorios = new ArrayList<>();
    static List<Ponto_Interesse> pontos_interesses = new ArrayList<>();
    static String todosDepartamentos = "";

    public static void main(String[] args) {
        // carrega todas os dados do banco
//        carregarTags();
//        carregarDepartamentos();
//        carregarLaboratorios();
//        carregarPontosInteresse();

        Thread c = new Thread(new ConexaoBL());
        c.start();

        // startar a thread para ouvir as modificações
//        OuvirArquivo ouvirArquivo = new OuvirArquivo();
//        Thread ouvirArq = new Thread(ouvirArquivo);
//        ouvirArq.start();

////         iniciando o servidor bluetooth
//        ConexaoBluetooth bluetooth = new ConexaoBluetooth();
//        Thread conexao = new Thread(bluetooth);
//        conexao.start();

//        com.example.stevie.BFS bfs = new com.example.stevie.BFS(tags);
    }

    public static void adicionarTags() {
        //modelo teste para adicionar tags
//        tag.setCod_tag("teste_codigo2");
//        tag.setId_tag_frente("tag frente2");
//        tag.setId_tag_tras("tag tras2");
//        tag.setId_tag_esquerda("tag esquerda2");
//        tag.setId_tag_direita("teste direita2");
//        dao.create(tag);
    }

    public static void carregarTags() {
        for (Tag t : tDAO.read()) {
            tags.add(t);
        }
    }

    public static void carregarDepartamentos() {
        for (Departamento d : dDAO.read()) {
            departamentos.add(d);
//            todosDepartamentos += d.getNome() + ";";
        }
    }

    public static void carregarLaboratorios() {
        for (Laboratorio l : lDAO.read()) {
            laboratorios.add(l);
        }
    }

    public static void carregarPontosInteresse() {
        for (Ponto_Interesse p : pDAO.read()) {
            pontos_interesses.add(p);
        }
    }

}

class OuvirArquivo implements Runnable {
    /* Diretório no qual o JNotify irá ficar ouvindo para
     * ver se houve modificação */
    private static final String PATH_TO_LISTEN = "C:\\stevie\\";

    /* A variável mask irá identificar o que deve ser monitorado no diretório acima
     * podendo ser: Criação, deleções, modificações e renomeações do arquivo*/
    private static int mask = JNotify.FILE_CREATED |
            JNotify.FILE_DELETED |
            JNotify.FILE_MODIFIED |
            JNotify.FILE_RENAMED;

    /* essa variável serve para habilitar a escuta de sub-diretórios
     * que venham a ser criados posteriormente, porém não irei precisar
     * então deixo falso */
    private static boolean ouvirSubDiretorios = false;

    @Override

    public void run() {
        try {
            int watchID = JNotify.addWatch(PATH_TO_LISTEN, mask, ouvirSubDiretorios, new JNotifyAdapter() {

                public void fileCreated(int wd, String rootPath,
                                        String name) {
                    System.out.println("Arquivo criado: " + name);
                }

                public void fileModified(int wd, String rootPath, String name) {
                    System.out.println("Arquivo modificado: " + name);
                    String ultimaTag = lerArq();
                    System.out.println(ultimaTag);
                }
            });
            Thread.sleep(1000000);
        } catch (JNotifyException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String lerArq() {
        String ultimaLinha = "";
        try {
            FileReader arq = new FileReader("c:\\stevie\\LeituraTags.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while ((linha = lerArq.readLine()) != null) {
                ultimaLinha = linha;
            }

            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return ultimaLinha;
    }
}

class ConexaoBL implements Runnable {
    LocalDevice local = null;

    StreamConnectionNotifier notifier;
    StreamConnection connection = null;

    public ConexaoBL () {

    }

    @Override
    public void run() {
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID(80087355); // "04c6093b-0000-1000-8000-00805f9b34fb"
            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);
            System.out.println("Conexão aberta! :D");

        } catch (Exception e) {
            System.out.println("Algo deu errado ao tentar abrir conexão. :(");
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                if(connection == null){
                    System.out.println("Esperando a conexão :c");
                    connection = notifier.acceptAndOpen();
                    System.out.println("CONECTEI, CANSO");
                }
                InputStream is = connection.openInputStream();
                OutputStream os = connection.openOutputStream();
                String mensagem = receberDados(is);
                Thread.sleep(2000);
                switch (mensagem){
                    case "Hi, baby":
                        enviarDados("hello, canso", os);
                        System.out.println(mensagem);
                        break;
                }
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String receberDados(InputStream is){
        try {
            byte[] buffer = new byte[1024];
            is.read(buffer);
            return msgConvertida(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }

    public void enviarDados(String mensagem, OutputStream os){
        try {
            os.write(mensagem.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String msgConvertida(byte[] buffer) {
        int cont = 0;
        String mensagem = "";
        while (buffer[cont] != 0) {
            mensagem += Character.toString((char) buffer[cont++]);
        }
        return mensagem;
    }

}


