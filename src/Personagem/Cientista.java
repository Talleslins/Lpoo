package Personagem;

import Questões.Question;
import Questões.MultipleChoice;

public class Cientista extends Personagem.Character implements Intelecto {
    private boolean habilidadeDisponivel;

    public Cientista() {
        super(120, 20, 60);
        this.habilidadeDisponivel = true;
    }

    @Override
    public void atacar(Character alvo) {
        int dano = this.getAtaque();
        alvo.receberDano(dano);
    }

    @Override
    public void ativarHabilidade(Question questaoAtual) {
        if (!habilidadeDisponivel) {
            System.out.println("Você já usou seu Intelecto neste combate!");
            return;
        }

        if (questaoAtual instanceof MultipleChoice) {
            MultipleChoice mc = (MultipleChoice) questaoAtual;
            boolean sucesso = mc.eliminarUmaFalsa();

            if (sucesso) {
                System.out.println("\n[HABILIDADE CIENTÍFICA] Intelecto ativado! Analisando os dados e descartando uma alternativa falsa...");
                this.habilidadeDisponivel = false;
            } else {
                System.out.println("Não há alternativas incorretas remanescentes para eliminar.");
            }
        } else {
            System.out.println("O Intelecto só funciona em questões de Múltipla Escolha!");
        }
    }

    public boolean isHabilidadeDisponivel() {
        return habilidadeDisponivel;
    }

    public void resetarHabilidade() {
        this.habilidadeDisponivel = true;
    }
}
