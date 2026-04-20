

public class Lutador extends Character {

    public Lutador(int vida, int defesa, int ataque) {
        super(100, 50, 200);
    }
    @Override
    public void atacar(Character alvo){
       int dano = (int) (this.getAtaque()*1.5);

       receberDano(dano);

    }
}
