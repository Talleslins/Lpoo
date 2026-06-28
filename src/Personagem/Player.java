package Personagem;

public class Player{
    private String Nome;
    private int Pontuacao;
    private Personagem.Character PersonagemEscolhido;

    public Player(String nome, int pontuacao , Personagem.Character personagemEscolhido) {
        this.Nome = nome;
        this.Pontuacao = pontuacao;
        this.PersonagemEscolhido = personagemEscolhido;
    }

    public Character getPersonagemEscolhido() {
        return PersonagemEscolhido;
    }
    public String getNome() { return Nome; }
    public int getPontuacao() { return Pontuacao; }
    public void adicionarPontos(int pontos) { this.Pontuacao += pontos; }
}
