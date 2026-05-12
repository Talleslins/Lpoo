package Personagem;

public class Lutador extends Personagem.Character {


    public Lutador(int vida, int defesa, int ataque) {
        super(100, 20,30);
    }

    @Override
    public void atacar(Character alvo){
       int dano = (int) (this.getAtaque()*1.5);

       alvo.receberDano(dano);

    }
}
