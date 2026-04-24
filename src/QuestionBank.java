import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank
{
    private ArrayList<Question> perguntas = new ArrayList<>();
    Scanner scanner = null;


    void lerArquivo(){

        File file = new File("src/banco_questoes_lpoo_final.txt");
        String linha;
        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                Question novaPergunta = null;
                linha = scanner.nextLine();
                if(linha.startsWith("TIPO")){
                    String[] partes = linha.split(":");
                    switch(partes[1].trim()){
                        case "MULTIPLA_ESCOLHA":
                            novaPergunta = new MultipleChoice();
                            break;
                        case "VERDADEIRO_FALSO":
                            novaPergunta = new TrueFalse();
                            break;

                    }
                    String[] dif = scanner.nextLine().split(":");
                    String[] enu = scanner.nextLine().split(":");
                    String[] resp = scanner.nextLine().split(":");
                    String linha1 = scanner.nextLine();
                    if(linha1.startsWith("OPCOES")){
                        String[] opc = linha1.split(":");
                        novaPergunta.setopcoes(opc[1].trim());
                    }
                    else{
                            linha1 = null;
                    }
                    //bloco de montagem de objeto
                    novaPergunta.setdificuldade(dif[1].trim());
                    novaPergunta.setenunciado(enu[1].trim());
                    novaPergunta.setresposta(resp[1].trim());
                    perguntas.add(novaPergunta);

                }

            }

        } catch (IOException e) {    
            System.out.println("Error: " + e.getMessage());
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
