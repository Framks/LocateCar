package view.Aluguel;

import models.Aluguel;
import models.Pessoa;
import models.Veiculo;
import services.ServiceAluguel;
import view.EntradaVerificada;
import view.Pessoa.ViewPessoa;
import view.Veiculo.ViewVeiculo;

import java.util.List;


public class ViewAluguel {

    private final ServiceAluguel serviceAluguel;
    private final EntradaVerificada entrada;

    private final ViewVeiculo viewVeiculo;

    private final ViewPessoa viewPessoa;

    public ViewAluguel(ServiceAluguel serviceAluguel, EntradaVerificada entrada, ViewPessoa viewPessoa, ViewVeiculo viewVeiculo){
        this.serviceAluguel= serviceAluguel;
        this.entrada = entrada;
        this.viewVeiculo = viewVeiculo;
        this.viewPessoa= viewPessoa;
    }
    public void run(){
        boolean execucao = true;
        while (execucao){
            System.out.print("""
                    ##    Aluguéis     ##
                        0 - Sair:\s
                        1 - Alugar Veiculo:\s
                        2 - Devolver Veiculo:\s
                        3 - Listar Todos Alugueis:\s
                    Digite a opção para entrar: \s""");
            Integer option = this.entrada.receberInteger();
            switch (option){
                case 0-> execucao = false;
                case 1 -> alugar();
                case 2 -> devolver();
                case 3 -> listar();
                default -> System.out.println("entrada não correspondente a opções");
            }
        }
    }

    private void alugar(){
        try {
            this.viewVeiculo.listar();
            System.out.print("Digite a placa do carro: ");
            Veiculo veiculo = this.viewVeiculo.getVeiculo(this.entrada.receberString());

            this.viewPessoa.listar();
            System.out.println("digite o cpf ou cnpj que deseja alugar o carro: ");
            Pessoa pessoa = this.viewPessoa.getPessoa(this.entrada.receberString());

            System.out.println("Qual a data de Aluguel: ");
            Aluguel aluguel = new Aluguel(this.entrada.receberLocalData(), veiculo, pessoa);
            this.serviceAluguel.cadastrar(aluguel);
        }catch (Exception e){
            System.out.println("Não foi possível alugar + "+e.getMessage());
        }
    }

    private void devolver(){
        try {
            this.viewVeiculo.listar();
            System.out.print("Digite a placa do carro: ");
            Veiculo veiculo = this.viewVeiculo.getVeiculo(this.entrada.receberString());
            Aluguel aluguel = this.serviceAluguel.buscarPorVeiculo(veiculo);
            Double valorAlugue = this.serviceAluguel.devolucao(aluguel);
            System.out.println("O valor do aluguel é: "+valorAlugue);
        }catch (Exception e){
            System.out.println("Não foi possível alugar + "+e.getMessage());
        }
    }

    private void listar(){
        List<Aluguel> lista = this.serviceAluguel.listar();
        System.out.println("##   Alugueis  ##");
        for (Aluguel aluguel : lista){
            System.out.print("Veiculo de placa: "+aluguel.getVeiculo().getPlaca()+
                    ", Alugado para:  "+aluguel.getPessoa().getNome()+
                    ", na data de: "+aluguel.getDataEmprestimo().toString());
            if (aluguel.getDataDevolucao() != null){
                System.out.println(", e devolução: "+aluguel.getDataDevolucao().toString());
            }else{
                System.out.println(" ");
            }
        }
    }


}
