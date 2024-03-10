package services;

import infra.repository.RepositorioPessoa;
import models.Pessoa;

import java.util.List;

public class ServicePessoa<T extends Pessoa> {

    private RepositorioPessoa<T> repositorioPessoa;

    public ServicePessoa(RepositorioPessoa repositorioPessoa){
        this.repositorioPessoa = repositorioPessoa;
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
