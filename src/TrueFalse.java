public class TrueFalse extends Question {
    public TrueFalse() {
        super("","","");
    }

    @Override
    void exibirPergunta() {
        System.out.println("--------------");
        System.out.println(this.Enunciado+"\n");
        System.out.print("Informe se o enunciado é verdadeiro ou falso [v/f]: ");
    }
}
