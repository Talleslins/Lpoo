public class Cientista extends Character {

    public Cientista() {
        super(60, 100, 60);
    }
    @Override
    public void atacar(Character alvo){
        int dano = this.getAtaque();

        receberDano(dano);

    }
}
