package bolonhaemilano;

public class Governo {
    public int arrecadarImpostos(Cidade cidade) {
        double base = cidade.getServos() * cidade.getTaxaImposto() / 100.0;
        double multiplicador = multiplicadorPara(cidade.getNivelJustica());
        int arrecadado = (int) (base * multiplicador);
        cidade.adicionarFlorins(arrecadado);
        return arrecadado;
    }

    public boolean definirTaxaImposto(Cidade cidade, int taxa) {
        if (taxa < 0 || taxa > 50) return false;
        cidade.setTaxaImposto(taxa);
        return true;
    }

    public boolean definirJustica(Cidade cidade, String nivel) {
        if (nivel == null) return false;
        if (!nivel.equalsIgnoreCase("Justa") &&
            !nivel.equalsIgnoreCase("Moderada") &&
            !nivel.equalsIgnoreCase("Severa")) return false;
        if (nivel.equalsIgnoreCase("Justa")) cidade.setNivelJustica("Justa");
        else if (nivel.equalsIgnoreCase("Moderada")) cidade.setNivelJustica("Moderada");
        else cidade.setNivelJustica("Severa");
        return true;
    }

    private double multiplicadorPara(String nivel) {
        switch (nivel) {
            case "Justa": return 0.8;
            case "Severa": return 1.3;
            default: return 1.0;
        }
    }
}
