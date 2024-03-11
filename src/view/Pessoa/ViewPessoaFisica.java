package view.Pessoa;

import models.PessoaFisica;
import services.ServicePessoa;
import view.EntradaVerificada;

import java.util.Objects;

public class ViewPessoaFisica{

    private final ServicePessoa<PessoaFisica> service;
    private final EntradaVerificada entrada;

    public ViewPessoaFisica(ServicePessoa<PessoaFisica> service, EntradaVerificada entrada){
        this.service = service;
        this.entrada = entrada;
    }

    public void alterar(Long cpf){

        PessoaFisica pessoa = this.service.buscarPorId(cpf);
        System.out.println("Nome: "+pessoa.getNome());
        System.out.println("Digite novo nome: ");

    }

    public void cadastrar(){
        PessoaFisica pessoa = new PessoaFisica();
        System.out.print("Digite o Cpf da pessoa: ");
        pessoa.setIdentificao(this.entrada.receberLong());
        System.out.println("Digite o nome da pessoa: ");
        pessoa.setNome(this.entrada.receberString());
        System.out.println("Digite o Telefone: ");
        pessoa.setTelefone(this.entrada.receberString());
        System.out.println("Endereço : ");
        pessoa.setEndereco(this.entrada.receberString());

        this.service.cadastrar(pessoa);
    }
}
