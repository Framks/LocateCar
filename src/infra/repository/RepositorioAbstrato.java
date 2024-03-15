package infra.repository;

import infra.db.BancoDeDados;

import java.util.Collections;
import java.util.List;

public abstract class RepositorioAbstrato<T> {
    protected BancoDeDados bancoDeDados;

    public RepositorioAbstrato(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public void gravar (T objeto) throws RuntimeException {
        this.bancoDeDados.inserirObjeto(objeto);
    }

    public List<T> listar () {
        List<T> objetosPresentesNoBanco = (List<T>) this.bancoDeDados.buscarObjetosPorTipo(classeModelo());
        return Collections.unmodifiableList(objetosPresentesNoBanco);
    }

    public Boolean excluir (T objeto){
        this.bancoDeDados.excluirObjeto(objeto);
        return true;
    }

    protected abstract Class<T> classeModelo ();

    public abstract T buscarPorId (Long id);

}