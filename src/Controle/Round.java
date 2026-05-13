package Controle;

import Personagem.Player;
import Personagem.Enemy;
import Questões.Question;
import java.util.Scanner;

public class Round {
    private Question questao;
    private Player jogador;
    private Enemy inimigo;

    public Round(Question questao, Player jogador, Enemy inimigo) {
        this.questao = questao;
        this.jogador = jogador;
        this.inimigo = inimigo;
    }

    public void executar(Scanner scanner) {
        questao.exibirPergunta(); // [cite: 65, 77]
        String resposta = scanner.nextLine();
        
        if (questao.validarResposta(resposta)) { // [cite: 66, 78]
            System.out.println("\nParabéns! Você acertou!");
            aplicarEfeitos(true);
        } else {
            System.out.println("\nVocê errou! A resposta era: " + questao.getResposta());
            aplicarEfeitos(false);
        }
    }

    private void aplicarEfeitos(boolean acertou) { // [cite: 67]
        if (acertou) {
            jogador.getPersonagemEscolhido().atacar(inimigo);
        } else {
            inimigo.atacar(jogador.getPersonagemEscolhido());
        }
    }
}
