package model.bean;

public class Local {
    private int id_local;
    private int id_tag;
    private int id_grupo;
    private String nome_local;
    private String prefixo_local;

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_tag() {
        return id_tag;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNome_local() {
        return nome_local;
    }

    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
    }

    public String getPrefixo_local() {
        return prefixo_local;
    }

    public void setPrefixo_local(String prefixo_local) {
        this.prefixo_local = prefixo_local;
    }
}
