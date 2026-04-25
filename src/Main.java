import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    QuestionBank bancoQuestoes = new QuestionBank();
    bancoQuestoes.lerArquivo();
    ArrayList<Question> questoes = bancoQuestoes.getPerguntas();

    questoes.get(1).exibirPergunta();
    String r = scanner.nextLine();
    questoes.get(1).validarResposta(r);
    System.out.println("\n");

    questoes.get(2).exibirPergunta();
    r = scanner.nextLine();
    questoes.get(2).validarResposta(r);
    System.out.println("\n");
}
