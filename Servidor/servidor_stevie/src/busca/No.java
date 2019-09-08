package busca;

public class No {
    private Node no;
    private Node anterior;
    private Node proximo;

    public No(Node no, Node anterior, Node proximo) {
        this.no = no;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public Node getNo() {
        return no;
    }

    public void setNo(Node no) {
        this.no = no;
    }

    public Node getAnterior() {
        return anterior;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
