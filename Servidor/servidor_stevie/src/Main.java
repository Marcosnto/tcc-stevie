import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import busca.BFS;
import database.connection.model.bean.Tag;
import database.connection.model.dao.TagDAO;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;
import servidor.Servidor;

public class Main {
    static ArrayList<Tag> tags_banco = new ArrayList<>();
    static List<Tag> tags = new ArrayList<>();
    static Tag tag = new Tag();
    static TagDAO dao = new TagDAO();
    Path path = Paths.get("C:\\stevie");

    public static void main(String[] args) {
        // carrega todas as tags do banco
        carregarTags();
        Thread servidor = new Thread(new Servidor(new BFS(tags)));
        servidor.start();

    }


    public static void carregarTags() {
        for (Tag t : dao.read()) {
            tags.add(t);
            //tags_banco.add(t);
        }
    }


    static class OuvirArquivo implements Runnable {
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
}