package pt.ipl.ti.poo.menu;

import pt.ipl.ti.poo.Utils;

public class Menu {
    private final String[] opcoes;
    private final int numeroDeOpcoes;

    public Menu(String ... opcoes) {
        this.opcoes = opcoes;
        this.numeroDeOpcoes = opcoes.length;
    }

    // Apresenta o menu
    public void apresentar() {
        for (int i = 0; i < numeroDeOpcoes; i++) {
            System.out.println("\t" + (i + 1) + ". " + opcoes[i]);
        }
    }

    // Obtem e devolve a escolha do utilizador
    public int obterEscolha() {
        System.out.print("Escolha: ");
        return Utils.lerInteiro(1, numeroDeOpcoes, "Opção inválida, escolha uma opção entre " + 1 + numeroDeOpcoes);
    }
}
