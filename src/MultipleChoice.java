public class MultipleChoice extends Question {
    protected String Opcoes;

    public MultipleChoice() {
        super("","","");
    }

    public MultipleChoice(String Opcoes) {
        super("","","");
        this.Opcoes = Opcoes;
    }

    public void setopcoes(String o) {
        this.Opcoes = o;
    }

    @Override
    void exibirPergunta() {
        System.out.println("--------------");
        System.out.println(this.Enunciado+"\n");
        String[] arrOpcoes = Opcoes.split(";");
        for (String opcao : arrOpcoes) {
            System.out.println(opcao.trim());
        }
        System.out.print("\nEscolha uma das opções: ");
    }

}
