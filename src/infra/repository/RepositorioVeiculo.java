package infra.repository;

import infra.db.BancoDeDados;
import models.Veiculo;

import java.util.List;

public class RepositorioVeiculo<T extends Veiculo> extends  RepositorioAbstrato<T>{

    private final Class<T> tipoClass;

    public RepositorioVeiculo(BancoDeDados bd, Class<T> tipoClass){
        super(bd);
        this.tipoClass = tipoClass;
    }

    @Override
    protected Class<T> classeModelo() {
        return this.tipoClass;
    }

    @Override
    public T buscarPorId(Long id) {
        List objetos = listar();
        T encontradoPorId = null;
        for (Object objeto : objetos) {
            T obj = (T) objeto;
            if (obj.getId().equals(id)) {
                encontradoPorId = obj;
            }
        }
        return encontradoPorId;
    }

    @Override
    public void gravar(T obj) throws RuntimeException {
        obj.setId(this.bancoDeDados.proximoId());
        this.bancoDeDados.inserirObjeto(obj);
    }
}
