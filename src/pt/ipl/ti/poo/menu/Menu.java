package pt.ipl.ti.poo.menu;

import java.util.LinkedList;
import pt.ipl.ti.poo.Utils;

public class Menu {
    private LinkedList<String> opcoes;
    private int numeroDeOpcoes;

    public Menu(LinkedList<String> opcoes) {
        this.opcoes = opcoes != null ? opcoes : new LinkedList<>();
        this.numeroDeOpcoes = opcoes.size();
    }

    // Apresenta o menu
    public void apresentar() {
        for (int i = 0; i < numeroDeOpcoes; i++) {
            System.out.println("\t" + String.valueOf(i + 1) + ". " + opcoes.get(i));
        }
    }

    // Obtem e devolve a escolha do utilizador
    public int obterEscolha() {
        System.out.print("Escolha: ");
        return Utils.lerInteiro(1, numeroDeOpcoes, "Opção inválida, escolha uma opção entre " + String.valueOf(1) + String.valueOf(numeroDeOpcoes));
    }
}
