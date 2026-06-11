package Questões;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class QuestionBank {
    private Map<String,ArrayList<Question>> questoesPorDificuldade = new HashMap<>();
    Scanner scanner = null;

    public void lerArquivo(){
        File file = new File("src/banco_questoes_lpoo_expandido.txt");
        String linha;
        try {
            scanner = new Scanner(file);
            Question novaPergunta = null;

            while (scanner.hasNextLine()) {
                linha = scanner.nextLine().trim();

                if (linha.isEmpty()) {
                    continue;
                }

                if (linha.startsWith("TIPO:")) {
                    if (novaPergunta != null) {
                        adicionarAoMapa(novaPergunta);
                    }

                    String tipo = linha.replace("TIPO:", "").trim();

                    switch (tipo) {
                        case "MULTIPLA_ESCOLHA":
                            novaPergunta = new MultipleChoice();
                            break;
                        case "VERDADEIRO_FALSO":
                            novaPergunta = new TrueFalse();
                            break;
                        case "PREENCHER_LACUNA":
                            novaPergunta = new FillTheBlank();
                            break;
                        default:
                            System.out.println("Tipo de questão desconhecido: " + tipo);
                            break;
                    }
                }
                else if (novaPergunta != null) {
                    if (linha.startsWith("DIFICULDADE:")) {
                        novaPergunta.setdificuldade(linha.replace("DIFICULDADE:", "").trim());
                    } else if (linha.startsWith("PERGUNTA:")) {
                        novaPergunta.setenunciado(linha.replace("PERGUNTA:", "").trim());
                    } else if (linha.startsWith("RESPOSTA:")) {
                        novaPergunta.setresposta(linha.replace("RESPOSTA:", "").trim());
                    } else if (linha.startsWith("OPCOES:")) {
                        if (novaPergunta instanceof MultipleChoice) {
                            ((MultipleChoice) novaPergunta).setopcoes(linha.replace("OPCOES:", "").trim());
                        }
                    }
                }
            }

            if (novaPergunta != null) {
                adicionarAoMapa(novaPergunta);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private void adicionarAoMapa(Question pergunta) {
        String dificuldade = pergunta.getDificuldade();
        if (!questoesPorDificuldade.containsKey(dificuldade)) {
            questoesPorDificuldade.put(dificuldade, new ArrayList<>());
        }
        questoesPorDificuldade.get(dificuldade).add(pergunta);
    }

    public ArrayList<Question> getQuestoes(String dificuldade) {
        return questoesPorDificuldade.get(dificuldade);
    }
}