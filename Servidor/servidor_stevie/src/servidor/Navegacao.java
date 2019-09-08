//package servidor;
//
//import javax.xml.soap.Node;
//import java.util.List;
//import java.util.Queue;
//
//public class Navegacao implements Runnable{
//
//    List<Node> caminhos;
//    Node caminhoAtual;
//    Node caminhoAnterior;
//
//    public Navegacao(List<Node> caminhos) {
//        this.caminhos = caminhos;
//    }
//
//    @Override
//    public void run() {
//        if (caminhos.peek() != null) {
//            caminhoAtual = caminhos.peek();
//            caminhoAnterior = caminhoAtual;
//            while(caminhoAtual != null){
//                //ler o arquivo e pegar a tag lida
//                if(){
//
//                }
//                if(){
//
//                }
//                if(){
//
//                }
//                caminhoAnterior = caminhoAtual;
//                caminho.pe
//            }
//        }
//    }
//}

// startar a thread para ouvir as modificações
//        OuvirArquivo ouvirArquivo = new OuvirArquivo();
//        Thread ouvirArq = new Thread(ouvirArquivo);
//        ouvirArq.start();


/*Fazer a verificação em tempo real do arquivo, comparando-o o que foi lido
* com o nó atual, o anterior e o próximo, sendo diferente, verifico se é
* um objeto qlqr, se n for um objeto, recalculo o caminho e envio
*/