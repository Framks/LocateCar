package services;

import infra.repository.RepositorioVeiculo;
import models.Veiculo;

import java.util.List;

public class ServiceVeiculo<T extends Veiculo> {

    RepositorioVeiculo<T> repositorioVeiculo;

    public ServiceVeiculo(RepositorioVeiculo<T> repositorioVeiculo){
        this.repositorioVeiculo = repositorioVeiculo;
    }

    public void cadastrar(T veiculo){
        if (veiculo == null)
            throw new RuntimeException("veiculo nulo");
        if (veiculo.getPlaca() == null)
            throw  new RuntimeException("placa nula");
        if (veiculo.getModelo() == null)
            throw new RuntimeException("Modelo nulo");
        this.repositorioVeiculo.gravar(veiculo);
    }
    public void alterar(T veiculo){
        if (veiculo == null)
            throw new RuntimeException("veiculo nulo");
        if (veiculo.getPlaca() == null)
            throw  new RuntimeException("placa nula");

        T veiculoAnterior = this.buscarPorPlaca(veiculo.getPlaca());
        this.repositorioVeiculo.excluir(veiculoAnterior);

        if (veiculo.getModelo() != null)
            veiculoAnterior.setModelo(veiculo.getModelo());
        if (veiculo.getMarca() != null)
            veiculoAnterior.setMarca(veiculo.getMarca());
        if (veiculo.getAnoFabricacao() != null)
            veiculoAnterior.setAnoFabricacao(veiculo.getAnoFabricacao());

        this.repositorioVeiculo.gravar(veiculo);
    }

    public List<T> listar(){
        return this.repositorioVeiculo.listar();
    }

    public void excluir(T veiculo){
        if (veiculo == null)
            throw new RuntimeException("veiculo nulo");
        if (veiculo.getPlaca() == null)
            throw  new RuntimeException("placa nula");
        this.repositorioVeiculo.excluir(veiculo);
    }

    public T buscarPorPlaca(String placa){
        if (placa == null)
            throw new RuntimeException("Placa nulo");
        List<T> lista = this.repositorioVeiculo.listar();
        for (T veiculo : lista){
            if (veiculo.getPlaca().equals(placa))
                return veiculo;
        }
        throw new RuntimeException("Veiculo não encontrado");
    }

}
