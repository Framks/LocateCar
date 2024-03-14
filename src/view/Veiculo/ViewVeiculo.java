package view.Veiculo;

import models.PessoaFisica;
import models.PessoaJuridica;
import models.Veiculo;
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

    }

    public void listar(){

    }

    public Veiculo getVeiculo(String placa){
        List<Veiculo> veiculos = this.servVeiPeq.listar();


        for (Veiculo veiculo : veiculos){
            if (veiculo.getPlaca().equals(placa));
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
