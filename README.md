# CodeArena: Batalha do Conhecimento (RPG Educacional LPOO)

**Autores:** Talles Pinheiro Lins e Diogo Bezerra  
**Instituição:** Escola Politécnica da Universidade de Pernambuco (POLI - UPE)  
**Disciplina:** Linguagem de Programação Orientada a Objetos (LPOO)

---

## 📖 Sobre o Projeto
O CodeArena é um RPG de turnos com interface gráfica (GUI), desenvolvido com foco educacional. O jogador assume o controle de um personagem (Cientista ou Lutador) e enfrenta oponentes em uma arena onde a principal arma é o conhecimento em Programação Orientada a Objetos (Java). 

Para atacar com eficácia ou se defender, o jogador deve responder corretamente a questões (múltipla escolha, verdadeiro/falso ou preenchimento de lacunas) sobre os conceitos da disciplina. O jogo é dividido em 3 rounds de dificuldade progressiva, culminando em uma batalha contra o Boss ("Senhor do Código Legado"). Ao final, a pontuação do jogador pode ser registrada em um Ranking Global ("Top 5").

O banco de questões é carregado dinamicamente a partir do arquivo `banco_questoes_lpoo_expandido.txt`, garantindo que as perguntas escalem do nível Fácil ao Difícil conforme o avanço dos rounds[cite: 1].

---

## 🏗️ Justificativas de Design (Orientação a Objetos)
A arquitetura do jogo foi desenvolvida aplicando fortemente os pilares e boas práticas da programação orientada a objetos:

* **Encapsulamento:**
  Atributos vitais, como `vida`, `ataque` e `defesa` na classe base `Character`, são mantidos privados e manipulados de forma segura através de métodos como `receberDano()`. O mesmo princípio de proteção de estado interno é aplicado nas classes de interface gráfica e controle, gerenciando as pontuações e instâncias de forma isolada.

* **Herança e Classes Abstratas:**
  * As entidades do jogo derivam da superclasse abstrata `Character`, o que permite o reaproveitamento da lógica de cálculo de dano e status para o `Player` e para as diferentes variações de inimigos (`Enemy`, `Enemy2`, `Enemy3`).
  * As questões são modeladas pela classe abstrata `Question`, definindo um contrato estrutural básico (enunciado, dificuldade, resposta). As subclasses `MultipleChoice`, `TrueFalse` e `FillTheBlank` herdam essas características e implementam comportamentos específicos de exibição e validação.
  * A própria interface gráfica herda de `JFrame`, utilizando os componentes visuais do Java Swing.

* **Polimorfismo:**
  * A Sobrescrita de Métodos (*Overriding*) é amplamente utilizada. Cada tipo de inimigo sobrescreve a lógica de ataque e possui seu próprio método `falar(boolean sofreuDano)` para reagir de forma diferente aos acertos e erros do jogador. 
  * O sistema de combate aceita instâncias genéricas de `Question` e `Character`, permitindo escalar e introduzir novos tipos de perguntas e personagens no futuro sem modificar a lógica principal do gerenciador de batalhas.

* **Interfaces (Contratos de Habilidades):**
  Para evitar problemas de herança múltipla e seguir princípios de design mais limpos, habilidades especiais foram segregadas em interfaces:
  * `Intelecto`: Implementada pelo `Cientista`, permite interagir com a mecânica da questão, eliminando uma alternativa falsa de um objeto `MultipleChoice`.
  * `Furia`: Implementada pelo `Lutador`, propicia a execução de mecânicas de ataque massivo condicionado ao acerto da próxima pergunta.

* **Leitura/Escrita de Arquivos e Coleções:**
  * O pacote `java.io` (`File`, `Scanner`, `FileWriter`, `BufferedWriter`) é utilizado para o carregamento do banco de questões e para a persistência de dados do sistema de pontuação no arquivo `ranking.txt`.
  * O gerenciamento de dados na memória é feito através do uso extensivo da API de Coleções do Java, incluindo `Map<String, ArrayList<Question>>` para indexar as perguntas por dificuldade na inicialização, e uso de `List` com ordenação `sort()` para estruturar o Ranking dos jogadores.

* **Interface Gráfica (Swing):**
  O projeto utiliza a biblioteca `javax.swing` para criar uma experiência visual. O uso de `CardLayout` permite a alternância suave entre diferentes painéis (Menu, Tutorial, Batalha e GameOver) dentro da mesma janela, mantendo o controle de fluxo da aplicação intuitivo e centralizado.

---

## 📁 Estrutura de Pacotes (Modularidade)

| Pacote | Responsabilidade Principal | Classes Inclusas |
| :--- | :--- | :--- |
| **`Controle`** | Gerencia o fluxo da aplicação, renderização de interface e persistência. | `GameGUI`, `RankingManager`, `Game`, `Round`, `BattleManager`, `Main` |
| **`Personagem`** | Define as entidades do jogo, heranças e os contratos de habilidades. | `Character`, `Player`, `Enemy`, `Enemy2`, `Enemy3`, `Cientista`, `Lutador`, `Furia`, `Intelecto` |
| **`Questões`** | Concentra a lógica de modelagem das perguntas e carregamento de dados. | `Question`, `QuestionBank`, `MultipleChoice`, `TrueFalse`, `FillTheBlank` |

---

## 🚀 Como Executar

1. Certifique-se de ter o **JDK (Java Development Kit)** instalado em sua máquina (recomenda-se versão 8 ou superior).
2. Clone o repositório e navegue até a pasta raiz do projeto.
3. Compile os arquivos fonte localizados na pasta `src/`. Em um terminal, dentro da pasta `src`, você pode usar:
   ```bash
   javac Controle/*.java Personagem/*.java Questões/*.java
