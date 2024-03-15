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

    public ViewPessoa(ServicePessoa<PessoaFisica> servPessoaPf,
                      ServicePessoa<PessoaJuridica> servicePessoaPj,
                      EntradaVerificada entrada){
        this.servPJ = servicePessoaPj;
        this.servPF = servPessoaPf;
        this.entrada = entrada;
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

    public void listar(){
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
                PessoaFisica pessoa = new PessoaFisica();

                System.out.print("Digite o Cpf da pessoa: ");
                pessoa.setIdentificao(this.entrada.receberLong());

                System.out.println("Digite o nome da pessoa: ");
                pessoa.setNome(this.entrada.receberString());

                System.out.println("Digite o Telefone: ");
                pessoa.setTelefone(this.entrada.receberString());

                System.out.println("Endereço : ");
                pessoa.setEndereco(this.entrada.receberString());
                if(getPessoa(pessoa.getIdentificao()) != null)
                    throw new RuntimeException("Cliente ja cadastrado: ");
                this.servPF.cadastrar(pessoa);
            }
            else if (opcao_pessoa == 2){
                PessoaJuridica pessoa = new PessoaJuridica();

                System.out.print("Digite o Cnpj da empresa: ");
                pessoa.setIdentificao(this.entrada.receberLong());

                System.out.println("Digite a Razão social: ");
                pessoa.setNome(this.entrada.receberString());

                System.out.println("Digite o Telefone: ");
                pessoa.setTelefone(this.entrada.receberString());

                System.out.println("Endereço : ");
                pessoa.setEndereco(this.entrada.receberString());
                if (getPessoa(pessoa.getIdentificao()) != null)
                    throw new RuntimeException("cliente ja cadastrado");
                this.servPJ.cadastrar(pessoa);
            }
       }catch (Exception e){
            System.out.println("NÃO FOI POSSIVEL CADASTRAR O CLIENTE");
        }
    }

    // para melhorar e remover bugs
    private void alterar(){
        try{
            listar();
            System.out.println("digite o cpf ou o cnpj da pessoa que deseja alterar: ");
            Integer opt = entrada.receberInteger();
            if (opt == 1){
                System.out.println("digite o cpf da pessoa que deseja alterar: ");
                Long cpf = this.entrada.receberLong();
                PessoaFisica pessoa = this.service.buscarPorId(cpf);
                PessoaFisica newPessoa = new PessoaFisica();
                System.out.println("Nome: "+pessoa.getNome());
                System.out.print("Vc deseja Mudar o nome, se sim(1) ou não(2): ");
                Integer opcao = this.entrada.receberInteger();
                if (opcao == 1){
                    System.out.print("Digite o novo nome: ");
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
            } else if (opt == 2) {
                System.out.println("digite o cnpj da pessoa que deseja alterar: ");
                Long cnpj = this.entrada.receberLong();
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
        }catch (Exception e){
            System.out.println("Ocorreu um erro não foi possível alterar o cliente");
        }
    }

    public Pessoa getPessoa(Long identificao){
        List<PessoaJuridica> pessoaJuridicas = this.servPJ.listar();
        List<PessoaFisica> pessoaFisicas = this.servPF.listar();

        for (PessoaJuridica p : pessoaJuridicas)
            if (p.getIdentificao().equals(identificao))
                return p;

        for (PessoaFisica p: pessoaFisicas)
            if (p.getIdentificao().equals(identificao))
                return p;
        return null;
    }
}
