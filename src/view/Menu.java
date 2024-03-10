package view;

import models.Pessoa;
import models.Veiculo;
import services.ServicePessoa;
import services.ServiceVeiculo;

public class Menu {
    private ServiceVeiculo<? extends Veiculo> servVeiPeq;
    private ServiceVeiculo<? extends Veiculo> servVeiMed;
    private ServiceVeiculo<? extends Veiculo> servVeiSuv;
    private ServicePessoa<? extends Pessoa> servPF;
    private ServicePessoa<? extends Pessoa> servPJ;

    public Menu(ServiceVeiculo<? extends Veiculo> servVeiPeq, ServiceVeiculo<? extends Veiculo> servVeiMed, ServiceVeiculo<? extends Veiculo> servVeiSuv, ServicePessoa<? extends Pessoa> servPF, ServicePessoa<? extends Pessoa> servPJ){
        this.servVeiPeq = servVeiPeq;
        this.servVeiMed = servVeiMed;
        this.servVeiSuv = servVeiSuv;
        this.servPF = servPF;
        this.servPJ = servPJ;
    }


}
