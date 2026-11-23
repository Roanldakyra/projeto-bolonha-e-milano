package bolonhaemilano;

public class Cidade {
    private final String nome;
    private int anoAtual;
    private final Jogador dono;
    private int florins, terras, graos, servos;
    private int taxaImposto;
    private String nivelJustica;
    private int soldados, construcoes;
    private boolean colheitaFeita, impostosArrecadados;

    public Cidade(String nome, Jogador dono) {
        if (nome == null || nome.trim().isEmpty() || dono == null) {
            throw new IllegalArgumentException("Cidade e governante devem ser informados.");
        }
        this.nome = nome.trim();
        this.dono = dono;
        anoAtual = 1400;
        florins = 1000;
        terras = 10000;
        graos = 500;
        servos = 200;
        taxaImposto = 10;
        nivelJustica = "Moderada";
        soldados = 0;
        construcoes = 0;
    }

    public String getNome() { return nome; }
    public int getAnoAtual() { return anoAtual; }
    public Jogador getDono() { return dono; }
    public int getFlorins() { return florins; }
    public int getTerras() { return terras; }
    public int getGraos() { return graos; }
    public int getServos() { return servos; }
    public int getTaxaImposto() { return taxaImposto; }
    public String getNivelJustica() { return nivelJustica; }
    public int getSoldados() { return soldados; }
    public int getConstrucoes() { return construcoes; }
    public boolean isColheitaFeita() { return colheitaFeita; }
    public boolean isImpostosArrecadados() { return impostosArrecadados; }

    public void marcarColheitaFeita() { colheitaFeita = true; }
    public void marcarImpostosArrecadados() { impostosArrecadados = true; }

    public boolean setTaxaImposto(int taxa) {
        if (taxa < 0 || taxa > 50) return false;
        taxaImposto = taxa;
        return true;
    }

    public boolean setNivelJustica(String nivel) {
        if (nivel == null || !(nivel.equalsIgnoreCase("Branda")
                || nivel.equalsIgnoreCase("Moderada") || nivel.equalsIgnoreCase("Severa"))) {
            return false;
        }
        if (nivel.equalsIgnoreCase("Branda")) nivelJustica = "Branda";
        else if (nivel.equalsIgnoreCase("Moderada")) nivelJustica = "Moderada";
        else nivelJustica = "Severa";
        return true;
    }

    public void avancarAno() {
        anoAtual++;
        colheitaFeita = false;
        impostosArrecadados = false;
    }

    public void adicionarFlorins(int q) { if (q > 0) florins = seguroSomar(florins, q); }
    public boolean removerFlorins(int q) { if (q <= 0 || florins < q) return false; florins -= q; return true; }
    public void adicionarTerras(int q) { if (q > 0) terras = seguroSomar(terras, q); }
    public boolean removerTerras(int q) { if (q <= 0 || terras < q) return false; terras -= q; return true; }
    public void adicionarGraos(int q) { if (q > 0) graos = seguroSomar(graos, q); }
    public boolean removerGraos(int q) { if (q <= 0 || graos < q) return false; graos -= q; return true; }
    public void adicionarServos(int q) { if (q > 0) servos = seguroSomar(servos, q); }
    public boolean removerServos(int q) { if (q <= 0 || servos < q) return false; servos -= q; return true; }
    public void adicionarSoldados(int q) { if (q > 0) soldados = seguroSomar(soldados, q); }
    public boolean removerSoldados(int q) { if (q <= 0 || soldados < q) return false; soldados -= q; return true; }
    public void adicionarConstrucoes(int q) { if (q > 0) construcoes = seguroSomar(construcoes, q); }
    public boolean removerConstrucoes(int q) { if (q <= 0 || construcoes < q) return false; construcoes -= q; return true; }

    private int seguroSomar(int atual, int quantidade) {
        long resultado = (long) atual + quantidade;
        return resultado > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) resultado;
    }

    public void exibirStatus() {
        System.out.println("\n===== STATUS DA CIDADE =====");
        System.out.println("Cidade: " + nome + " | Ano: " + anoAtual);
        System.out.println("Governante: " + dono.getNome() + " (" + dono.getTitulo() + ")");
        System.out.println("Florins: " + florins + " | Terras: " + terras + " ha");
        System.out.println("Grãos: " + graos + " | Servos: " + servos);
        System.out.println("Soldados: " + soldados + " | Construções: " + construcoes);
        System.out.println("Imposto: " + taxaImposto + "% | Justiça: " + nivelJustica);
        System.out.println("Colheita do ano: " + (colheitaFeita ? "realizada" : "pendente"));
        System.out.println("Impostos do ano: " + (impostosArrecadados ? "arrecadados" : "pendentes"));
        System.out.println("============================");
    }
}
