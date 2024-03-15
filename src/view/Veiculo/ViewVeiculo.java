package view.Veiculo;

import models.*;
import services.ServiceVeiculo;
import view.EntradaVerificada;

import java.util.List;

public class ViewVeiculo {
    private final ServiceVeiculo servVeiPeq;
    private final ServiceVeiculo servVeiMed;
    private final ServiceVeiculo servVeiSuv;

    private final EntradaVerificada entrada;

    public ViewVeiculo(ServiceVeiculo<? extends Veiculo> servVeiPeq, ServiceVeiculo<? extends Veiculo> servVeiMed, ServiceVeiculo<? extends Veiculo> servVeiSuv, EntradaVerificada entrada){
        this.servVeiMed = servVeiMed;
        this.servVeiSuv = servVeiSuv;
        this.servVeiPeq = servVeiPeq;
        this.entrada = entrada;
    }

    public void run(){
        Integer option;
        boolean execucao = true;
        while (execucao){
            System.out.print("##    Veiculos    ##\n" +
                    "    0 - Sair: \n" +
                    "    1 - Cadastrar: \n" +
                    "    2 - Alterar: \n" +
                    "    3 - Buscar por modelo: \n" +
                    "    4 - Listar Todos: \n"+
                    "Digite a opção para entrar: ");
            option = this.entrada.receberInteger();
            switch (option){
                case 0-> execucao = false;
                case 1 -> cadastrar();
                case 2 -> alterar();
                case 3 -> buscarPorNome();
                case 4 -> listar();
                default -> System.out.println("entrada não correspondente a opções");
            }
        }
    }

    public void cadastrar(){
        try {
            System.out.println("Digite a placa do carro: ");
            String placa = entrada.receberString();
            System.out.println("Digite a modelo do carro: ");
            String modelo = entrada.receberString();
            System.out.println("Digite a marca do carro: ");
            String marca = entrada.receberString();
            System.out.println("Digite a Ano de fabricação do carro: ");
            Integer anoFabricacao = entrada.receberInteger();
            System.out.println("selecione o tipo de carro SUV(1), carro pequeno(2) ou carro medio(3): ");
            Integer tipo = entrada.receberInteger();
            Veiculo veiculo = this.getVeiculo(placa);
            if (veiculo != null)
                throw new RuntimeException("placa ja cadastrada");
            if (tipo == 1){
                veiculo = new VeiculoSuv(modelo,marca,placa,anoFabricacao);
                this.servVeiSuv.cadastrar(veiculo);
            }else if (tipo == 2){
                veiculo = new VeiculoPequeno(modelo,marca,placa,anoFabricacao);
                this.servVeiPeq.cadastrar(veiculo);
            }else if(tipo == 3) {
                veiculo = new VeiculoMedio(modelo,marca,placa,anoFabricacao);
                this.servVeiMed.cadastrar(veiculo);
            }else{
                System.out.println("erro veiculo não cadastrado");
            }
        }catch (Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void alterar(){
        try {
            System.out.println("digite a placa do carro que deseja alterar: ");
            Veiculo veiculo = getVeiculo(this.entrada.receberString());
            if (veiculo == null)
                throw new RuntimeException("Veiculo nulo");
            System.out.println("deseja alterar a marca, sim(1) ou não(2): ");
            if (this.entrada.receberInteger() == 1){
                System.out.println("atualmente a marca é: "+veiculo.getMarca()+ ", digite a nova marca: ");
                veiculo.setMarca(this.entrada.receberString());
            }
            System.out.println("deseja alterar a modelo, sim(1) ou não(2): ");
            if (this.entrada.receberInteger() == 1){
                System.out.println("atualmente o modelo é: "+veiculo.getModelo()+ ", digite o novo modelo: ");
                veiculo.setModelo(this.entrada.receberString());
            }
            System.out.println("deseja alterar a Ano Fabricação, sim(1) ou não(2): ");
            if (this.entrada.receberInteger() == 1){
                System.out.println("atualmente o ano de fabricação é: "+veiculo.getAnoFabricacao()+ ", digite o novo ano: ");
                veiculo.setAnoFabricacao(this.entrada.receberInteger());
            }
            if (veiculo instanceof VeiculoPequeno)
                this.servVeiPeq.alterar(veiculo);
            if (veiculo instanceof VeiculoMedio)
                this.servVeiMed.alterar(veiculo);
            if (veiculo instanceof VeiculoSuv)
                this.servVeiSuv.alterar(veiculo);
        }catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void buscarPorNome(){
        try {
            System.out.println("digite o nome ou parte dele: ");
            String modelo =  this.entrada.receberString();
            Veiculo veiculo = this.servVeiPeq.buscarPorNome(modelo);
            if (veiculo == null)
                veiculo = this.servVeiMed.buscarPorNome(modelo);
            if (veiculo == null)
                veiculo = this.servVeiSuv.buscarPorNome(modelo);
            if (veiculo == null)
                System.out.println("Veiculo não encontrado");
            else
                System.out.println("placa : "+veiculo.getPlaca()+
                        ", marca: "+veiculo.getMarca()+
                        ", modelo: "+veiculo.getModelo()+
                        ", ano de fabricação: "+veiculo.getAnoFabricacao());
        }catch (Exception e) {
            System.out.println("Error ao bucar"+e.getMessage());
        }
    }

    public void listar(){
        try {
            System.out.println("###      Veiculos      ####");
            List<Veiculo> lista = this.servVeiSuv.listar();
            System.out.println("-----------SUVs------------");
            System.out.println("placa    |    marca    |    modelo    |    Ano Fabricação   ");
            for (Veiculo v : lista){
                System.out.println(v.getPlaca() +"  |  "+v.getMarca()+"  |  "+v.getModelo()+"   |   "+v.getAnoFabricacao());
            }
            System.out.println("-------- Pequenos ---------");
            lista = this.servVeiPeq.listar();
            System.out.println("placa    |    marca    |    modelo    |    Ano Fabricação   ");
            for (Veiculo v : lista){
                System.out.println(v.getPlaca() +"  |  "+v.getMarca()+"  |  "+v.getModelo()+"   |   "+v.getAnoFabricacao());
            }
            System.out.println("--------- Medios ----------");
            lista = this.servVeiMed.listar();
            System.out.println("placa    |    marca    |    modelo    |    Ano Fabricação   ");
            for (Veiculo v : lista){
                System.out.println(v.getPlaca() +"  |  "+v.getMarca()+"  |  "+v.getModelo()+"   |   "+v.getAnoFabricacao());
            }
        }catch (Exception e) {
            System.out.println("não foi possivel listar"+ e.getMessage());
        }
    }

    public Veiculo getVeiculo(String placa){
        List<Veiculo> veiculos = this.servVeiSuv.listar();

        for (Veiculo veiculo : veiculos){
            if (veiculo.getPlaca().equals(placa))
                return veiculo;
        }
        veiculos = this.servVeiMed.listar();
        for (Veiculo veiculo: veiculos) {
            if (veiculo.getPlaca().equals(placa))
                return veiculo;
        }
        veiculos = this.servVeiPeq.listar();
        for(Veiculo veiculo: veiculos){
            if (veiculo.getPlaca().equals(placa))
                return  veiculo;
        }
        return null;
    }
}
