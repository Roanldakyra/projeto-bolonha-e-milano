package bolonhaemilano;

public class Mercado {
    public boolean comprarGraos(Cidade cidade, int quantidade, int precoUnitario) {
        if (quantidade <= 0 || precoUnitario < 0) return false;
        int custo = quantidade * precoUnitario;
        if (!cidade.removerFlorins(custo)) return false;
        cidade.adicionarGraos(quantidade);
        return true;
    }

    public boolean venderGraos(Cidade cidade, int quantidade, int precoUnitario) {
        if (quantidade <= 0 || precoUnitario < 0 || !cidade.removerGraos(quantidade)) return false;
        cidade.adicionarFlorins(quantidade * precoUnitario);
        return true;
    }
}
