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
        ArrayList<Question> questoes = bancoQuestoes.getPerguntas();


        Collections.shuffle(questoes);

        if (questoes.isEmpty()) {
            System.out.println("Erro: Nenhuma questão carregada. Verifique o arquivo txt.");
            return;
        }


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


            Round round = new Round(jogador, questoes, scanner);
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