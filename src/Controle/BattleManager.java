package Controle;

import Personagem.Enemy;
import Personagem.Player;
import Questões.Question;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleManager {
    private Player jogador;
    private Enemy inimigo;
    private ArrayList<Round> historicoRodadas; // [cite: 51]

    public BattleManager(Player jogador, Enemy inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.historicoRodadas = new ArrayList<>();
    }

    public boolean iniciarCombate(ArrayList<Question> questoes, Scanner scanner) {
        int indice = 0;
        
        while (jogador.getPersonagemEscolhido().getVida() > 0 && 
               inimigo.getVida() > 0 && 
               indice < questoes.size()) {

            System.out.println("\n---- STATUS ----");
            System.out.println("Jogador: " + jogador.getPersonagemEscolhido().getVida());
            System.out.println("Inimigo: " + inimigo.getVida());

            // Delega a responsabilidade do turno para a classe Round 
            Round rodadaAtual = new Round(questoes.get(indice), jogador, inimigo);
            rodadaAtual.executar(scanner);
            historicoRodadas.add(rodadaAtual);
            
            indice++;
        }
        return inimigo.getVida() <= 0; // Retorna se o jogador venceu [cite: 55]
    }
}
