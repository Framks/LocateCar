package view;

import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Veiculo;
import services.ServiceAluguel;
import services.ServicePessoa;
import services.ServiceVeiculo;
import view.Aluguel.ViewAluguel;
import view.Pessoa.ViewPessoa;
import view.Veiculo.ViewVeiculo;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Menu {
    private final ServiceVeiculo servVeiPeq;
    private final ServiceVeiculo servVeiMed;
    private final ServiceVeiculo servVeiSuv;
    private final ServicePessoa servPF;
    private final ServicePessoa servPJ;
    private final EntradaVerificada entrada;
    private final ServiceAluguel servAlu;
    private final ViewPessoa viewPessoa;
    private final ViewAluguel viewAluguel;

    private final ViewVeiculo viewVeiculo;

    public Menu(ServiceVeiculo<? extends Veiculo> servVeiPeq,
                ServiceVeiculo<? extends Veiculo> servVeiMed,
                ServiceVeiculo<? extends Veiculo> servVeiSuv,
                ServicePessoa<PessoaFisica> servPF,
                ServicePessoa<PessoaJuridica> servPJ,
                ServiceAluguel servAlu){
        this.servVeiPeq = servVeiPeq;
        this.servVeiMed = servVeiMed;
        this.servVeiSuv = servVeiSuv;
        this.servPF = servPF;
        this.servPJ = servPJ;
        this.entrada = new EntradaVerificada(new Scanner(System.in));
        this.servAlu = servAlu;
        this.viewVeiculo = new ViewVeiculo(servVeiPeq,servVeiMed,servVeiSuv,entrada);
        this.viewPessoa = new ViewPessoa(servPF,servPJ,entrada);
        this.viewAluguel = new ViewAluguel(servAlu,entrada,viewPessoa,viewVeiculo);
    }

    public void run(){
        Integer option;
        boolean execucao = true;
        while (execucao){
            System.out.print("##  Bem VINDO a LOCATECAR ##\n" +
                               "            Menu:\n" +
                               "    0 - Sair: \n" +
                               "    1 - Alugueis: \n" +
                               "    2 - Clientes: \n" +
                               "    3 - Veiculos: \n" +
                               "Digite a opção para entrar: ");
            option = this.entrada.receberInteger();
            switch (option){
                case 0-> execucao = false;
                case 1 -> this.viewAluguel.run();
                case 2 -> this.viewPessoa.run();
                case 3 -> this.viewVeiculo.run();
                default -> System.out.println("entrada não correspondente a opções");
            }
        }
        entrada.scanClose();
    }

}
