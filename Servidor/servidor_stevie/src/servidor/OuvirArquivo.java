package servidor;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OuvirArquivo implements Runnable {
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
//                    System.out.println("Arquivo criado: " + name);
                }

                public void fileModified(int wd, String rootPath, String name) {
                    System.out.println("Arquivo modificado: " + name);
                    String ultimaTag = lerArq();
                    System.out.println(ultimaTag);
                }
            });
            Thread.sleep(1000);
        } catch (JNotifyException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String lerArq() {
        String code = "";
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