package bolonhaemilano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partida {
    public static final int MAXIMO_JOGADORES = 4;
    private static final int ANO_FINAL = 1410;
    private final List<Cidade> cidades = new ArrayList<>();
    private int indiceAtual;

    public boolean adicionarCidade(Cidade cidade) {
        if (cidade == null || cidades.size() >= MAXIMO_JOGADORES) return false;
        if (!cidades.isEmpty() && cidade.getAnoAtual() != getAnoAtual()) return false;
        cidades.add(cidade);
        return true;
    }
    public List<Cidade> getCidades() { return Collections.unmodifiableList(cidades); }
    public Cidade getCidadeAtual() { return cidades.isEmpty() ? null : cidades.get(indiceAtual); }
    public int getAnoAtual() { return cidades.isEmpty() ? 1400 : cidades.get(0).getAnoAtual(); }
    public int getAnoFinal() { return ANO_FINAL; }

    public boolean passarTurno() {
        if (cidades.isEmpty()) return false;
        indiceAtual++;
        if (indiceAtual >= cidades.size()) {
            indiceAtual = 0;
            return true;
        }
        return false;
    }
    public List<Cidade> encerrarAno(Nobreza nobreza) {
        List<Cidade> promovidas = new ArrayList<>();
        if (nobreza == null) return promovidas;
        for (Cidade cidade : cidades) {
            cidade.avancarAno();
            if (nobreza.verificarPromocao(cidade)) promovidas.add(cidade);
        }
        return promovidas;
    }
    public boolean terminou() {
        if (cidades.isEmpty()) return true;
        for (Cidade cidade : cidades) {
            if (cidade.getDono().ehMonarca()) return true;
        }
        return getAnoAtual() > ANO_FINAL;
    }
    public Cidade determinarVencedor(Nobreza nobreza) {
        if (cidades.isEmpty() || nobreza == null) return null;
        Cidade vencedor = cidades.get(0);
        for (int i = 1; i < cidades.size(); i++) {
            Cidade candidata = cidades.get(i);
            int titulo = candidata.getDono().getNivelTitulo();
            int melhorTitulo = vencedor.getDono().getNivelTitulo();
            if (titulo > melhorTitulo || (titulo == melhorTitulo
                    && nobreza.calcularPrestigio(candidata) > nobreza.calcularPrestigio(vencedor))) {
                vencedor = candidata;
            }
        }
        return vencedor;
    }
}
