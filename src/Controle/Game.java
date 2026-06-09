package Controle;

import Personagem.Cientista;
import Personagem.Lutador;
import Personagem.Player;
import Personagem.Character;
import Questões.Question;
import Questões.QuestionBank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private Player jogador;
    private QuestionBank bancoQuestoes;
    private Scanner scanner;
    private int roundAtual;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.bancoQuestoes = new QuestionBank();
        this.roundAtual = 1;
    }

    public void iniciar() {
        System.out.println("===================================");
        System.out.println("     BEM-VINDO AO JOGO DE LPOO!    ");
        System.out.println("===================================");
        bancoQuestoes.lerArquivo();

        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine();

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Cientista (Ataque: 60 | Defesa: 100 | Vida: 100)");
        System.out.println("2 - Lutador (Ataque: 30 | Defesa: 20 | Vida: 100)");
        System.out.print("Sua opção: ");
        String opcaoClasse = scanner.nextLine();

        Character personagemEscolhido;
        if (opcaoClasse.equals("2")) {
            personagemEscolhido = new Lutador(100, 20, 30);
        } else {
            personagemEscolhido = new Cientista();
        }

        jogador = new Player(nome, 0, personagemEscolhido);
        System.out.println("\nJogador " + nome + " criado com sucesso!");

        boolean jogando = true;
        while (jogando && jogador.getPersonagemEscolhido().getVida() > 0) {
            System.out.println("\n===================================");
            System.out.println("          INICIANDO ROUND " + roundAtual);
            System.out.println("===================================");

            String chaveDeDificuldade;
            switch(roundAtual) {
                case 1:
                    chaveDeDificuldade = "FACIL";
                    break;
                case 2:
                    chaveDeDificuldade = "MEDIA";
                    break;
                default:
                    chaveDeDificuldade = "DIFICIL";
                    break;
            }

            ArrayList<Question> listaOriginal = bancoQuestoes.getQuestoes(chaveDeDificuldade);
            if (listaOriginal == null || listaOriginal.isEmpty()) {
                System.out.println("Erro: Nenhuma questão carregada para a dificuldade " + chaveDeDificuldade);
                return;
            }

            ArrayList<Question> questoesDoRound = new ArrayList<>(listaOriginal);
            Collections.shuffle(questoesDoRound);

            ArrayList<Question> questoesBatalha = new ArrayList<>();
            int limite = Math.min(50, questoesDoRound.size());
            for(int i = 0; i < limite; i++) {
                questoesBatalha.add(questoesDoRound.get(i));
            }

            Round round = new Round(jogador, questoesBatalha, scanner);
            boolean sobreviveu = round.jogar();

            if (sobreviveu) {
                System.out.println("\nVocê sobreviveu ao Round " + roundAtual + "!");
                roundAtual++;

                System.out.print("Deseja avançar para o próximo inimigo? (s/n): ");
                String continuar = scanner.nextLine();
                if (!continuar.equalsIgnoreCase("s")) {
                    jogando = false;
                    System.out.println("\nVocê decidiu se retirar com glória. Fim de jogo!");
                }
            } else {
                jogando = false;
                System.out.println("\nFim de jogo! Você chegou até o Round " + roundAtual + ".");
            }
        }

        System.out.println("Obrigado por jogar!");
        scanner.close();
    }
}