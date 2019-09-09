package busca;

public class No {
    private Node node;
    private No proximo;

    public No(Node node, No proximo) {
        this.node = node;
        this.proximo = proximo;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
