package models;

import java.util.Objects;

public class PessoaFisica extends Pessoa {
    private Long cpf;

    public PessoaFisica(String nome, String telefone, Long cpf, String endereco){
        super(nome,telefone,endereco);
        this.cpf = cpf;
    }

    public PessoaFisica() {

    }


    @Override
    public Long getIdentificao() {
        return this.cpf;
    }

    @Override
    public void setIdentificao(Long identificao) {
        this.cpf = identificao;
    }

    @Override
    public boolean equals(Object o){
        if (o.getClass() == this.getClass()){
            PessoaFisica pessoa = (PessoaFisica) o;
            return Objects.equals(pessoa.getIdentificao(), this.getIdentificao());
        }
        return false;
    }
}
