import java.io.*;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.obex.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static void main(String[] args) {
        // carrega todas os dados do banco
        carregarTags();
        carregarDepartamentos();
        carregarLaboratorios();
        carregarPontosInteresse();

        // startar a thread para ouvir as modificações
//        OuvirArquivo ouvirArquivo = new OuvirArquivo();
//        Thread ouvirArq = new Thread(ouvirArquivo);
//        ouvirArq.start();

//         iniciando o servidor bluetooth
        ConexaoBluetooth bluetooth = new ConexaoBluetooth();
        Thread conexao = new Thread(bluetooth);
        conexao.start();

        BFS bfs = new BFS(tags);
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

class ConexaoBluetooth implements Runnable {
    //definição do Identificador universal unico (UUID)
    //não é aceito se houver os ifens (-)
    static final String serverUUID = "11111111111111111111111111111123";
    private OutputStream mmOutStream;

    @Override
    public void run() {
        //ficará ouvindo até conectar-se a um dispositivo
        try {
            LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }

        SessionNotifier serverConnection = null;
        try {
            serverConnection = (SessionNotifier) Connector.open("btgoep://localhost:" +
                    serverUUID + ";name=ObexExample");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        while (count < 2) {
            RequestHandler handler = new RequestHandler();
            try {
                serverConnection.acceptAndOpen(handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Received OBEX database.connection " + (++count));
        }
    }

    public static class RequestHandler extends ServerRequestHandler {
        public int onPut(Operation op) {
            try {
                HeaderSet hs = op.getReceivedHeaders();
                String name = (String) hs.getHeader(HeaderSet.NAME);
                if (name != null) {
                    System.out.println("put name: " + name);
                }

                InputStream is = op.openInputStream();
                //buffer de armazenamento do dado
                StringBuffer buf = new StringBuffer();
                int data;
                while ((data = is.read()) != -1) {
                    buf.append((char) data);
                }
                System.out.println("got:" + buf.toString());
                op.close();
                return ResponseCodes.OBEX_HTTP_OK;
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseCodes.OBEX_HTTP_UNAVAILABLE;
            }
        }
    }

    public void write(byte[] dados){
        try{
            mmOutStream.write(dados);
            

        }catch (IOException e){
            System.out.println("Erro ao enviar para o cliente: "+ e);
        }
    }
}


