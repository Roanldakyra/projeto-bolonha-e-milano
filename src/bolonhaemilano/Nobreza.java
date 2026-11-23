package bolonhaemilano;

public class Nobreza {
    private static final int PESO_CONSTRUCAO = 10;
    private static final int PESO_SOLDADO = 2;
    private static final int PESO_SERVO = 1;
    private static final int HECTARES_POR_PONTO = 100;
    private static final int[] PRESTIGIO_MINIMO = {0, 400, 550, 700, 850, 1000};

    public int calcularPrestigio(Cidade cidade) {
        if (cidade == null) return 0;
        long total = (long) cidade.getConstrucoes() * PESO_CONSTRUCAO
                + (long) cidade.getSoldados() * PESO_SOLDADO
                + cidade.getServos()
                + cidade.getTerras() / HECTARES_POR_PONTO;
        return (int) Math.min(Integer.MAX_VALUE, total);
    }
    public int prestigioParaProximoTitulo(Jogador jogador) {
        if (jogador == null || jogador.ehMonarca()) return -1;
        return PRESTIGIO_MINIMO[jogador.getNivelTitulo() + 1];
    }
    public boolean verificarPromocao(Cidade cidade) {
        if (cidade == null || cidade.getDono() == null) return false;
        Jogador dono = cidade.getDono();
        int necessario = prestigioParaProximoTitulo(dono);
        if (necessario < 0 || calcularPrestigio(cidade) < necessario) return false;
        return dono.promover();
    }
}
