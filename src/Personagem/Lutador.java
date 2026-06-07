package Personagem;

public class Lutador extends Personagem.Character implements Furia {


    public Lutador(int vida, int defesa, int ataque) {
        super(100, 20,30);
    }

    @Override
    public void atacar(Character alvo){
       int dano = this.getAtaque();

       alvo.receberDano(dano);

    }
    @Override
    public void ativarHabilidade(Character alvo) {
        int dano = this.getAtaque()*2;
        alvo.receberDano(dano);
    }
}

