import Controle.BattleManager;
import Personagem.Cientista;
import Personagem.Enemy;
import Personagem.Player;
import Questões.Question;
import Questões.QuestionBank;

import java.util.ArrayList;
import java.util.Scanner;

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Player jogador = new Player("talles",0, new Cientista());
    Enemy inimigo = new Enemy();
    QuestionBank bancoQuestoes = new QuestionBank();
    bancoQuestoes.lerArquivo();
    ArrayList<Question> questoes = bancoQuestoes.getPerguntas();

    BattleManager manager = new BattleManager(jogador,inimigo);

    manager.iniciarCombate(questoes,scanner);
}

