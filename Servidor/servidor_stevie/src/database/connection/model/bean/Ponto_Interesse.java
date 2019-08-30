package database.connection.model.bean;

public class Ponto_Interesse {
    private int id_ponto_interesse;
    private int id_tag;
    private int id_local;
    private String nome_objeto;
    private String descricao_objeto;

    public int getId_ponto_interesse() {
        return id_ponto_interesse;
    }

    public void setId_ponto_interesse(int id_ponto_interesse) {
        this.id_ponto_interesse = id_ponto_interesse;
    }

    public int getId_tag() {
        return id_tag;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getNome_objeto() {
        return nome_objeto;
    }

    public void setNome_objeto(String nome_objeto) {
        this.nome_objeto = nome_objeto;
    }

    public String getDescricao_objeto() {
        return descricao_objeto;
    }

    public void setDescricao_objeto(String descricao_objeto) {
        this.descricao_objeto = descricao_objeto;
    }
}
