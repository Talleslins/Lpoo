public class Enemy extends Character {

    public Enemy() {
        super(300, 10, 60);
    }
        @Override
        public void atacar(Character alvo){
            int dano = this.getAtaque();

            alvo.receberDano(dano);

        }
}

