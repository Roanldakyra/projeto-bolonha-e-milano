package bolonhaemilano;

public class Mercado {
    private static final int PRECO_GRAO = 2;
    private static final int PRECO_TERRA = 10;
    public int getPrecoGrao() { return PRECO_GRAO; }
    public int getPrecoTerra() { return PRECO_TERRA; }

    public boolean comprarGraos(Cidade c, int q) {
        if (c == null || q <= 0 || (long) q * PRECO_GRAO > Integer.MAX_VALUE) return false;
        int custo = q * PRECO_GRAO;
        if (!c.removerFlorins(custo)) return false;
        c.adicionarGraos(q); return true;
    }
    public boolean venderGraos(Cidade c, int q) {
        if (c == null || q <= 0 || (long) q * PRECO_GRAO > Integer.MAX_VALUE) return false;
        if (!c.removerGraos(q)) return false;
        c.adicionarFlorins(q * PRECO_GRAO); return true;
    }
    public boolean comprarTerras(Cidade c, int q) {
        if (c == null || q <= 0 || (long) q * PRECO_TERRA > Integer.MAX_VALUE) return false;
        int custo = q * PRECO_TERRA;
        if (!c.removerFlorins(custo)) return false;
        c.adicionarTerras(q); return true;
    }
    public boolean venderTerras(Cidade c, int q) {
        if (c == null || q <= 0 || (long) q * PRECO_TERRA > Integer.MAX_VALUE) return false;
        if (!c.removerTerras(q)) return false;
        c.adicionarFlorins(q * PRECO_TERRA); return true;
    }
}
