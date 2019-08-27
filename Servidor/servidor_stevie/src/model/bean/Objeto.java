package model.bean;

public class Objeto {
    private int id_objeto;
    private int id_tag;
    private int id_local;
    private String nome_objeto;
    private String descricao_objeto;

    public int getId_objeto() {
        return id_objeto;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
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
