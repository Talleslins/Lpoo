package Personagem;

public class Lutador extends Personagem.Character implements Furia {


    public Lutador(int vida, int defesa, int ataque) {
        super(180, 40,70);
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

