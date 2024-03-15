package infra.repository;

import infra.db.BancoDeDados;
import models.Pessoa;
import models.PessoaJuridica;

import java.util.List;

public class RepositorioPessoa<T extends Pessoa> extends RepositorioAbstrato<T> {

    private final Class<T> tipoClass;

    public RepositorioPessoa(BancoDeDados bancoDeDados, Class<T> tipoClass) {
        super(bancoDeDados);
        this.tipoClass = tipoClass;
    }

    @Override
    protected Class<T> classeModelo() {
        return tipoClass;
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