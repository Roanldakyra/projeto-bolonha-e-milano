package bolonhaemilano;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;
    private Jogador jogador;
    private final Governo governo = new Governo();
    private final Agricultura agricultura = new Agricultura();
    private final Mercado mercado = new Mercado();
    private final Construcoes construcoes = new Construcoes();

    public void executar() {
        while (emExecucao) {
            System.out.println("\n===== BOLONHA E MILANO =====");
            System.out.println("1 - Novo jogo / iniciar cidade");
            System.out.println("2 - Exibir status");
            System.out.println("3 - Agricultura");
            System.out.println("4 - Mercado");
            System.out.println("5 - Acessar governo");
            System.out.println("6 - Acessar construcoes");
            System.out.println("0 - Sair");
            int opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1: iniciarJogo(); break;
                case 2: if (temCidade()) jogador.getCidade().exibirStatus(); break;
                case 3: menuAgricultura(); break;
                case 4: menuMercado(); break;
                case 5: abrirGoverno(); break;
                case 6: abrirConstrucoes(); break;
                case 0: emExecucao = false; System.out.println("Programa encerrado."); break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    private void iniciarJogo() {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) nome = "Jogador";
        System.out.print("Nome da cidade: ");
        String cidadeNome = scanner.nextLine().trim();
        if (cidadeNome.isEmpty()) cidadeNome = "Bolonha";
        Cidade cidade = new Cidade(cidadeNome, 100, 10, 50, 20);
        jogador = new Jogador(nome, cidade);
        System.out.println("Jogo iniciado para " + nome + ".");
    }

    private void abrirGoverno() {
        if (!temCidade()) return;
        boolean noGoverno = true;
        while (noGoverno) {
            Cidade cidade = jogador.getCidade();
            System.out.println("\n===== GOVERNO =====");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Taxa atual: " + cidade.getTaxaImposto() + "%");
            System.out.println("Justica: " + cidade.getNivelJustica());
            System.out.println("1 - Arrecadar impostos");
            System.out.println("2 - Definir taxa de imposto");
            System.out.println("3 - Definir nivel de justica");
            System.out.println("0 - Voltar");
            int opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1:
                    int valor = governo.arrecadarImpostos(cidade);
                    System.out.println("Impostos arrecadados: " + valor + " florins.");
                    break;
                case 2:
                    int taxa = lerInteiro("Nova taxa (0 a 50): ");
                    System.out.println(governo.definirTaxaImposto(cidade, taxa) ? "Taxa atualizada." : "Taxa invalida.");
                    break;
                case 3:
                    System.out.print("Nivel (Justa, Moderada ou Severa): ");
                    String nivel = scanner.nextLine().trim();
                    System.out.println(governo.definirJustica(cidade, nivel) ? "Justica atualizada." : "Nivel invalido.");
                    break;
                case 0: noGoverno = false; break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    private void abrirConstrucoes() {
        if (!temCidade()) return;
        boolean nasConstrucoes = true;
        while (nasConstrucoes) {
            Cidade cidade = jogador.getCidade();
            System.out.println("\n===== CONSTRUCOES =====");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Soldados: " + cidade.getSoldados());
            System.out.println("Construcoes: " + cidade.getConstrucoes());
            System.out.println("1 - Recrutar soldados (5 florins + 1 servo por soldado)");
            System.out.println("2 - Construir (20 florins por construcao)");
            System.out.println("0 - Voltar");

            int opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1: recrutarSoldados(); break;
                case 2: construir(); break;
                case 0: nasConstrucoes = false; break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    private void recrutarSoldados() {
        int quantidade = lerInteiro("Quantidade de soldados: ");
        boolean sucesso = construcoes.recrutarSoldados(jogador.getCidade(), quantidade);
        if (sucesso) System.out.println("Soldados recrutados com sucesso.");
        else System.out.println("Nao foi possivel recrutar: verifique a quantidade, os florins e os servos.");
    }

    private void construir() {
        int quantidade = lerInteiro("Quantidade de construcoes: ");
        boolean sucesso = construcoes.construir(jogador.getCidade(), quantidade);
        if (sucesso) System.out.println("Construcoes realizadas com sucesso.");
        else System.out.println("Nao foi possivel construir: verifique a quantidade e os florins.");
    }

    private void menuAgricultura() {
        if (!temCidade()) return;
        System.out.println("1 - Plantar (consome terras e graos)\n2 - Colher graos");
        int op = lerInteiro("Escolha: ");
        if (op == 1) {
            int terras = lerInteiro("Terras a utilizar: ");
            int graos = lerInteiro("Graos para plantio: ");
            System.out.println(agricultura.plantar(jogador.getCidade(), terras, graos) ? "Plantio registrado." : "Recursos insuficientes ou valores invalidos.");
        } else if (op == 2) {
            int graos = lerInteiro("Quantidade de graos colhidos: ");
            if (graos > 0) { agricultura.colher(jogador.getCidade(), graos); System.out.println("Colheita registrada."); }
            else System.out.println("Quantidade invalida.");
        }
    }

    private void menuMercado() {
        if (!temCidade()) return;
        System.out.println("1 - Comprar graos (2 florins por unidade)\n2 - Vender graos (1 florim por unidade)");
        int op = lerInteiro("Escolha: ");
        int qtd = lerInteiro("Quantidade: ");
        if (op == 1) System.out.println(mercado.comprarGraos(jogador.getCidade(), qtd, 2) ? "Compra realizada." : "Compra nao realizada.");
        else if (op == 2) System.out.println(mercado.venderGraos(jogador.getCidade(), qtd, 1) ? "Venda realizada." : "Venda nao realizada.");
    }

    private boolean temCidade() {
        if (jogador == null) { System.out.println("Inicie um jogo primeiro, na opcao 1."); return false; }
        return true;
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
