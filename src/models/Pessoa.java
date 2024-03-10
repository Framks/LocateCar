package models;
public abstract class Pessoa {
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

    public Pessoa(String nome, String telefone, String endereco){
        this.endereco = endereco;
        this.telefone = telefone;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract Long getIdentificao();
    public abstract void setIdentificao(Long identificao);
    public String getNome(){
        return this.nome;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


}
