package bolonhaemilano;

public class Governo {
    private static final int TAXA_MAXIMA = 50;
    private static final double MULTIPLICADOR_BRANDA = 0.8;
    private static final double MULTIPLICADOR_MODERADA = 1.0;
    private static final double MULTIPLICADOR_SEVERA = 1.3;

    public int arrecadarImpostos(Cidade cidade) {
        if (cidade == null || cidade.isImpostosArrecadados()) return -1;
        long base = (long) cidade.getServos() * cidade.getTaxaImposto() / 100;
        int total = (int) Math.min(Integer.MAX_VALUE, Math.round(base * multiplicadorPara(cidade.getNivelJustica())));
        cidade.adicionarFlorins(total);
        cidade.marcarImpostosArrecadados();
        return total;
    }
    public boolean definirTaxaImposto(Cidade cidade, int taxa) {
        return cidade != null && taxa >= 0 && taxa <= TAXA_MAXIMA && cidade.setTaxaImposto(taxa);
    }
    public boolean definirJustica(Cidade cidade, String nivel) {
        return cidade != null && cidade.setNivelJustica(nivel);
    }
    private double multiplicadorPara(String nivel) {
        if ("Branda".equalsIgnoreCase(nivel)) return MULTIPLICADOR_BRANDA;
        if ("Severa".equalsIgnoreCase(nivel)) return MULTIPLICADOR_SEVERA;
        return MULTIPLICADOR_MODERADA;
    }
}
