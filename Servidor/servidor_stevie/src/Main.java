import java.io.*;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.obex.*;
import java.io.IOException;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;

public class Main {

    public static void main(String[] args) {
        //startar a thread para ouvir as modificações
        OuvirArquivo ouvirArquivo = new OuvirArquivo();
        Thread ouvirArq = new Thread(ouvirArquivo);
        ouvirArq.start();


//        iniciando o servidor bluetooth
        ConexaoBluetooth bluetooth = new ConexaoBluetooth();
        Thread conexao = new Thread(bluetooth);
        conexao.start();

    }

    public static void novoArq(File arquivo) throws IOException {
        arquivo.delete();
        FileWriter novoArq = new FileWriter("C:\\Users\\Marcos\\Documents\\Leitura.txt");
        System.out.printf("Novo arquivo criado!");

        return;
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
    static final String serverUUID = "11111111111111111111111111111123";

    @Override
    public void run() {

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
            System.out.println("Received OBEX connection " + (++count));
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
}




