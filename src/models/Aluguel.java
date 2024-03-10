package models;

import java.time.LocalDateTime;

public class Aluguel {
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private Veiculo veiculo;
    private Cliente cliente;

    public Aluguel(LocalDateTime dataEmprestimo,Veiculo veiculo, Cliente cliente){
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
