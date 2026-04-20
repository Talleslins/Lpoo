 public abstract class Character {
     private int vida;
     private int defesa;
     private int ataque;

     public Character(int vida, int defesa, int ataque) {
         this.vida = vida;
         this.defesa = defesa;
         this.ataque = ataque;
     }

     public void receberDano(int dano){
         int danoCalculado = dano - this.defesa;
         if(danoCalculado>=0) vida -= danoCalculado;
         else vida -= 1;
     }
     public abstract void atacar(Character alvo);

     public int getAtaque() {
         return ataque;
     }

     public int getDefesa() {
         return defesa;
     }
 }
