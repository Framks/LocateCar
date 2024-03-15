package services;

import infra.repository.RepositorioAluguel;
import models.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ServiceAluguel {

    private RepositorioAluguel repositorioAluguel;

    public ServiceAluguel(RepositorioAluguel repositorioAluguel){
        this.repositorioAluguel = repositorioAluguel;
    }

    public void cadastrar(Aluguel aluguel) throws RuntimeException{
        if (aluguel == null)
            throw new RuntimeException("Aluguel não pode ser nulo");
        if(aluguel.getPessoa() == null)
            throw new RuntimeException("Para alugar tem que ter uma pessoa");
        if (aluguel.getVeiculo() == null)
            throw new RuntimeException("Para alugar tem que ter um veiculo");
        if(aluguel.getDataEmprestimo() == null)
            throw  new RuntimeException("Para alugar tem que ter uma data de emprestimo");
        if (aluguel.getVeiculo().getOcupado())
            throw new RuntimeException("Carro ja esta ocupado");
        aluguel.getVeiculo().setOcupado(true);
        this.repositorioAluguel.gravar(aluguel);
    }

    public void excluir(Aluguel aluguel){
        if (aluguel == null)
            throw new RuntimeException("Aluguel não pode ser nulo");
        if(aluguel.getId() == null)
            throw new RuntimeException("Id de aluguel nulo");
        this.repositorioAluguel.excluir(aluguel);
    }

    public List<Aluguel> listar(){
        return this.repositorioAluguel.listar();
    }

    public Aluguel buscarPorVeiculo(Veiculo veiculo){
        if (veiculo == null)
            throw new RuntimeException("Veiculo não pode ser nulo");
        List<Aluguel> alugueis = this.repositorioAluguel.listar();
        for (Aluguel a: alugueis){
            if (a.getVeiculo().getPlaca().equals(veiculo.getPlaca())){
                return a;
            }
        }
        throw new RuntimeException("não encontrado");
    }

    private Double calcularValorDevolucao(Aluguel aluguel){
        if (aluguel == null)
            throw new RuntimeException("aluguel nulo");
        if(aluguel.getDataDevolucao() == null)
            throw new RuntimeException("Para calcular a devolução é necessario ter data de devolução");
        if(aluguel.getVeiculo() == null)
            throw new RuntimeException("Veiculo nulo");

        Integer valorDiaria = 0;

        if(aluguel.getVeiculo() instanceof VeiculoSuv){
            valorDiaria = 200;
        }else if (aluguel.getVeiculo() instanceof VeiculoPequeno){
            valorDiaria = 100;
        }else if(aluguel.getVeiculo() instanceof VeiculoMedio){
            valorDiaria = 150;
        }

        // aqui nós pegamos a diferença de dias
        long diferencaDataMinutos = ChronoUnit.MINUTES.between(aluguel.getDataEmprestimo(),aluguel.getDataDevolucao());
        long diferecaDataDia = (diferencaDataMinutos/(24*60) + (((diferencaDataMinutos%(24*60)) > 0 ) ? 1: 0));

        // aqui faz o calculo do valor total dos dias
        Double valorTotal = (double) (valorDiaria * diferecaDataDia);
        // aqui faz calculo do desconto
        if(aluguel.getPessoa() instanceof PessoaFisica){
            if (diferecaDataDia >= 5)
                valorTotal= valorTotal*0.95;
        }
        else if(aluguel.getPessoa() instanceof PessoaJuridica){
            if(diferecaDataDia >=3)
                valorTotal = valorTotal*0.90;
        }
        return valorTotal;
    }

    public Double devolucao(Aluguel aluguel){
        if (aluguel == null)
            throw new RuntimeException("Aluguel nulo");
        Double valor = calcularValorDevolucao(aluguel);
        aluguel.getVeiculo().setOcupado(false);
        return valor;
    }
}
