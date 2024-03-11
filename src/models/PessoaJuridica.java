package models;

import java.util.Objects;

public class PessoaJuridica extends Pessoa {
    private Long cnpj;

    public PessoaJuridica(String nome, String telefone, String endereco, Long identificacao){
        super(nome,telefone,endereco);
        this.cnpj = identificacao;
    }

    public PessoaJuridica() {
        super();
    }

    @Override
    public Long getIdentificao() {
        return this.cnpj;
    }

    @Override
    public void setIdentificao(Long identificao) {
        this.cnpj = identificao;
    }

    @Override
    public boolean equals(Object o){
        if (o.getClass() == this.getClass()){
            PessoaJuridica pessoa = (PessoaJuridica) o;
            if (Objects.equals(pessoa.getIdentificao() , this.getIdentificao())){
                return true;
            }
        }
        return false;
    }
}
