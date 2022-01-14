package pt.ipl.ti.poo;

import java.util.LinkedList;
import java.util.Arrays;
import pt.ipl.ti.poo.menu.Menu;

public class Main {
    // Opções do menu principal
    private final LinkedList<String> opcoesMenu = new LinkedList<String>(Arrays.asList(
            "Listar anúncios abertos", "Listar anúncios concretizados", "Inserir anúncio",
            "Registar anúncio como concretizado", "Estatísticas", "Gravar dados", "Carregar dados",
            "Sair"
    ));

    Imobiliaria imobiliariaAtiva;

    public Main() {

        Imobiliaria imobiliaria = new Imobiliaria(
                "TioMax",
                new Localizacao( "Portugal", "Leiria", "ESTG", "2400"
        ));

        imobiliariaAtiva = imobiliaria;

        Menu menu = new Menu(opcoesMenu);
        int escolha = 0;
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("GESTOR IMOBILIÁRIA - " + imobiliariaAtiva.getDescricao());
            System.out.println("-----------------------------");

            menu.apresentar();
            escolha = menu.obterEscolha();
            switch(escolha) {
                case 1 -> {
                    // Listar anúncios abertos
                }
                case 2 -> {
                    // Listar anúncios concretizados
                }
                case 3 -> {
                    // Inserir anúncio
                }
                case 4 -> {
                    // Registar anúncio como concretizado
                }
                case 5 -> {
                    // Estatísticas
                }
                case 6 -> {
                    // Gravar dados
                }
                case 7 -> {
                    // Carregar dados
                }
                default -> {
                    // Sair
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void listarAnunciosAbertos(Imobiliaria imobiliaria) {

    }
}
