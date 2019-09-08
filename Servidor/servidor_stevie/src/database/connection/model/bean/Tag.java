package database.connection.model.bean;

public class Tag {
    private int id_tag;
    private String cod_tag;
    private String id_tag_norte;
    private String id_tag_sul;
    private String id_tag_leste;
    private String id_tag_oeste;
    private String nome;
    private String descricao;
    private String regiao;
    private String tipo_tag;

    public int getId_tag() {
        return id_tag;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public String getCod_tag() {
        return cod_tag;
    }

    public void setCod_tag(String cod_tag) {
        this.cod_tag = cod_tag;
    }

    public String getId_tag_norte() {
        return id_tag_norte;
    }

    public void setId_tag_norte(String id_tag_norte) {
        this.id_tag_norte = id_tag_norte;
    }

    public String getId_tag_sul() {
        return id_tag_sul;
    }

    public void setId_tag_sul(String id_tag_sul) {
        this.id_tag_sul = id_tag_sul;
    }

    public String getId_tag_leste() {
        return id_tag_leste;
    }

    public void setId_tag_leste(String id_tag_leste) {
        this.id_tag_leste = id_tag_leste;
    }

    public String getId_tag_oeste() {
        return id_tag_oeste;
    }

    public void setId_tag_oeste(String id_tag_oeste) {
        this.id_tag_oeste = id_tag_oeste;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getTipo_tag() {
        return tipo_tag;
    }

    public void setTipo_tag(String tipo_tag) {
        this.tipo_tag = tipo_tag;
    }
}
