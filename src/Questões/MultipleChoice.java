package Questões;

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
    public void exibirPergunta() {
        System.out.println("--------------");
        System.out.println(this.Enunciado+"\n");
        String[] arrOpcoes = Opcoes.split(";");
        for (String opcao : arrOpcoes) {
            if (!opcao.contains("[ELIMINADA]")) {
                System.out.println(opcao.trim());
            }
        }
        System.out.print("\nEscolha uma das opções: ");
    }

    public boolean eliminarUmaFalsa() {
        String[] arrOpcoes = Opcoes.split(";");
        boolean eliminou = false;

        for (int i = 0; i < arrOpcoes.length; i++) {
            String opcaoTratada = arrOpcoes[i].trim();

            if (opcaoTratada.isEmpty() || opcaoTratada.contains("[ELIMINADA]")) {
                continue;
            }

            if (!opcaoTratada.startsWith(this.getResposta())) {
                arrOpcoes[i] = arrOpcoes[i] + " [ELIMINADA]";
                eliminou = true;
                break;
            }
        }

        if (eliminou) {
            StringBuilder novaOpcoes = new StringBuilder();
            for (int i = 0; i < arrOpcoes.length; i++) {
                novaOpcoes.append(arrOpcoes[i]);
                if (i < arrOpcoes.length - 1) {
                    novaOpcoes.append(";");
                }
            }
            this.Opcoes = novaOpcoes.toString();
        }

        return eliminou;
    }
    public String[] getOpcoesArray() {
        return Opcoes.split(";");
    }
}
