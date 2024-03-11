package view.Aluguel;

import infra.repository.RepositorioAluguel;
import services.ServiceAluguel;
import view.EntradaVerificada;
import view.Pessoa.ViewPessoa;

public class ViewAluguel {

    private final ServiceAluguel serviceAluguel;
    private final EntradaVerificada entrada;

    public ViewAluguel(ServiceAluguel serviceAluguel, EntradaVerificada entrada){
        this.serviceAluguel= serviceAluguel;
        this.entrada = entrada;
    }
    public void run(){

    }
}
