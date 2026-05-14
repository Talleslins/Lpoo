package Personagem;

public class Cientista extends Personagem.Character {

    public Cientista() {
        super(100, 40, 60);
    }
    @Override
    public void atacar(Character alvo){
        int dano = this.getAtaque();

        alvo.receberDano(dano);

    }
}
