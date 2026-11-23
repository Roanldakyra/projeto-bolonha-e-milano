package bolonhaemilano;

public class Construcoes {
    private static final int CUSTO_CONSTRUCAO = 20;
    private static final int CUSTO_SOLDADO = 5;
    private static final int SERVOS_POR_SOLDADO = 1;

    public boolean construir(Cidade cidade, int quantidade) {
        if (cidade == null || quantidade <= 0 || (long) quantidade * CUSTO_CONSTRUCAO > Integer.MAX_VALUE) return false;
        int custo = quantidade * CUSTO_CONSTRUCAO;
        if (!cidade.removerFlorins(custo)) return false;
        cidade.adicionarConstrucoes(quantidade);
        return true;
    }
    public boolean recrutarSoldados(Cidade cidade, int quantidade) {
        if (cidade == null || quantidade <= 0
                || (long) quantidade * CUSTO_SOLDADO > Integer.MAX_VALUE) return false;
        int custo = quantidade * CUSTO_SOLDADO;
        // Validar os dois recursos antes de debitar qualquer um.
        if (cidade.getFlorins() < custo || cidade.getServos() < quantidade * SERVOS_POR_SOLDADO) return false;
        if (!cidade.removerFlorins(custo)) return false;
        if (!cidade.removerServos(quantidade * SERVOS_POR_SOLDADO)) {
            cidade.adicionarFlorins(custo); // proteção adicional contra débito parcial
            return false;
        }
        cidade.adicionarSoldados(quantidade);
        return true;
    }
}
