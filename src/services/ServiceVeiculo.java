package services;

import infra.repository.RepositorioVeiculo;
import models.Veiculo;

import java.util.List;

public class ServiceVeiculo<T extends Veiculo> {

    RepositorioVeiculo<T> repositorioVeiculo;

    public ServiceVeiculo(RepositorioVeiculo repositorioVeiculo){
        this.repositorioVeiculo = repositorioVeiculo;
    }

    public void cadastrar(T veiculo){

    }
    public void alterar(T veiculo){

    }

    public List<T> listar(){
        return null;
    }

    public void excluir(T veiculo){

    }

    public T buscarPorId(Long id){
        return null;
    }

}
