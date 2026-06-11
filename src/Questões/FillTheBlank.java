package Questões;

public class FillTheBlank extends Question {
    public FillTheBlank() {super("","","");}

    @Override
    public void exibirPergunta(){
        System.out.println("--------------");
        System.out.println(this.Enunciado+"\n");
        System.out.print("Qual termo preenche a lacuna? ");
    }
}
