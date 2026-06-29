package Personagem;

public class Lutador extends Personagem.Character implements Furia {
    private boolean habilidadeDisponivel;

    public Lutador() {
        // Status atualizado: Tanker e Brawler (Vida Alta, Defesa Média, Ataque Forte)
        super(200, 30, 80);
        this.habilidadeDisponivel = true;
    }

    @Override
    public void atacar(Character alvo){
        int dano = this.getAtaque();
        alvo.receberDano(dano);
    }

    @Override
    public void ativarHabilidade(Character alvo) {
        if(habilidadeDisponivel) {
            // Golpe esmagador: Dobra o ataque e anula a defesa do alvo no cálculo
            int danoFuria = this.getAtaque() * 2;
            alvo.receberDano(danoFuria + alvo.getDefesa());
            this.habilidadeDisponivel = false;
        }
    }

    public boolean isHabilidadeDisponivel() { return habilidadeDisponivel; }
    public void resetarHabilidade() { this.habilidadeDisponivel = true; }
}