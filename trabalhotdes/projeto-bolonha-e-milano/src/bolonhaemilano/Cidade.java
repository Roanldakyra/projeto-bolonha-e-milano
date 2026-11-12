package bolonhaemilano;

public class Cidade {
    private String nome;
    private int florins;
    private int terras;
    private int graos;
    private int servos;
    private int taxaImposto = 10;
    private String nivelJustica = "Moderada";
    private int soldados = 0;
    private int construcoes = 0;

    public Cidade(String nome, int florins, int terras, int graos, int servos) {
        this.nome = nome;
        this.florins = florins;
        this.terras = terras;
        this.graos = graos;
        this.servos = servos;
    }

    public String getNome() { return nome; }
    public int getFlorins() { return florins; }
    public int getTerras() { return terras; }
    public int getGraos() { return graos; }
    public int getServos() { return servos; }
    public int getTaxaImposto() { return taxaImposto; }
    public String getNivelJustica() { return nivelJustica; }
    public int getSoldados() { return soldados; }
    public int getConstrucoes() { return construcoes; }

    public void setTaxaImposto(int taxaImposto) { this.taxaImposto = taxaImposto; }
    public void setNivelJustica(String nivelJustica) { this.nivelJustica = nivelJustica; }

    public void adicionarFlorins(int quantidade) { if (quantidade > 0) florins += quantidade; }
    public boolean removerFlorins(int quantidade) {
        if (quantidade <= 0 || florins < quantidade) return false;
        florins -= quantidade;
        return true;
    }
    public void adicionarTerras(int quantidade) { if (quantidade > 0) terras += quantidade; }
    public boolean removerTerras(int quantidade) {
        if (quantidade <= 0 || terras < quantidade) return false;
        terras -= quantidade;
        return true;
    }
    public void adicionarGraos(int quantidade) { if (quantidade > 0) graos += quantidade; }
    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || graos < quantidade) return false;
        graos -= quantidade;
        return true;
    }
    public void adicionarServos(int quantidade) { if (quantidade > 0) servos += quantidade; }
    public boolean removerServos(int quantidade) {
        if (quantidade <= 0 || servos < quantidade) return false;
        servos -= quantidade;
        return true;
    }
    public void adicionarSoldados(int quantidade) { if (quantidade > 0) soldados += quantidade; }
    public boolean removerSoldados(int quantidade) {
        if (quantidade <= 0 || soldados < quantidade) return false;
        soldados -= quantidade;
        return true;
    }
    public void adicionarConstrucoes(int quantidade) { if (quantidade > 0) construcoes += quantidade; }
    public boolean removerConstrucoes(int quantidade) {
        if (quantidade <= 0 || construcoes < quantidade) return false;
        construcoes -= quantidade;
        return true;
    }

    public void exibirStatus() {
        System.out.println("\n===== STATUS DE " + nome.toUpperCase() + " =====");
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras);
        System.out.println("Graos: " + graos);
        System.out.println("Servos: " + servos);
        System.out.println("Taxa de imposto: " + taxaImposto + "%");
        System.out.println("Nivel de justica: " + nivelJustica);
        System.out.println("Soldados: " + soldados);
        System.out.println("Construcoes: " + construcoes);
    }
}
