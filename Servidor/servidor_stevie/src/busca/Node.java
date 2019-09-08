package busca;

public class Node {
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