import database.connection.model.bean.Tag;

import java.util.*;

public class BFS {

    List<Tag> tags;
    List<Node> nodes = new ArrayList<>();

    public BFS(List<Tag> tags) {
        this.tags = tags;
        preencherLista();
        preencherConexoes();
        Node noResultado = buscaBFS(buscarTag("E"),buscarTag("H"));

        while(noResultado.getPai() != null){
            System.out.println(noResultado.getPai().getId());
            noResultado = noResultado.getPai();
        }
    }

    public Node buscarTag(String codTag) {
        if (codTag != null) {
            for (int i = 0; i < nodes.size(); i++) {
                if (codTag.equals(nodes.get(i).getId())) {
                    return nodes.get(i);
                }
            }
        }
        return null;
    }

    public Node buscaBFS(Node noInicial, Node noFinal) {

        Queue<Node> fila = new LinkedList<Node>();
        Node noAtual = noInicial;

        fila.add(noAtual);
        while(!fila.isEmpty()){

            noAtual = fila.poll();

            if (noAtual == noFinal) return noAtual;


            if (noAtual.isVisitado() == false) {
                //pegando os filhos
                if(noAtual.getFrente()!= null && noAtual.getFrente() != noAtual.getPai()) {
                    noAtual.getFrente().setPai(noAtual);
                    fila.add(noAtual.getFrente());
                }
                if(noAtual.getTras()!= null && noAtual.getTras() != noAtual.getPai()) {
                    noAtual.getTras().setPai(noAtual);
                    fila.add(noAtual.getTras());
                }
                if(noAtual.getEsquerda()!= null&& noAtual.getEsquerda() != noAtual.getPai()) {
                    noAtual.getEsquerda().setPai(noAtual);
                    fila.add(noAtual.getEsquerda());
                }
                if(noAtual.getDireita()!= null && noAtual.getDireita() != noAtual.getPai()) {
                    noAtual.getDireita().setPai(noAtual);
                    fila.add(noAtual.getDireita());
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
            nodes.get(i).setFrente(buscarTag(tags.get(i).getId_tag_frente()));
            nodes.get(i).setTras(buscarTag(tags.get(i).getId_tag_tras()));
            nodes.get(i).setEsquerda(buscarTag(tags.get(i).getId_tag_esquerda()));
            nodes.get(i).setDireita(buscarTag(tags.get(i).getId_tag_direita()));
        }
    }

}

class Node {
    private String id;
    private Node pai;
    private Node frente;
    private Node tras;
    private Node esquerda;
    private Node direita;
    private boolean objetivo;
    private boolean visitado;

    public Node(String id) {
        this.id = id;
    }

    public Node() {
        this.pai = null;
        this.frente = null;
        this.tras = null;
        this.esquerda = null;
        this.direita = null;
        this.objetivo = false;
        this.visitado = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public Node getFrente() {
        return frente;
    }

    public void setFrente(Node frente) {
        this.frente = frente;
    }

    public Node getTras() {
        return tras;
    }

    public void setTras(Node tras) {
        this.tras = tras;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public boolean isFinal() {
        return objetivo;
    }

    public void setFinal(boolean aFinal) {
        objetivo = aFinal;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}



