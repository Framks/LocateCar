package services;

import infra.repository.RepositorioPessoa;
import models.Pessoa;

import java.util.List;

public class ServicePessoa<T extends Pessoa> {

    private RepositorioPessoa<T> repositorioPessoa;

    public ServicePessoa(RepositorioPessoa<T> repositorioPessoa){
        this.repositorioPessoa = repositorioPessoa;
    }

    public void cadastrar(T pessoa){
        if (pessoa == null)
            throw new RuntimeException("Pessoa nulo");
        if (pessoa.getIdentificao() == null)
            throw new RuntimeException("identificação nula");
        if (pessoa.getNome() == null)
            throw new RuntimeException("nome nulo");
        this.repositorioPessoa.gravar(pessoa);
    }
    public void alterar(T pessoa) throws RuntimeException{
        if (pessoa == null)
            throw new RuntimeException("Pessoa nulo");
        if (pessoa.getIdentificao() == null)
            throw new RuntimeException("Pessoa em que ter uma identificação");
        T pessoaanterior = this.repositorioPessoa.buscarPorId(pessoa.getIdentificao());
        this.excluir(pessoaanterior);
        if (pessoa.getNome() != null)
            pessoaanterior.setNome(pessoa.getNome());
        if (pessoa.getTelefone() != null)
            pessoaanterior.setTelefone(pessoa.getTelefone());
        if (pessoa.getEndereco() != null)
            pessoaanterior.setEndereco(pessoa.getEndereco());
        this.repositorioPessoa.gravar(pessoaanterior);
    }

    public List<T> listar(){
        return this.repositorioPessoa.listar();
    }

    public void excluir(T pessoa){
        if (pessoa == null || pessoa.getIdentificao() == null){
            throw new RuntimeException("Pessoa não pode ser nulo");
        }
        this.repositorioPessoa.excluir(pessoa);
    }

    public T buscarPorNome(String nome){
        if (nome == null)
            throw new RuntimeException("Nome esta nulo");
        List<T> lista = this.repositorioPessoa.listar();
        for (T pessoa : lista){
            if (pessoa.getNome().equals(nome))
                return pessoa;
        }
        return null;
    }

    public T buscarPorId(Long identificao){
        if (identificao == null)
            throw new RuntimeException("Identificação nulo");
        return this.repositorioPessoa.buscarPorId(identificao);
    }


}
