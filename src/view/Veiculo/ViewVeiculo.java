package view.Veiculo;

import models.Veiculo;
import services.ServiceVeiculo;
import view.EntradaVerificada;

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
}
