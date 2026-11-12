package bolonhaemilano;

public class Jogador {
    private String nome;
    private Cidade cidade;

    public Jogador(String nome, Cidade cidade) { this.nome = nome; this.cidade = cidade; }
    public String getNome() { return nome; }
    public Cidade getCidade() { return cidade; }
}
