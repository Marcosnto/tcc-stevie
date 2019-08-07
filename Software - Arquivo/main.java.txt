import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        int posicao = 0;

        String arq = "C:\\Users\\Marcos\\Documents\\Leitura.txt";

        File arqLeitura = new File(arq);

        if(arqLeitura.exists()){
            novoArq(arqLeitura);

        }else {
            System.out.println("O arquivo não existe no diretório informado.");
        }

    }

    public static void novoArq(File arquivo) throws IOException{
        arquivo.delete();
        FileWriter novoArq = new FileWriter("C:\\Users\\Marcos\\Documents\\Leitura.txt");
        System.out.printf("Novo arquivo criado!");

        return;
    }

}