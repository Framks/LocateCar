package view.Pessoa;

import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import services.ServicePessoa;

import view.EntradaVerificada;

import javax.sound.midi.Soundbank;
import java.util.List;

public class ViewPessoa {

    private final ServicePessoa<PessoaJuridica> servPJ;
    private final ServicePessoa<PessoaFisica> servPF;
    private final EntradaVerificada entrada;
    private final ViewPessoaFisica viewPF;
    private final ViewPessoaJuridica viewPJ;

    public ViewPessoa(ServicePessoa<PessoaFisica> servPessoaPf,
                      ServicePessoa<PessoaJuridica> servicePessoaPj,
                      EntradaVerificada entrada){
        this.servPJ = servicePessoaPj;
        this.servPF = servPessoaPf;
        this.entrada = entrada;
        this.viewPF = new ViewPessoaFisica(servPessoaPf,entrada);
        this.viewPJ = new ViewPessoaJuridica( servicePessoaPj,entrada);
    }

    public void run(){
        Integer option;
        boolean execucao = true;
        while (execucao){
            System.out.print("##    Clientes    ##\n" +
                            "    0 - Sair: \n" +
                            "    1 - Cadastrar: \n" +
                            "    2 - Alterar: \n" +
                            "    3 - Buscar por nome: \n" +
                            "    4 - Listar Todos: \n"+
                    "Digite a opção para entrar: ");
            option = this.entrada.receberInteger();
            switch (option){
                case 0-> execucao = false;
                case 1 -> cadastrar();
                case 2 -> alterar();
                case 3 -> buscarPorNome();
                case 4 -> listar();
                default -> System.out.println("entrada não correspondente a opções");
            }
        }
    }

    private void buscarPorNome(){
        try {
            System.out.println("Digite o nome ou parte dele: ");
            String nome = this.entrada.receberString();
            Pessoa pessoa = this.servPF.buscarPorNome(nome);
            if (pessoa == null) {
                pessoa = this.servPJ.buscarPorNome(nome);
            }
            if (pessoa == null){
                System.out.println("Não existe pessoa com esse nome: "+nome);
            }else{
                String print = "";
                if (pessoa instanceof PessoaFisica){
                    print += "CPF: ";
                }else {
                    print += "CNPJ: ";
                }
                System.out.println(print+pessoa.getIdentificao()+", Nome: "+pessoa.getNome()+", Telefone: "+pessoa.getTelefone()+", Endereço: "+pessoa.getEndereco());
            }
        }catch (Exception e){
            System.out.println("Não foi possível Buscar");
        }
    }

    private void listar(){
        try{
            List<PessoaJuridica> pessoasjuridicas = this.servPJ.listar();
            System.out.println("###     Pessoas Jurídica    ###");
            System.out.println("   CNPJ   |    Nome     |    Telefone   |     Endereço  ");
            for (PessoaJuridica pj : pessoasjuridicas){
                System.out.println(pj.getIdentificao() + "  |  "+pj.getNome()+"  |  "+pj.getTelefone()+"  |  "+pj.getEndereco()
                );
            }
            System.out.println("\n");
            System.out.println("###     Pessoas Físicas     ###");
            List<PessoaFisica> pessoasfisicas = this.servPF.listar();
            System.out.println("   CPF   |    Nome     |    Telefone   |     Endereço  ");
            for (PessoaFisica pessoa : pessoasfisicas){
                System.out.println(pessoa.getIdentificao() + "  |  "+pessoa.getNome()+"  |  "+pessoa.getTelefone()+"  |  "+pessoa.getEndereco()
                );
            }
        }catch (Exception e){
            System.out.println("Não foi possível listar os Clientes");
        }
    }

    private void cadastrar(){
        try {
            System.out.print("para cadastrar o Cliente diga se ele é Fisico(1) ou Juridico(2): ");
            Integer opcao_pessoa = this.entrada.receberInteger();
            if (opcao_pessoa == 1){
                this.viewPF.cadastrar();
            }else if (opcao_pessoa == 2){
                this.viewPJ.cadastrar();
            }
       }catch (Exception e){
            System.out.println("NÃO FOI POSSIVEL CADASTRAR O CLIENTE");
        }
    }

    private void alterar(){
        try{
            listar();
            System.out.println("digite 1 para alterar uma pessoa fisica e 2 para pessoa Juridica: ");
            Integer opt = entrada.receberInteger();
            if (opt == 1){
                System.out.println("digite o cpf da pessoa que deseja alterar: ");
                Long cpf = this.entrada.receberLong();
                this.viewPF.alterar(cpf);
            } else if (opt == 2) {
                System.out.println("digite o cnpj da pessoa que deseja alterar: ");
                Long cnpj = this.entrada.receberLong();
                this.viewPJ.alterar(cnpj);
            }
        }catch (Exception e){
            System.out.println("Ocorreu um erro não foi possível alterar o cliente");
        }
    }
}
