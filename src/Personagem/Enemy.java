package Personagem;
import java.util.Random;

public class Enemy extends Personagem.Character {
    private String nome = "Bugs, o Inseto do Código";
    private String[] falasDano = {"Argh! Meu código quebrou!", "Isso não estava na documentação!", "Maldito StackOverflow..."};
    private String[] falasAtaque = {"Hahaha, erro de sintaxe!", "Tome esse NullPointerException!", "Seu código não compila!"};
    private Random rand = new Random();

    public Enemy() {
        super(150, 20, 45);
    }

    @Override
    public void atacar(Character alvo){
        int dano = this.getAtaque();
        alvo.receberDano(dano);
    }

    // Novos métodos para a interface
    public String getNome() { return nome; }

    public String falar(boolean sofreuDano) {
        if(sofreuDano) return falasDano[rand.nextInt(falasDano.length)];
        return falasAtaque[rand.nextInt(falasAtaque.length)];
    }
}