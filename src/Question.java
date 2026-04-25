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

    abstract void exibirPergunta();

    boolean validarResposta(String r) {
        if (r.equalsIgnoreCase(this.Resposta)) {
            System.out.println(">>Resposta correta!");
            System.out.print("--------------");
            return true;
        } else {
            System.out.println(">>Resposta incorreta.");
            System.out.print("--------------");
            return false;
        }
    }

}
