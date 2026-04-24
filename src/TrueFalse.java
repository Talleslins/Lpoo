public class TrueFalse extends Question{
    public TrueFalse() {
        super("", "", "");
    }

    @Override
    String exibirPergunta() {
        return "";
    }

    @Override
    boolean validarResposta(String r) {
        return false;
    }
}
