package infra.repository;

import infra.db.BancoDeDados;
import models.Pessoa;
import models.PessoaJuridica;

import java.util.List;

public class RepositorioPessoa<T extends Pessoa> extends RepositorioAbstrato<T> {

    public RepositorioPessoa(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    protected Class classeModelo() {
        return PessoaJuridica.class;
    }

    @Override
    public T buscarPorId(Long id) {
        List objetos = listar();
        T encontradoPorId = null;
        for (Object objeto : objetos) {
            T obj = (T) objeto;
            if (obj.getIdentificao().equals(id)) {
                encontradoPorId = obj;
            }
        }
        return encontradoPorId;
    }

}