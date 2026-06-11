package Controle;

import Personagem.Enemy;
import Personagem.Player;
import Personagem.Cientista;
import Questões.Question;
import Questões.MultipleChoice;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleManager {
    private Player jogador;
    private Enemy inimigo;

    public BattleManager(Player jogador, Enemy inimigo){
        this.jogador = jogador;
        this.inimigo = inimigo;
    }

    public void iniciarCombate(ArrayList<Question> questoes, Scanner scanner){
        int vidaAtualPlayer = jogador.getPersonagemEscolhido().getVida();
        int vidaAtualEnemy = inimigo.getVida();

        if (jogador.getPersonagemEscolhido() instanceof Cientista) {
            ((Cientista) jogador.getPersonagemEscolhido()).resetarHabilidade();
        }

        while(vidaAtualPlayer > 0 && vidaAtualEnemy > 0 && !questoes.isEmpty()){

            System.out.println("----NOVO TURNO----");
            System.out.println("Sua vida: " + vidaAtualPlayer);
            System.out.println("Vida do inimigo: " + vidaAtualEnemy);
            Question perguntaAtual = questoes.remove(0);

            if (jogador.getPersonagemEscolhido() instanceof Cientista && perguntaAtual instanceof MultipleChoice) {
                Cientista cientista = (Cientista) jogador.getPersonagemEscolhido();

                if (cientista.isHabilidadeDisponivel()) {
                    System.out.println("\n[HABILIDADE] Você pode usar o Intelecto para eliminar 1 alternativa incorreta.");
                    System.out.print("Deseja ativar agora? (s/n): ");
                    String opcao = scanner.nextLine();
                    if (opcao.equalsIgnoreCase("s")) {
                        cientista.ativarHabilidade(perguntaAtual);
                    }
                }
            }
            perguntaAtual.exibirPergunta();

            String resposta = scanner.nextLine();

            if(perguntaAtual.validarResposta(resposta)){
                System.out.println("\nParabéns você acertou a pergunta!!!\naplicando dano ao inimigo...");
                jogador.getPersonagemEscolhido().atacar(inimigo);
            }
            else{
                System.out.println("\nVocê errou a pergunta :(" +"\nResposta: "+ (perguntaAtual.getResposta()) + "\nO inimigo aproveitou a oportunidade para lhe atacar.");
                inimigo.atacar(jogador.getPersonagemEscolhido());
            }

            vidaAtualPlayer = jogador.getPersonagemEscolhido().getVida();
            vidaAtualEnemy = inimigo.getVida();
        }

        if(vidaAtualPlayer <= 0){
            System.out.println("Você foi derrotado!!!");
        }
        if(vidaAtualEnemy <= 0){
            System.out.println("Parabéns, voce ganhou!!!");
        }
    }
}
