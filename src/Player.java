import java.lang.Character;

public class Player {
    private String Nome;
    private int Vida;
    private int Pontuacao;
    private Character PersonagemEscolhido;

    public Player(String nome, int vida, int pontuacao , Character personagemEscolhido) {
        this.Nome = nome;
        this.Vida = vida;
        this.Pontuacao = pontuacao;
        this.PersonagemEscolhido = personagemEscolhido;

    }


}
