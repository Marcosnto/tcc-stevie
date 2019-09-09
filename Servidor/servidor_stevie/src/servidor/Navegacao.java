package servidor;
import busca.No;

import java.util.List;

public class Navegacao implements Runnable {



    public Navegacao(No caminhos) {

    }

    @Override
    public void run() {

    }
}

// startar a thread para ouvir as modificações
//        OuvirArquivo ouvirArquivo = new OuvirArquivo();
//        Thread ouvirArq = new Thread(ouvirArquivo);
//        ouvirArq.start();


/*Fazer a verificação em tempo real do arquivo, comparando-o o que foi lido
 * com o nó atual, o anterior e o próximo, sendo diferente, verifico se é
 * um objeto qlqr, se n for um objeto, recalculo o caminho e envio
 */