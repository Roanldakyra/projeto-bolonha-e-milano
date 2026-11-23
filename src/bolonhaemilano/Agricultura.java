package bolonhaemilano;

public class Agricultura {
    private static final int HECTARES_POR_SERVO = 5;
    private static final int RENDIMENTO_POR_HECTARE = 2;
    private static final int SERVOS_POR_GRAO = 5;
    private static final int CRESCIMENTO_PERCENTUAL = 5;
    private static final int PERCENTUAL_MINIMO = 20;
    private static final int PERCENTUAL_MAXIMO = 80;

    public int processarColheita(Cidade cidade) {
        if (cidade == null || cidade.isColheitaFeita()) return -1;
        int hectares = (int) Math.min(cidade.getTerras(), (long) cidade.getServos() * HECTARES_POR_SERVO);
        int sementes = (hectares + 1) / 2;
        if (sementes > cidade.getGraos()) {
            hectares = cidade.getGraos() * 2;
            sementes = (hectares + 1) / 2;
        }
        if (sementes > 0) cidade.removerGraos(sementes);
        long producao = (long) hectares * RENDIMENTO_POR_HECTARE;
        cidade.adicionarGraos((int) Math.min(producao, Integer.MAX_VALUE));
        cidade.marcarColheitaFeita();
        return hectares;
    }

    public boolean distribuirAlimentos(Cidade cidade, int percentual) {
        if (cidade == null || percentual < PERCENTUAL_MINIMO || percentual > PERCENTUAL_MAXIMO) return false;
        int alimento = (int) ((long) cidade.getGraos() * percentual / 100);
        if (alimento > 0) cidade.removerGraos(alimento);
        int necessidade = (cidade.getServos() + SERVOS_POR_GRAO - 1) / SERVOS_POR_GRAO;
        if (alimento >= necessidade) {
            cidade.adicionarServos(cidade.getServos() * CRESCIMENTO_PERCENTUAL / 100);
        } else {
            cidade.removerServos(Math.min(necessidade - alimento, cidade.getServos()));
        }
        return true;
    }
}
