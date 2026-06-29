package Controle;

import java.io.*;
import java.util.*;

public class RankingManager {
    private static final String ARQUIVO_RANKING = "ranking.txt";

    public static void salvarPontuacao(String nome, int pontuacao) {
        try (FileWriter fw = new FileWriter(ARQUIVO_RANKING, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(nome + "," + pontuacao);
        } catch (IOException e) {
            System.out.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }

    public static List<String> obterRankingTop5() {
        List<JogadorScore> scores = new ArrayList<>();
        try {
            File file = new File(ARQUIVO_RANKING);
            if (!file.exists()) return new ArrayList<>();

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    scores.add(new JogadorScore(partes[0].trim(), Integer.parseInt(partes[1].trim())));
                }
            }
            scanner.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler ranking: " + e.getMessage());
        }

        // Ordena do maior para o menor
        scores.sort((s1, s2) -> Integer.compare(s2.pontuacao, s1.pontuacao));

        List<String> rankingFormatado = new ArrayList<>();
        int limite = Math.min(5, scores.size()); // Pega apenas os 5 melhores
        for (int i = 0; i < limite; i++) {
            rankingFormatado.add((i + 1) + "º - " + scores.get(i).nome + " (" + scores.get(i).pontuacao + " pts)");
        }
        return rankingFormatado;
    }

    // Classe auxiliar interna para facilitar a ordenação
    private static class JogadorScore {
        String nome;
        int pontuacao;
        JogadorScore(String nome, int pontuacao) {
            this.nome = nome;
            this.pontuacao = pontuacao;
        }
    }
}