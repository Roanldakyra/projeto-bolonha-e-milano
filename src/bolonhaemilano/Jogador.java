package bolonhaemilano;

public class Jogador {
    private static final String[] TITULOS = {
        "Cavaleiro", "Barão", "Conde", "Marquês", "Duque", "Rei/Rainha"
    };
    private final String nome;
    private int nivelTitulo;

    public Jogador(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do jogador não pode ficar vazio.");
        }
        this.nome = nome.trim();
        this.nivelTitulo = 0;
    }

    public String getNome() { return nome; }
    public String getTitulo() { return TITULOS[nivelTitulo]; }
    public int getNivelTitulo() { return nivelTitulo; }

    public boolean promover() {
        if (ehMonarca()) return false;
        nivelTitulo++;
        return true;
    }

    public boolean ehMonarca() {
        return nivelTitulo == TITULOS.length - 1;
    }
}
