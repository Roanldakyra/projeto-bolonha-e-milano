package bolonhaemilano;

public class Agricultura {
    public boolean plantar(Cidade cidade, int quantidadeTerras, int graosNecessarios) {
        if (quantidadeTerras <= 0 || graosNecessarios <= 0) return false;
        if (cidade.getTerras() < quantidadeTerras || cidade.getGraos() < graosNecessarios) return false;
        return cidade.removerTerras(quantidadeTerras) && cidade.removerGraos(graosNecessarios);
    }

    public void colher(Cidade cidade, int quantidadeGraos) {
        cidade.adicionarGraos(quantidadeGraos);
    }
}
