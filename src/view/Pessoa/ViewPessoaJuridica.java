package view.Pessoa;

import models.PessoaJuridica;
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
        PessoaJuridica pessoa = this.service.buscarPorId(cnpj);
        PessoaJuridica newPessoa = new PessoaJuridica();
        System.out.println("Razão social: "+pessoa.getNome());

        System.out.print("Vc deseja Mudar a razão social, se sim(1) ou não(2): ");
        Integer opcao = this.entrada.receberInteger();
        if (opcao == 1){
            System.out.print("Digite a razão social: ");
            newPessoa.setNome(this.entrada.receberString());
        }

        System.out.print("Vc deseja Mudar o telefone, se sim(1) ou não(2): ");
        opcao = this.entrada.receberInteger();
        if (opcao == 1){
            System.out.print("Digite o novo telefone: ");
            newPessoa.setTelefone(this.entrada.receberString());
        }

        System.out.print("Vc deseja Mudar o endereço, se sim(1) ou não(2): ");
        opcao = this.entrada.receberInteger();
        if (opcao == 1){
            System.out.print("Digite o novo endereço: ");
            newPessoa.setEndereco(this.entrada.receberString());
        }

        newPessoa.setIdentificao(pessoa.getIdentificao());
        this.service.alterar(newPessoa);
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
