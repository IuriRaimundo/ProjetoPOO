package pt.ipl.ti.poo.menu;

import pt.ipl.ti.poo.Utils;

import java.util.LinkedList;

/**
 * Esta classe é utilizada para criar menus para apresentar ao utilizador
 */
public class Menu {
    private final String[] opcoes;
    private final int numeroDeOpcoes;

    /**
     * Este construtor recebe um vetor de Strings, e cada String corresponde a uma opção do menu.
     * A escolha do utilizador será a localização da opção no vetor + 1
     * @param opcoes Opções para o menu
     */
    public Menu(String ... opcoes) {
        this.opcoes = opcoes;
        this.numeroDeOpcoes = opcoes.length;
    }

    /**
     * Este construtor recebe uma LinkedList de strings, e a mesma é convertida para um vetor de strings.
     * @param opcoes Lista de opções para o menu
     */
    public Menu(LinkedList<String> opcoes) {
        this.opcoes = opcoes.toArray(new String[opcoes.size()]);
        this.numeroDeOpcoes = this.opcoes.length;
    }

    /**
     * Este método apresenta o menu na consola
     */
    public void apresentar() {
        for (int i = 0; i < numeroDeOpcoes; i++) {
            System.out.println("\t" + (i + 1) + ". " + opcoes[i]);
        }
    }

    /**
     * Este método recolhe a partir da consola a escolha do utilizador. <br>
     * Apenas devolve um valor quando o utilizador efetuar uma escolha válida.
     * É necessário executar o método "apresentar()" antes de executar este método.
     * @return Escolha do utilizador
     */
    public int obterEscolha() {
        System.out.print("Escolha: ");
        return Utils.lerInteiro(1, numeroDeOpcoes, "Opção inválida, escolha uma opção entre " + 1 + " e " + numeroDeOpcoes + ": ");
    }
}
