package infra.repository;

import infra.db.BancoDeDados;
import models.Aluguel;

import java.util.List;

public class RepositorioAluguel extends RepositorioAbstrato<Aluguel>{

    public RepositorioAluguel(BancoDeDados bd){
        super(bd);
    }

    @Override
    protected Class classeModelo() {
        return Aluguel.class;
    }

    @Override
    public Aluguel buscarPorId(Long id) {
        List objetos = listar();
        Aluguel encontradoPorId = null;
        for (Object objeto : objetos) {
            Aluguel obj = (Aluguel) objeto;
            if (obj.getId().equals(id)) {
                encontradoPorId = obj;
            }
        }
        return encontradoPorId;
    }


}
