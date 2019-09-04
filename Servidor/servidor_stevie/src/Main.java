import java.io.*;
import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.ContentConnection;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.obex.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import database.connection.model.bean.Departamento;
import database.connection.model.bean.Laboratorio;
import database.connection.model.bean.Ponto_Interesse;
import database.connection.model.bean.Tag;
import database.connection.model.dao.DepartamentoDAO;
import database.connection.model.dao.LaboratorioDAO;
import database.connection.model.dao.Ponto_InteresseDAO;
import database.connection.model.dao.TagDAO;
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

//        BFS bfs = new BFS(tags);
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

    @Override
    public void run() {
        waitForConnection();
    }

    private void waitForConnection() {
        LocalDevice local = null;

        StreamConnectionNotifier notifier;

        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

//            String url = "btgoep://localhost:" + serverUUID + ";name=Stevie";
            UUID uuid = new UUID(80087355); // "04c6093b-0000-1000-8000-00805f9b34fb"
            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            StreamConnection connection = notifier.acceptAndOpen();
            InputStream inputStream = connection.openInputStream();
            OutputStream outputStream = connection.openOutputStream();
            while (true) {
                System.out.println("waiting for connection");
                System.out.println("Conexao aberta");
//          // prepare to receive data
                try {
                    String st = "";
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                    st = (String) objectInputStream.readObject();
                    System.out.println(st);
                    switch (st) {
                        case "1":
                            write("DISGRAÇA", outputStream);
                            break;
                        case "2":
                            break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void write(String string, OutputStream outputStream) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(string);
            System.out.println("Enviei!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



