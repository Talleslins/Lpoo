public class MultipleChoice extends Question {
  protected String Opcoes;

    public MultipleChoice() {
        super("", "", "");
    }

    public MultipleChoice(String Opcoes) {
        super("", "", ""); // Necessário chamar o super aqui também
        this.Opcoes = Opcoes;
    }
    public void setopcoes(String o) {
        this.Opcoes = o;
    }
    @Override
    String exibirPergunta() {
        System.out.println("--------------");
        System.out.println(this.Enunciado);
        return "";
    }
    @Override
    boolean validarResposta(String r) {
        return this.Resposta.equalsIgnoreCase(r);
    }
}
