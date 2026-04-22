import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class QuestionBank
{



    void lerArquivo(){

        File file = new File("src/questoes_lpoo_jogo_v2.txt");
        Scanner scanner = null;
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


                    }
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
}
