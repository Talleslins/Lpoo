public class Enemy extends Character {

    public Enemy() {
        super(100, 100, 100);
    }
        @Override
        public void atacar(Character alvo){
            int dano = this.getAtaque();

            receberDano(dano);

        }
}

