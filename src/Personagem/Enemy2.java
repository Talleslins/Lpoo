package Personagem;

    public class Enemy2 extends Personagem.Character {

        public Enemy2() {
            super(300, 40, 60);
        }
        @Override
        public void atacar(Character alvo){
            int dano = this.getAtaque();

            alvo.receberDano(dano);

        }
    }



