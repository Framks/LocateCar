package services;

import infra.repository.RepositorioAluguel;
import models.Aluguel;

import java.util.List;

public class ServiceAluguel {

    private RepositorioAluguel repositorioAluguel;

    public ServiceAluguel(RepositorioAluguel repositorioAluguel){
        this.repositorioAluguel = repositorioAluguel;
    }

    public void cadastrar(Aluguel aluguel){

    }

    public void excluir(Aluguel aluguel){

    }

    public List<Aluguel> listar(){
        return null;
    }

    public Aluguel buscarPorId(Long id){
        return null;
    }

    private Double CalcularValorDevolucao(Aluguel aluguel){
        return null;
    }

    public void devolucao(Aluguel aluguel){

    }
}
