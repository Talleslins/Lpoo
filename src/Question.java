public abstract class Question {
    protected String Enunciado;
    protected String Dificuldade;
    protected String Resposta;

    public Question(String Enunciado, String Dificuldade, String Resposta) {
        this.Enunciado = Enunciado;
        this.Dificuldade = Dificuldade;
        this.Resposta = Resposta;
    }

    void setenunciado(String e) { Enunciado = e; }
    void setdificuldade(String d) { Dificuldade = d; }
    void setresposta(String r) { Resposta = r; }

    abstract String exibirPergunta();

    abstract boolean validarResposta(String r);

}
