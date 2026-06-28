package Personagem;
import java.util.Random;

public class Enemy3 extends Character {
    private String nome = "Senhor do Código Legado (BOSS)";
    private String[] falasDano = {
        "Meu sistema monolítico está desmoronando!", 
        "Você encontrou minha vulnerabilidade zero-day!", 
        "Impossível... um refactoring perfeito!"
    };
    private String[] falasAtaque = {
        "Sinta a fúria de 10.000 linhas sem documentação!", 
        "Experimente este vazamento de memória massivo!", 
        "Prepare-se para o Deadlock eterno!"
    };
    private Random rand = new Random();

    public Enemy3() {
        // Status do Boss: Muita Vida, Defesa Alta e Ataque Devastador
        super(400, 35, 75); 
    }

    @Override
    public void atacar(Character alvo){
        int dano = this.getAtaque();
        alvo.receberDano(dano);
    }

    public String getNome() { return nome; }
    
    public String falar(boolean sofreuDano) {
        if(sofreuDano) return falasDano[rand.nextInt(falasDano.length)];
        return falasAtaque[rand.nextInt(falasAtaque.length)];
    }
}