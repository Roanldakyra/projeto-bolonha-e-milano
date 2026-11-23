package bolonhaemilano;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private boolean emExecucao;
    private Partida partida;
    private Cidade cidade;
    private final Mercado mercado;
    private final Agricultura agricultura;
    private final Governo governo;
    private final Construcoes construcoes;
    private final Nobreza nobreza;

    public Menu() {
        scanner = new Scanner(System.in);
        emExecucao = true;
        mercado = new Mercado();
        agricultura = new Agricultura();
        governo = new Governo();
        construcoes = new Construcoes();
        nobreza = new Nobreza();
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            processarOpcao(lerOpcao());
        }
    }
    private void exibirOpcoes() {
        System.out.println("\n===== BOLONHA E MILANO | Ano " + (partida == null ? "—" : partida.getAnoAtual()) + " =====");
        System.out.println("1 - Iniciar novo jogo");
        System.out.println("2 - Ver status da cidade atual");
        System.out.println("3 - Acessar mercado");
        System.out.println("4 - Acessar agricultura");
        System.out.println("5 - Acessar governo");
        System.out.println("6 - Acessar construções");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificação");
        System.out.println("9 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    private int lerOpcao() { return lerInteiro(); }
    private int lerQuantidade() {
        System.out.print("Digite a quantidade: ");
        return lerInteiro();
    }
    private int lerInteiro() {
        String entrada = scanner.nextLine().trim();
        try { return Integer.parseInt(entrada); }
        catch (NumberFormatException e) { return -1; }
    }
    private boolean exigirJogoEmAndamento() {
        if (partida == null) {
            System.out.println("Nenhum jogo em andamento. Inicie um novo jogo primeiro.");
            return false;
        }
        return true;
    }
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: iniciarNovoJogo(); break;
            case 2: exibirStatus(); break;
            case 3: abrirMercado(); break;
            case 4: abrirAgricultura(); break;
            case 5: abrirGoverno(); break;
            case 6: abrirConstrucoes(); break;
            case 7: encerrarTurno(); break;
            case 8: exibirClassificacao(); break;
            case 9: exibirSobre(); break;
            case 0: emExecucao = false; System.out.println("Saindo do jogo..."); break;
            default: System.out.println("Opção inválida!");
        }
    }
    private void iniciarNovoJogo() {
        System.out.print("Quantidade de jogadores (1 a 4; padrão 2): ");
        int quantidade = lerOpcao();
        if (quantidade < 1 || quantidade > 4) quantidade = 2;
        Partida nova = new Partida();
        String[] cidadesPadrao = {"Bolonha", "Milano", "Florença", "Veneza"};
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Nome do jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) nome = "Jogador " + (i + 1);
            Jogador jogador = new Jogador(nome);
            Cidade novaCidade = new Cidade(cidadesPadrao[i], jogador);
            nova.adicionarCidade(novaCidade);
        }
        partida = nova;
        cidade = partida.getCidadeAtual();
        System.out.println("Partida iniciada! Começa " + cidade.getDono().getNome()
                + ", governante de " + cidade.getNome() + ".");
        exibirClassificacao();
    }
    private void exibirStatus() {
        if (!exigirJogoEmAndamento()) return;
        cidade = partida.getCidadeAtual();
        cidade.exibirStatus();
    }
    private void abrirMercado() {
        if (!exigirJogoEmAndamento()) return;
        cidade = partida.getCidadeAtual();
        boolean ativo = true;
        while (ativo) {
            System.out.println("\n===== MERCADO | " + cidade.getNome() + " =====");
            System.out.println("Florins: " + cidade.getFlorins() + " | Grãos: " + cidade.getGraos()
                    + " | Terras: " + cidade.getTerras());
            System.out.println("1 Comprar grãos | 2 Vender grãos | 3 Comprar terras | 4 Vender terras | 0 Voltar");
            int op = lerOpcao();
            int q;
            switch (op) {
                case 1: q = lerQuantidade(); informarOperacao(mercado.comprarGraos(cidade, q)); break;
                case 2: q = lerQuantidade(); informarOperacao(mercado.venderGraos(cidade, q)); break;
                case 3: q = lerQuantidade(); informarOperacao(mercado.comprarTerras(cidade, q)); break;
                case 4: q = lerQuantidade(); informarOperacao(mercado.venderTerras(cidade, q)); break;
                case 0: ativo = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }
    private void informarOperacao(boolean ok) {
        System.out.println(ok ? "Operação realizada com sucesso." : "Operação recusada. Confira quantidade e recursos.");
    }
    private void abrirAgricultura() {
        if (!exigirJogoEmAndamento()) return;
        cidade = partida.getCidadeAtual();
        boolean ativo = true;
        while (ativo) {
            System.out.println("\n===== AGRICULTURA =====");
            System.out.println("1 Colher | 2 Distribuir alimentos | 0 Voltar");
            int op = lerOpcao();
            if (op == 1) {
                int resultado = agricultura.processarColheita(cidade);
                System.out.println(resultado < 0 ? "A colheita deste ano já foi realizada."
                        : "Colheita: " + resultado + " hectares. Grãos: " + cidade.getGraos());
            } else if (op == 2) {
                System.out.print("Percentual de distribuição (20 a 80): ");
                informarOperacao(agricultura.distribuirAlimentos(cidade, lerOpcao()));
            } else if (op == 0) ativo = false;
            else System.out.println("Opção inválida.");
        }
    }
    private void abrirGoverno() {
        if (!exigirJogoEmAndamento()) return;
        cidade = partida.getCidadeAtual();
        boolean ativo = true;
        while (ativo) {
            System.out.println("\n===== GOVERNO =====");
            System.out.println("Taxa: " + cidade.getTaxaImposto() + "% | Justiça: " + cidade.getNivelJustica());
            System.out.println("1 Arrecadar impostos | 2 Definir taxa | 3 Definir justiça | 0 Voltar");
            int op = lerOpcao();
            if (op == 1) {
                int valor = governo.arrecadarImpostos(cidade);
                System.out.println(valor < 0 ? "Impostos já arrecadados neste ano." : "Arrecadados: " + valor + " florins.");
            } else if (op == 2) {
                System.out.print("Nova taxa (0 a 50): ");
                informarOperacao(governo.definirTaxaImposto(cidade, lerOpcao()));
            } else if (op == 3) {
                System.out.print("Justiça (Branda, Moderada ou Severa): ");
                informarOperacao(governo.definirJustica(cidade, scanner.nextLine().trim()));
            } else if (op == 0) ativo = false;
            else System.out.println("Opção inválida.");
        }
    }
    private void abrirConstrucoes() {
        if (!exigirJogoEmAndamento()) return;
        cidade = partida.getCidadeAtual();
        boolean ativo = true;
        while (ativo) {
            System.out.println("\n===== CONSTRUÇÕES =====");
            System.out.println("Construções: " + cidade.getConstrucoes() + " | Soldados: " + cidade.getSoldados());
            System.out.println("1 Construir | 2 Recrutar soldados | 0 Voltar");
            int op = lerOpcao();
            if (op == 1) {
                int q = lerQuantidade(); informarOperacao(construcoes.construir(cidade, q));
            } else if (op == 2) {
                int q = lerQuantidade(); informarOperacao(construcoes.recrutarSoldados(cidade, q));
            } else if (op == 0) ativo = false;
            else System.out.println("Opção inválida.");
        }
    }
    private void encerrarTurno() {
        if (!exigirJogoEmAndamento()) return;
        Cidade encerrada = partida.getCidadeAtual();
        boolean fimAno = partida.passarTurno();
        if (fimAno) {
            List<Cidade> promovidas = partida.encerrarAno(nobreza);
            System.out.println("\n===== FIM DO ANO =====");
            for (Cidade promovida : promovidas) {
                System.out.println(promovida.getDono().getNome() + " foi promovido(a) a "
                        + promovida.getDono().getTitulo() + "!");
            }
            exibirClassificacao();
            if (partida.terminou()) { encerrarPartida(); return; }
        }
        cidade = partida.getCidadeAtual();
        System.out.println("Turno encerrado por " + encerrada.getDono().getNome()
                + ". Agora é a vez de " + cidade.getDono().getNome() + " (" + cidade.getNome() + ").");
    }
    private void exibirClassificacao() {
        if (!exigirJogoEmAndamento()) return;
        System.out.println("\n===== CLASSIFICAÇÃO | ANO " + partida.getAnoAtual() + " =====");
        for (Cidade c : partida.getCidades()) {
            int prestigio = nobreza.calcularPrestigio(c);
            int proximo = nobreza.prestigioParaProximoTitulo(c.getDono());
            System.out.println(c.getNome() + " — " + c.getDono().getNome() + " | "
                    + c.getDono().getTitulo() + " | Prestígio: " + prestigio
                    + (proximo < 0 ? " | título máximo" : " | faltam: " + Math.max(0, proximo - prestigio)));
        }
    }
    private void encerrarPartida() {
        if (partida == null) return;
        Cidade vencedor = partida.determinarVencedor(nobreza);
        System.out.println("\n===== FIM DE JOGO =====");
        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.getDono().getNome() + ", de "
                    + vencedor.getNome() + " (" + vencedor.getDono().getTitulo() + ").");
        }
        partida = null;
        cidade = null;
    }
    private void exibirSobre() {
        System.out.println("\nBolonha e Milano — jogo de estratégia e administração medieval.");
        System.out.println("De 1 a 4 governantes disputam prestígio até 1410 ou até alguém alcançar Rei/Rainha.");
    }
}
