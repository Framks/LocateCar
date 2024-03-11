package services;

import infra.repository.RepositorioPessoa;
import models.Pessoa;

import java.util.List;

public class ServicePessoa<T extends Pessoa> {

    private RepositorioPessoa<T> repositorioPessoa;

    public ServicePessoa(RepositorioPessoa<T> repositorioPessoa){
        this.repositorioPessoa = repositorioPessoa;
    }

    public void cadastrar(T veiculo){

    }
    public void alterar(T veiculo){

    }

    public List<T> listar(){
        return this.repositorioPessoa.listar();
    }

    public void excluir(T veiculo){

    }

    public T buscarPorNome(String nome){
        return null;
    }

    public T buscarPorId(Long id){
        return null;
    }


}
