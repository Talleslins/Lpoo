package Questões;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank {
    private ArrayList<Question> perguntas = new ArrayList<>();
    Scanner scanner = null;

    public void lerArquivo(){
        File file = new File("src/banco_questoes_lpoo_expandido.txt");
        String linha;
        try {
            scanner = new Scanner(file);


            Question novaPergunta = null;

            while (scanner.hasNextLine()) {
                linha = scanner.nextLine().trim();

                // Ignora linhas em branco
                if (linha.isEmpty()) {
                    continue;
                }


                if (linha.equals("---")) {
                    if (novaPergunta != null) {
                        perguntas.add(novaPergunta);
                        novaPergunta = null;
                    }
                    continue;
                }


                if (linha.startsWith("TIPO:")) {
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
                perguntas.add(novaPergunta);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public ArrayList<Question> getPerguntas() {
        return perguntas;
    }
}