package infra.repository;

import infra.db.BancoDeDados;
import models.Veiculo;

import java.util.List;

public class RepositorioVeiculo<T extends Veiculo> extends  RepositorioAbstrato<T>{

    public RepositorioVeiculo(BancoDeDados bd){
        super(bd);
    }

    @Override
    protected Class classeModelo() {
        T obj =null;
        return obj.getClass();
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
    public void gravar(T obj) throws Exception {
        obj.setId(this.bancoDeDados.proximoId());
        this.bancoDeDados.inserirObjeto(obj);
    }
}
