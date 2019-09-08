package busca;

import database.connection.model.bean.Tag;

import java.util.*;

public class BFS {

    List<Tag> tags;
    List<Node> nodes = new ArrayList<>();

    public BFS(List<Tag> tags) {
        this.tags = tags;
        preencherLista();
        preencherConexoes();
//        busca.Node atual = buscaBFS(buscarTag("A2"),buscarTag("C3"));
    }

    public List<No> listaResultado(Node atual){
        List<No> no = new ArrayList<>();
        Node proximo = null;
        while(atual != null){
            System.out.println("busca.No atual: " + atual.getTag());
            no.add(new No (atual, atual.getPai(), proximo));
            proximo = atual;
            atual = atual.getPai();
        }
        Collections.reverse(no);
        return no;
    }

    public Node buscarTag(String codTag) {
        if (codTag != null) {
            for (int i = 0; i < nodes.size(); i++) {
                if (codTag.equals(nodes.get(i).getTag())) {
                    return nodes.get(i);
                }
            }
        }
        return null;
    }

    public Node buscaBFS(Node noInicial, Node noFinal) {
        //limpando os nós de Pai e Visitado para uma nova busca
        limparLista();

        Queue<Node> fila = new LinkedList<Node>();
        Node noAtual = noInicial;

        fila.add(noAtual);
        while(!fila.isEmpty()){

            noAtual = fila.poll();

            if (noAtual == noFinal) return noAtual;


            if (noAtual.isVisitado() == false) {
                //pegando os filhos
                if(noAtual.getNorte()!= null && noAtual.getNorte() != noAtual.getPai()) {
                    noAtual.getNorte().setPai(noAtual);
                    fila.add(noAtual.getNorte());
                }
                if(noAtual.getSul()!= null && noAtual.getSul() != noAtual.getPai()) {
                    noAtual.getSul().setPai(noAtual);
                    fila.add(noAtual.getSul());
                }
                if(noAtual.getLeste()!= null&& noAtual.getLeste() != noAtual.getPai()) {
                    noAtual.getLeste().setPai(noAtual);
                    fila.add(noAtual.getLeste());
                }
                if(noAtual.getOeste()!= null && noAtual.getOeste() != noAtual.getPai()) {
                    noAtual.getOeste().setPai(noAtual);
                    fila.add(noAtual.getOeste());
                }
            }
            noAtual.setVisitado(true);
        }
        return noAtual;
    }

    public void preencherLista() {
        for (int i = 0; i < tags.size(); i++) {
            nodes.add(new Node(tags.get(i).getCod_tag()));
        }
    }

    public void preencherConexoes() {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setNorte(buscarTag(tags.get(i).getId_tag_norte()));
            nodes.get(i).setSul(buscarTag(tags.get(i).getId_tag_sul()));
            nodes.get(i).setLeste(buscarTag(tags.get(i).getId_tag_leste()));
            nodes.get(i).setOeste(buscarTag(tags.get(i).getId_tag_oeste()));
        }
    }

    public void limparLista(){
        for (int i = 0; i < nodes.size() ; i++) {
            nodes.get(i).setPai(null);
            nodes.get(i).setVisitado(false);
        }
    }
}

class Node {
    private String tag;
    private Node pai;
    private Node norte;
    private Node sul;
    private Node leste;
    private Node oeste;
    private boolean visitado;

    public Node(String tag) {
        this.tag = tag;
    }

    public Node() {
        this.pai = null;
        this.norte = null;
        this.sul = null;
        this.leste = null;
        this.oeste = null;
        this.visitado = false;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public Node getNorte() {
        return norte;
    }

    public void setNorte(Node norte) {
        this.norte = norte;
    }

    public Node getSul() {
        return sul;
    }

    public void setSul(Node sul) {
        this.sul = sul;
    }

    public Node getLeste() {
        return leste;
    }

    public void setLeste(Node leste) {
        this.leste = leste;
    }

    public Node getOeste() {
        return oeste;
    }

    public void setOeste(Node oeste) {
        this.oeste = oeste;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}

class No {
    Node no;
    Node anterior;
    Node proximo;

    public No(Node no, Node anterior, Node proximo) {
        this.no = no;
        this.anterior = anterior;
        this.proximo = proximo;
    }
}



