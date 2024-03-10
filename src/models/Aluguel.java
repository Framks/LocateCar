package models;

import java.time.LocalDateTime;

public class Aluguel {

    private Long id;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private Veiculo veiculo;
    private Pessoa pessoa;

    public Aluguel(){

    }

    public Aluguel(LocalDateTime dataEmprestimo,Veiculo veiculo, Pessoa pessoa){
        this.pessoa = pessoa;
        this.dataEmprestimo = dataEmprestimo;
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public Pessoa getCliente() {
        return pessoa;
    }

    public void setCliente(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
