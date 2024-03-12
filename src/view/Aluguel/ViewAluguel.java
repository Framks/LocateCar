package view.Aluguel;

import infra.repository.RepositorioAluguel;
import services.ServiceAluguel;
import view.EntradaVerificada;
import view.Pessoa.ViewPessoa;

public class ViewAluguel {

    private final ServiceAluguel serviceAluguel;
    private final EntradaVerificada entrada;

    public ViewAluguel(ServiceAluguel serviceAluguel, EntradaVerificada entrada){
        this.serviceAluguel= serviceAluguel;
        this.entrada = entrada;
    }
    public void run(){
        Integer option;
        boolean execucao = true;
        while (execucao){
            System.out.print("##    Aluguéis     ##\n" +
                    "    0 - Sair: \n" +
                    "    1 - Alugar Veiculo: \n" +
                    "    2 - Devolver Veiculo: \n" +
                    "    3 - Listar Todos Alugueis: \n"+
                    "Digite a opção para entrar: ");
            option = this.entrada.receberInteger();
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

    }

    private void devolver(){

    }

    private void listar(){

    }
}
