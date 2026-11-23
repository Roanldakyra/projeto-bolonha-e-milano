package bolonhaemilano;

public class Testes {
    private static int aprovados;
    private static int reprovados;

    private static void verificar(String descricao, boolean condicao) {
        if (condicao) {
            aprovados++;
            System.out.println("[OK]    " + descricao);
        } else {
            reprovados++;
            System.out.println("[FALHA] " + descricao);
        }
    }
    private static Cidade novaCidade() {
        return new Cidade("Bolonha", new Jogador("Teste"));
    }
    private static void testarCidade() {
        System.out.println("\n-- Cidade --");
        Cidade c = novaCidade();
        verificar("cidade nasce com 1000 florins", c.getFlorins() == 1000);
        verificar("cidade nasce no ano 1400", c.getAnoAtual() == 1400);
        verificar("removerFlorins acima do saldo devolve false", !c.removerFlorins(1001));
        verificar("remoção recusada não altera saldo", c.getFlorins() == 1000);
        c.marcarColheitaFeita(); c.marcarImpostosArrecadados(); c.avancarAno();
        verificar("avancarAno soma 1 ao ano", c.getAnoAtual() == 1401);
        verificar("avancarAno libera colheita e impostos", !c.isColheitaFeita() && !c.isImpostosArrecadados());
    }
    private static void testarMercado() {
        System.out.println("\n-- Mercado --");
        Mercado m = new Mercado(); Cidade c = novaCidade();
        verificar("comprar 100 grãos funciona", m.comprarGraos(c, 100));
        verificar("compra custa 200 florins", c.getFlorins() == 800);
        verificar("compra soma 100 grãos", c.getGraos() == 600);
        verificar("quantidade negativa é recusada", !m.comprarGraos(c, -1));
        verificar("compra sem florins suficientes é recusada", !m.comprarTerras(c, 1000));
    }
    private static void testarAgricultura() {
        System.out.println("\n-- Agricultura --");
        Agricultura a = new Agricultura(); Cidade c = novaCidade();
        int hectares = a.processarColheita(c);
        verificar("primeira colheita cultiva 1000 hectares", hectares == 1000);
        verificar("colheita deixa 2000 grãos", c.getGraos() == 2000);
        verificar("segunda colheita no mesmo ano devolve -1", a.processarColheita(c) == -1);
        verificar("percentual abaixo de 20 é recusado", !a.distribuirAlimentos(c, 10));
    }
    private static void testarGoverno() {
        System.out.println("\n-- Governo --");
        Governo g = new Governo(); Cidade c = novaCidade();
        verificar("taxa de 60% é recusada", !g.definirTaxaImposto(c, 60));
        verificar("justiça inválida é recusada", !g.definirJustica(c, "Brandaço"));
        c.removerServos(0); // preserva a população inicial
        g.definirTaxaImposto(c, 10); g.definirJustica(c, "Severa");
        verificar("200 servos, 10%, justiça Severa arrecada 26", g.arrecadarImpostos(c) == 26);
        verificar("segunda arrecadação no ano devolve -1", g.arrecadarImpostos(c) == -1);
    }
    private static void testarConstrucoes() {
        System.out.println("\n-- Construções --");
        Construcoes b = new Construcoes(); Cidade c = novaCidade();
        c.removerServos(150);
        verificar("recrutar sem servos suficientes é recusado", !b.recrutarSoldados(c, 100));
        verificar("recrutamento recusado não debita florins", c.getFlorins() == 1000);
        verificar("construir 10 funciona", b.construir(c, 10));
        verificar("construir 10 custa 200 florins", c.getFlorins() == 800);
    }
    private static void testarJogadorENobreza() {
        System.out.println("\n-- Jogador e Nobreza --");
        Cidade c = novaCidade(); Jogador j = c.getDono(); Nobreza n = new Nobreza();
        verificar("jogador começa como Cavaleiro", j.getTitulo().equals("Cavaleiro"));
        verificar("prestígio inicial é 300", n.calcularPrestigio(c) == 300);
        verificar("prestígio 300 não promove", !n.verificarPromocao(c));
        c.adicionarConstrucoes(50);
        verificar("50 construções promovem para Barão", n.verificarPromocao(c) && j.getTitulo().equals("Barão"));
        c.adicionarConstrucoes(100);
        verificar("no máximo um título por chamada", n.verificarPromocao(c) && j.getTitulo().equals("Conde"));
        j.promover(); j.promover(); j.promover(); // Marquês, Duque e Rei/Rainha
        verificar("não promove além de Rei/Rainha", !j.promover());
    }
    private static void testarPartida() {
        System.out.println("\n-- Partida --");
        Partida p = new Partida(); Nobreza n = new Nobreza();
        Cidade b = new Cidade("Bolonha", new Jogador("Ana"));
        Cidade m = new Cidade("Milano", new Jogador("Bia"));
        Cidade f = new Cidade("Florença", new Jogador("Caio"));
        Cidade v = new Cidade("Veneza", new Jogador("Dora"));
        p.adicionarCidade(b); p.adicionarCidade(m); p.adicionarCidade(f); p.adicionarCidade(v);
        verificar("primeiro turno é de Bolonha", p.getCidadeAtual() == b);
        verificar("turno de Bolonha não fecha o ano", !p.passarTurno());
        verificar("agora é a vez de Milano", p.getCidadeAtual() == m);
        boolean turnosIntermediarios = !p.passarTurno() && p.getCidadeAtual() == f
                && !p.passarTurno() && p.getCidadeAtual() == v;
        verificar("Milano e Florença passam; Veneza fecha o ano",
                turnosIntermediarios && p.passarTurno());
        p.encerrarAno(n);
        verificar("encerrarAno avança todas as cidades", b.getAnoAtual() == 1401 && m.getAnoAtual() == 1401);
        verificar("partida não terminou em 1401", !p.terminou());
        verificar("vencedor parcial é a primeira cidade no empate", p.determinarVencedor(n) == b);
        verificar("quinta cidade é recusada", !p.adicionarCidade(new Cidade("Roma", new Jogador("Eva")))
                && p.getCidades().size() == 4);
    }
    public static void main(String[] args) {
        aprovados = 0; reprovados = 0;
        System.out.println("===== TESTES BOLONHA E MILANO =====");
        testarCidade(); testarMercado(); testarAgricultura(); testarGoverno();
        testarConstrucoes(); testarJogadorENobreza(); testarPartida();
        System.out.println("\nAprovados: " + aprovados + " | Reprovados: " + reprovados);
        if (reprovados > 0) System.exit(1);
    }
}
