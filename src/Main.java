import infra.db.BancoDeDados;
import infra.repository.RepositorioAluguel;
import infra.repository.RepositorioPessoa;
import infra.repository.RepositorioVeiculo;
import models.*;
import services.ServiceAluguel;
import services.ServicePessoa;
import services.ServiceVeiculo;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        BancoDeDados bancoDeDados = new BancoDeDados();
        RepositorioPessoa<PessoaFisica> repoPF = new RepositorioPessoa<>(bancoDeDados);
        RepositorioPessoa<PessoaJuridica> repoPJ = new RepositorioPessoa<>(bancoDeDados);
        RepositorioVeiculo<VeiculoMedio> repoVeiMedio = new RepositorioVeiculo<>(bancoDeDados);
        RepositorioVeiculo<VeiculoPequeno> repoVeiPeq = new RepositorioVeiculo<>(bancoDeDados);
        RepositorioVeiculo<VeiculoSuv> repoVeiSuv = new RepositorioVeiculo<>(bancoDeDados);
        RepositorioAluguel repoAlu = new RepositorioAluguel(bancoDeDados);
        ServicePessoa<PessoaFisica> servPF = new ServicePessoa<>(repoPF);
        ServicePessoa<PessoaJuridica> servPJ = new ServicePessoa<>(repoPJ);
        ServiceVeiculo<VeiculoPequeno> servVeiPeq = new ServiceVeiculo<>(repoVeiPeq);
        ServiceVeiculo<VeiculoMedio> servVeiMed = new ServiceVeiculo<>(repoVeiMedio);
        ServiceVeiculo<VeiculoSuv> servVeiSuv = new ServiceVeiculo<>(repoVeiSuv);
        ServiceAluguel servAlu = new ServiceAluguel(repoAlu);

        Menu menuprincipal = new Menu(
                servVeiPeq,
                servVeiMed,
                servVeiSuv,
                servPF,
                servPJ,
                servAlu
        );
        menuprincipal.run();
    }
}