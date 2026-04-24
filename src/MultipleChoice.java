public class MultipleChoice extends Question {
  protected String Opcoes;

    public MultipleChoice(String Opcoes) {
        this.Opcoes = Opcoes;
    }

    void setopcoes(String o) { Opcoes = o; }

    @Override
    String exibirPergunta() {
        System.out.println("--------------");
        System.out.println(this.Enunciado);

    }
}
