package Controle;

import Personagem.Cientista;
import Personagem.Enemy;
import Personagem.Enemy2;
import Personagem.Enemy3; // Importando o Boss
import Personagem.Lutador;
import Personagem.Player;
import Questões.FillTheBlank;
import Questões.MultipleChoice;
import Questões.Question;
import Questões.QuestionBank;
import Questões.TrueFalse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Player jogador;
    private Personagem.Character inimigoAtual;
    private QuestionBank bancoQuestoes;
    private ArrayList<Question> questoesBatalha;
    private Question perguntaAtual;
    private int roundAtual = 1;

    // Controle da Habilidade do Lutador
    private boolean furiaPendente = false;

    // Elementos da Interface
    private JLabel lblEnunciado;
    private JPanel panelOpcoes;
    private JLabel lblVidaPlayer;
    private JLabel lblVidaInimigo;
    private JLabel lblPontuacao;
    private JLabel lblStatus;
    private JLabel lblDialogoInimigo;
    private JButton btnHabilidade;

    public GameGUI() {
        setTitle("CodeArena: Batalha do Conhecimento");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bancoQuestoes = new QuestionBank();
        bancoQuestoes.lerArquivo();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(criarPainelMenu(), "Menu");
        mainPanel.add(criarPainelTutorial(), "Tutorial");
        mainPanel.add(criarPainelBatalha(), "Batalha");
        mainPanel.add(criarPainelFim(), "GameOver");

        add(mainPanel);
    }

    private JPanel criarPainelMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel lblTitulo = new JLabel("CODE ARENA: Batalha do Conhecimento");
        lblTitulo.setForeground(Color.CYAN);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(lblTitulo, gbc);

        gbc.gridy++;
        JTextField txtNome = new JTextField(15);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(new JLabel("<html><font color='white'>Digite seu nome:</font></html>"), gbc);
        gbc.gridy++;
        panel.add(txtNome, gbc);

        gbc.gridy++;
        String[] classes = {"Cientista (Intelecto)", "Lutador (Fúria)"};
        JComboBox<String> cbClasses = new JComboBox<>(classes);
        panel.add(cbClasses, gbc);

        gbc.gridy++;
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.setOpaque(false);

        JButton btnTutorial = new JButton("TUTORIAL");
        btnTutorial.setBackground(Color.ORANGE);
        btnTutorial.setFont(new Font("Arial", Font.BOLD, 18));
        btnTutorial.addActionListener(e -> cardLayout.show(mainPanel, "Tutorial"));

        JButton btnIniciar = new JButton("INICIAR BATALHA");
        btnIniciar.setBackground(Color.GREEN);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 18));
        btnIniciar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) nome = "Herói";

            Personagem.Character p = (cbClasses.getSelectedIndex() == 0) ? new Cientista() : new Lutador();
            jogador = new Player(nome, 0, p);

            // Reseta caso o jogador decida jogar novamente sem fechar a janela
            roundAtual = 1;

            iniciarRound();
            cardLayout.show(mainPanel, "Batalha");
        });

        panelBotoes.add(btnTutorial);
        panelBotoes.add(btnIniciar);
        panel.add(panelBotoes, gbc);

        return panel;
    }

    private JPanel criarPainelTutorial() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(30, 30, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblTitulo = new JLabel("📚 Manual de Batalha", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.CYAN);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(lblTitulo, BorderLayout.NORTH);

        String textoTutorial = "<html><font size='5' color='white'>" +
                "<b>Objetivo:</b> Sobreviva às batalhas respondendo perguntas de LPOO corretamente.<br><br>" +
                "<b>Mecânicas de Combate:</b><br>" +
                "• <font color='green'>Acertar</font> a pergunta faz seu personagem atacar o inimigo.<br>" +
                "• <font color='red'>Errar</font> a pergunta deixa uma brecha para o inimigo atacar você.<br><br>" +
                "<b>Habilidades Especiais (1 uso por batalha):</b><br>" +
                "• <b>🧠 Intelecto (Cientista):</b> Ative para eliminar imediatamente 1 alternativa falsa (Apenas questões de múltipla escolha).<br>" +
                "• <b>🔥 Fúria (Lutador):</b> Ative <i>antes</i> de responder. Se você acertar a questão, desfere um acerto crítico letal que ignora a defesa do inimigo. Se você errar, o ataque falha e a habilidade é desperdiçada.<br><br>" +
                "<b>Progressão:</b><br>" +
                "O jogo possui oponentes cada vez mais fortes. Derrote o Boss no 3º Round para se consagrar campeão e gravar seu nome no Ranking Global!" +
                "</font></html>";

        JLabel lblTexto = new JLabel(textoTutorial);
        panel.add(lblTexto, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.setBackground(Color.GRAY);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        JPanel panelSul = new JPanel();
        panelSul.setOpaque(false);
        panelSul.add(btnVoltar);
        panel.add(panelSul, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelBatalha() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 40, 50));

        JPanel panelStatus = new JPanel(new GridLayout(1, 3));
        panelStatus.setBackground(new Color(20, 20, 30));

        lblVidaPlayer = new JLabel("Sua Vida: 100", SwingConstants.CENTER);
        lblVidaPlayer.setForeground(Color.GREEN);
        lblVidaPlayer.setFont(new Font("Arial", Font.BOLD, 18));

        lblPontuacao = new JLabel("Pontos: 0", SwingConstants.CENTER);
        lblPontuacao.setForeground(Color.YELLOW);
        lblPontuacao.setFont(new Font("Arial", Font.BOLD, 20));

        lblVidaInimigo = new JLabel("Vida Inimigo: 100", SwingConstants.CENTER);
        lblVidaInimigo.setForeground(Color.RED);
        lblVidaInimigo.setFont(new Font("Arial", Font.BOLD, 18));

        panelStatus.add(lblVidaPlayer);
        panelStatus.add(lblPontuacao);
        panelStatus.add(lblVidaInimigo);
        panel.add(panelStatus, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);

        lblEnunciado = new JLabel("Pergunta aparecerá aqui", SwingConstants.CENTER);
        lblEnunciado.setForeground(Color.WHITE);
        lblEnunciado.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel panelStatusCentro = new JPanel(new GridLayout(2, 1));
        panelStatusCentro.setOpaque(false);

        lblDialogoInimigo = new JLabel("", SwingConstants.CENTER);
        lblDialogoInimigo.setForeground(new Color(255, 100, 100));
        lblDialogoInimigo.setFont(new Font("Consolas", Font.BOLD, 18));

        lblStatus = new JLabel("Prepare-se!", SwingConstants.CENTER);
        lblStatus.setForeground(Color.YELLOW);
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 16));

        panelStatusCentro.add(lblDialogoInimigo);
        panelStatusCentro.add(lblStatus);

        panelCentro.add(lblEnunciado, BorderLayout.CENTER);
        panelCentro.add(panelStatusCentro, BorderLayout.SOUTH);
        panel.add(panelCentro, BorderLayout.CENTER);

        JPanel panelSul = new JPanel(new BorderLayout());
        panelOpcoes = new JPanel(new GridLayout(0, 1, 5, 5));
        panelOpcoes.setOpaque(false);

        btnHabilidade = new JButton("Ativar Habilidade Especial");
        btnHabilidade.setBackground(new Color(138, 43, 226));
        btnHabilidade.setForeground(Color.WHITE);
        btnHabilidade.setFont(new Font("Arial", Font.BOLD, 14));
        btnHabilidade.addActionListener(e -> ativarHabilidade());

        panelSul.add(panelOpcoes, BorderLayout.CENTER);
        panelSul.add(btnHabilidade, BorderLayout.SOUTH);
        panel.add(panelSul, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelFim() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(20, 20, 30));
        return panel;
    }

    private void iniciarRound() {
        String dificuldade = "FACIL";

        if (roundAtual == 1) {
            inimigoAtual = new Enemy();
        } else if (roundAtual == 2) {
            dificuldade = "MEDIA";
            inimigoAtual = new Enemy2();
        } else {
            // Round 3 - BOSS FINAL
            dificuldade = "DIFICIL";
            inimigoAtual = new Enemy3();
        }

        ArrayList<Question> todas = bancoQuestoes.getQuestoes(dificuldade);
        questoesBatalha = new ArrayList<>(todas);
        Collections.shuffle(questoesBatalha);

        furiaPendente = false;

        if (jogador.getPersonagemEscolhido() instanceof Cientista) {
            ((Cientista) jogador.getPersonagemEscolhido()).resetarHabilidade();
        } else if (jogador.getPersonagemEscolhido() instanceof Lutador) {
            ((Lutador) jogador.getPersonagemEscolhido()).resetarHabilidade();
        }

        lblStatus.setText("Round " + roundAtual + " Iniciado! Inimigo: " + obternomeInimigo() + " (" + dificuldade + ")");
        lblDialogoInimigo.setText("");

        carregarProximaPergunta();
    }

    private String obternomeInimigo() {
        if(inimigoAtual instanceof Enemy) return ((Enemy)inimigoAtual).getNome();
        if(inimigoAtual instanceof Enemy2) return ((Enemy2)inimigoAtual).getNome();
        if(inimigoAtual instanceof Enemy3) return ((Enemy3)inimigoAtual).getNome();
        return "Inimigo";
    }

    private void carregarProximaPergunta() {
        atualizarStatusVida();

        // Checa se o Jogador morreu
        if (jogador.getPersonagemEscolhido().getVida() <= 0) {
            finalizarJogo(false);
            return;
        }

        // Checa se o Inimigo morreu
        if (inimigoAtual.getVida() <= 0) {
            jogador.adicionarPontos(100 * roundAtual); // Bônus escalonado por matar o inimigo

            if (roundAtual == 3) {
                // MATOU O BOSS - ZEROU O JOGO!
                finalizarJogo(true);
            } else {
                // Passa para o próximo inimigo
                roundAtual++;
                iniciarRound();
            }
            return;
        }

        // Fim de jogo caso acabem as questões do banco
        if (questoesBatalha.isEmpty()) {
            finalizarJogo(true);
            return;
        }

        perguntaAtual = questoesBatalha.remove(0);
        lblEnunciado.setText("<html><div style='text-align: center; width: 600px;'>" + perguntaAtual.getEnunciado() + "</div></html>");

        btnHabilidade.setEnabled(false);
        if (jogador.getPersonagemEscolhido() instanceof Cientista && perguntaAtual instanceof MultipleChoice) {
            if (((Cientista) jogador.getPersonagemEscolhido()).isHabilidadeDisponivel()) {
                btnHabilidade.setText("🧠 Usar Intelecto (Remover Opção)");
                btnHabilidade.setEnabled(true);
            }
        } else if (jogador.getPersonagemEscolhido() instanceof Lutador) {
            if (((Lutador) jogador.getPersonagemEscolhido()).isHabilidadeDisponivel() && !furiaPendente) {
                btnHabilidade.setText("🔥 Preparar Fúria (Próximo Acerto = Dano Massivo)");
                btnHabilidade.setEnabled(true);
            }
        }
        montarPainelRespostas();
    }

    private void montarPainelRespostas() {
        panelOpcoes.removeAll();

        if (perguntaAtual instanceof MultipleChoice) {
            String[] opcoes = ((MultipleChoice) perguntaAtual).getOpcoesArray();
            for (String opcao : opcoes) {
                if (!opcao.contains("[ELIMINADA]")) {
                    JButton btn = new JButton(opcao.trim());
                    btn.addActionListener(e -> processarResposta(opcao.trim().substring(0, 1)));
                    panelOpcoes.add(btn);
                }
            }
        } else if (perguntaAtual instanceof TrueFalse) {
            JButton btnV = new JButton("Verdadeiro (V)");
            JButton btnF = new JButton("Falso (F)");
            btnV.addActionListener(e -> processarResposta("V"));
            btnF.addActionListener(e -> processarResposta("F"));
            panelOpcoes.add(btnV);
            panelOpcoes.add(btnF);
        } else if (perguntaAtual instanceof FillTheBlank) {
            JTextField txtResposta = new JTextField();
            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.addActionListener(e -> processarResposta(txtResposta.getText().trim()));
            panelOpcoes.add(txtResposta);
            panelOpcoes.add(btnConfirmar);
        }

        panelOpcoes.revalidate();
        panelOpcoes.repaint();
    }

    private void ativarHabilidade() {
        if (jogador.getPersonagemEscolhido() instanceof Cientista && perguntaAtual instanceof MultipleChoice) {
            Cientista cientista = (Cientista) jogador.getPersonagemEscolhido();
            cientista.ativarHabilidade(perguntaAtual);
            lblStatus.setText("<html><font color='cyan'>Intelecto ativado! Uma opção incorreta foi eliminada.</font></html>");
            montarPainelRespostas();
            btnHabilidade.setEnabled(false);
        } else if (jogador.getPersonagemEscolhido() instanceof Lutador) {
            furiaPendente = true;
            lblStatus.setText("<html><font color='orange'>FÚRIA PREPARADA! Acerte a questão para obliterar o inimigo!</font></html>");
            btnHabilidade.setEnabled(false);
        }
    }

    private void processarResposta(String resposta) {
        boolean acertou = perguntaAtual.getResposta().equalsIgnoreCase(resposta);

        String falaInimigo = "";
        if(inimigoAtual instanceof Enemy) falaInimigo = ((Enemy)inimigoAtual).falar(acertou);
        if(inimigoAtual instanceof Enemy2) falaInimigo = ((Enemy2)inimigoAtual).falar(acertou);
        if(inimigoAtual instanceof Enemy3) falaInimigo = ((Enemy3)inimigoAtual).falar(acertou);

        lblDialogoInimigo.setText(obternomeInimigo() + " diz: \"" + falaInimigo + "\"");

        if (acertou) {
            if (furiaPendente && jogador.getPersonagemEscolhido() instanceof Lutador) {
                lblStatus.setText("<html><font color='orange'>FÚRIA LIBERADA! ACERTO CRÍTICO NO INIMIGO!</font></html>");
                ((Lutador) jogador.getPersonagemEscolhido()).ativarHabilidade(inimigoAtual);
                furiaPendente = false;
            } else {
                lblStatus.setText("<html><font color='green'>Resposta Correta! Você causou dano.</font></html>");
                jogador.getPersonagemEscolhido().atacar(inimigoAtual);
            }
            jogador.adicionarPontos(15 * roundAtual);
        } else {
            if (furiaPendente && jogador.getPersonagemEscolhido() instanceof Lutador) {
                lblStatus.setText("<html><font color='red'>Miserável! Você errou e perdeu sua Fúria! Resposta correta: " + perguntaAtual.getResposta() + "</font></html>");
                ((Lutador) jogador.getPersonagemEscolhido()).ativarHabilidade(null);
                furiaPendente = false;
            } else {
                lblStatus.setText("<html><font color='red'>Errou! O Inimigo atacou. Resposta correta: " + perguntaAtual.getResposta() + "</font></html>");
            }
            inimigoAtual.atacar(jogador.getPersonagemEscolhido());
            jogador.adicionarPontos(-5);
        }

        for(Component c : panelOpcoes.getComponents()){
            c.setEnabled(false);
        }

        Timer timer = new Timer(2200, e -> {
            lblDialogoInimigo.setText("");
            carregarProximaPergunta();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void atualizarStatusVida() {
        lblVidaPlayer.setText("Sua Vida: " + Math.max(0, jogador.getPersonagemEscolhido().getVida()));
        lblPontuacao.setText("Pontos: " + jogador.getPontuacao());
        lblVidaInimigo.setText("Vida ("+obternomeInimigo()+"): " + Math.max(0, inimigoAtual.getVida()));
    }

    private void finalizarJogo(boolean vitoria) {
        RankingManager.salvarPontuacao(jogador.getNome(), jogador.getPontuacao());

        JPanel painelFim = (JPanel) mainPanel.getComponent(3);
        painelFim.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        String mensagem = vitoria ? "🏆 PARABÉNS! VOCÊ ZEROU O CODE ARENA! 🏆" : "💀 GAME OVER - VOCÊ FOI DERROTADO! 💀";
        JLabel lblFim = new JLabel(mensagem);
        lblFim.setForeground(vitoria ? Color.GREEN : Color.RED);
        lblFim.setFont(new Font("Arial", Font.BOLD, 30));
        painelFim.add(lblFim, gbc);

        gbc.gridy++;
        JLabel lblEstatisticas = new JLabel("<html><div style='text-align: center;'><br><b>Estatísticas de " + jogador.getNome() + ":</b><br>"
                + "Sua Pontuação Final: " + jogador.getPontuacao() + " XP<br>"
                + "Rounds Alcançados: " + roundAtual + "/3</div></html>");
        lblEstatisticas.setForeground(Color.WHITE);
        lblEstatisticas.setFont(new Font("Arial", Font.PLAIN, 20));
        painelFim.add(lblEstatisticas, gbc);

        gbc.gridy++;
        List<String> topScores = RankingManager.obterRankingTop5();
        StringBuilder rankingHtml = new StringBuilder("<html><div style='text-align: center;'><br><b>🏆 TOP 5 RANKING GERAL 🏆</b><br>");
        for (String r : topScores) {
            rankingHtml.append(r).append("<br>");
        }
        rankingHtml.append("</div></html>");

        JLabel lblRanking = new JLabel(rankingHtml.toString());
        lblRanking.setForeground(Color.CYAN);
        lblRanking.setFont(new Font("Arial", Font.BOLD, 18));
        painelFim.add(lblRanking, gbc);

        // Botão para jogar novamente ou fechar
        gbc.gridy++;
        JButton btnVoltarMenu = new JButton("VOLTAR AO MENU");
        btnVoltarMenu.setBackground(Color.DARK_GRAY);
        btnVoltarMenu.setForeground(Color.WHITE);
        btnVoltarMenu.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltarMenu.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        painelFim.add(btnVoltarMenu, gbc);

        cardLayout.show(mainPanel, "GameOver");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameGUI().setVisible(true);
        });
    }
}