package infra.repository;

import java.util.List;

public interface RepositorioAbstrato<T> {
    public void salva(T objeto);

    public void deletar(T objeto);

    public List<T> buscar();

    public T buscarporId(Long id);

    public void atualizar(T objeto);
}