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
}
