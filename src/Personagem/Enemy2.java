package Personagem;
import java.util.Random;

public class Enemy2 extends Personagem.Character {
    private String nome = "Dr. Memória Leak";
    private String[] falasDano = {"Minha RAM está esgotando...", "Você liberou meus ponteiros!", "Falha de segmentação..."};
    private String[] falasAtaque = {"Sua memória é minha!", "Loop infinito ativado!", "Esqueceu do Garbage Collector?"};
    private Random rand = new Random();

    public Enemy2() {
        super(220,25,55);
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
