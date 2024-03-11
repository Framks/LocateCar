package view.Pessoa;

import models.PessoaJuridica;
import services.ServicePessoa;
import view.EntradaVerificada;

public class ViewPessoaJuridica {

    private final ServicePessoa<PessoaJuridica> service;
    private final EntradaVerificada entrada;

    public ViewPessoaJuridica(ServicePessoa<PessoaJuridica> service, EntradaVerificada entrada){
        this.service = service;
        this.entrada = entrada;
    }

    public void alterar(Long cnpj){

    }

    public void cadastrar(){
        PessoaJuridica pessoa = new PessoaJuridica();
        System.out.print("Digite o Cnpj da empresa: ");
        pessoa.setIdentificao(this.entrada.receberLong());
        System.out.println("Digite a Razão social: ");
        pessoa.setNome(this.entrada.receberString());
        System.out.println("Digite o Telefone: ");
        pessoa.setTelefone(this.entrada.receberString());
        System.out.println("Endereço : ");
        pessoa.setEndereco(this.entrada.receberString());

        this.service.cadastrar(pessoa);
    }
}
