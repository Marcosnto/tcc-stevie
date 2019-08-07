import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;

public class Main {

//    private static Logger logger = Logger.getLogger(Main.class);


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

    public static void main(String[] args) throws IOException {
        //variável que será usada para setar o o arquivo na posição de leitura
//        int posicao = 0;
//
//        String arq = "C:\\Users\\Marcos\\Documents\\stevie_leitura\\Leitura.txt";
//
//        File arqLeitura = new File(arq);
//
//        if(arqLeitura.exists()){
//            novoArq(arqLeitura);
//        }else {
//            System.out.println("O arquivo não existe no diretório informado.");
//        }

        try{
                int watchID = JNotify.addWatch(PATH_TO_LISTEN, mask, ouvirSubDiretorios, new JNotifyAdapter(){
//                    @Override
//                    public void fileCreated(int wd, String rootPath,
//                                            String name){
//                        System.out.println("Arquivo criado: "+name);
//                    }
//
//                    @Override
//                    public void fileRenamed(int wd, String rootPath,
//                            String oldName, String newName) {
//                        System.out.println("Arquivo renomeado de: "+oldName+" para: "+newName);
//                    }
//
//                    @Override
//                    public void fileDeleted(int wd, String rootPath, String name){
//                        System.out.println("Arquivo deletado: "+name);
//                    }

                    @Override
                    public void fileModified(int wd, String rootPath, String name){
                        System.out.println("Arquivo modificado: "+name);

                    }
                });
                Thread.sleep(1000000);
            } catch (JNotifyException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }

    public static void novoArq(File arquivo) throws IOException{
        arquivo.delete();
        FileWriter novoArq = new FileWriter("C:\\Users\\Marcos\\Documents\\Leitura.txt");
        System.out.printf("Novo arquivo criado!");

        return;
    }

}
