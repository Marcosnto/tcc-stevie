package database.connection.model.bean;

public class Ponto_Interesse {
    private int id_ponto_interesse;
    private int id_regiao;
    private int id_tag;
    private int id_departamento;
    private int id_laboratorio;
    private String nome;
    private String descricao;

    public int getId_ponto_interesse() {
        return id_ponto_interesse;
    }

    public void setId_ponto_interesse(int id_ponto_interesse) {
        this.id_ponto_interesse = id_ponto_interesse;
    }

    public int getId_regiao() {
        return id_regiao;
    }

    public void setId_regiao(int id_regiao) {
        this.id_regiao = id_regiao;
    }

    public int getId_tag() {
        return id_tag;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public int getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(int id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
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
}
