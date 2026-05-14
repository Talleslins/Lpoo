package Controle;

import Personagem.Enemy;
import Personagem.Player;
import Questões.Question;

import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    private Player jogador;
    private Enemy inimigo;
    private ArrayList<Question> questoes;
    private Scanner scanner;

    public Round(Player jogador, ArrayList<Question> questoes, Scanner scanner) {
        this.jogador = jogador;
        this.questoes = questoes;
        this.scanner = scanner;


        this.inimigo = new Enemy();
    }

    public boolean jogar() {

        BattleManager manager = new BattleManager(jogador, inimigo);
        manager.iniciarCombate(questoes, scanner);
        return jogador.getPersonagemEscolhido().getVida() > 0;
    }
}