package Controle;

import Personagem.Cientista;
import Personagem.Enemy;
import Personagem.Player;
import Questões.QuestionBank;
import java.util.Scanner;

public class Game {
    private Player player;
    private BattleManager battleManager; // [cite: 41]

    public void iniciarJogo() { // [cite: 44]
        Scanner scanner = new Scanner(System.in);
        this.player = new Player("Talles", 0, new Cientista());
        Enemy inimigo = new Enemy();
        
        QuestionBank banco = new QuestionBank();
        banco.lerArquivo();
        
        this.battleManager = new BattleManager(player, inimigo);
        
        System.out.println("Bem-vindo ao Character Battle!");
        boolean vitoria = battleManager.iniciarCombate(banco.getPerguntas(), scanner);
        
        if (vitoria) {
            System.out.println("Vitória Geral!");
        } else {
            System.out.println("Game Over!");
        }
    }
}
