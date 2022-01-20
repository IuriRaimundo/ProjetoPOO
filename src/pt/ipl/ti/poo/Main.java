package pt.ipl.ti.poo;

import java.io.*;
import java.util.LinkedList;

import pt.ipl.ti.poo.imobiliaria.*;
import pt.ipl.ti.poo.menu.Menu;


public class Main {
    // Opções do menu principal
    private final String[] opcoesMenu = {
            "Listar anúncios abertos", "Listar anúncios concretizados", "Inserir anúncio",
            "Registar anúncio como concretizado", "Estatísticas", "Gravar dados", "Carregar dados",
            "Sair"
    };

    Imobiliaria imobiliariaAtiva;
    private final String NOME_FICH_IMOBILIARIAS = "imobiliarias.dat";

    public Main() {

        Imobiliaria imobiliaria = new Imobiliaria(
                "TioMax",
                new Localizacao("Portugal", "Leiria", "ESTG", "2400"
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
            switch (escolha) {
                case 1 -> {     // Listar anúncios abertos
                    listarAnuncios(imobiliariaAtiva.getAnunciosAtivos());
                }
                case 2 -> {     // Listar anúncios concretizados
                    listarAnuncios(imobiliariaAtiva.getAnunciosConcretizados());
                }
                case 3 -> {
                    // Criar novo anúncio
                    inserirAnuncio();
                }
                case 4 -> {
                    // Registar anúncio como concretizado
                    concretizarAnuncio();
                }
                case 5 -> {
                    // Estatísticas
                    estatisticasImobiliaria();
                }
                case 6 -> {
                    // Gravar dados
                    gravarDadosImobiliaria();
                }
                case 7 -> {
                    // Carregar dados
                    lerFicheiroImobiliaria();
                }
                default -> {
                    // Sair
                    Utils.fecharScanner();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void listarAnuncios(LinkedList<Anuncio> anuncios) {
        Menu menu = new Menu("Anúncios de venda", "Anúncios de aluguer", "Todos");
        menu.apresentar();
        int escolha = menu.obterEscolha();
        switch (escolha) {
            case 1 -> {
                for (Anuncio anuncio : anuncios) {
                    if (anuncio instanceof AnuncioVenda) {
                        AnuncioVenda anuncioVenda = (AnuncioVenda) anuncio;
                        System.out.println(anuncioVenda.toString());
                    }
                }
            }
            case 2 -> {
                for (Anuncio anuncio : anuncios) {
                    if (anuncio instanceof AnuncioAluguer) {
                        AnuncioAluguer anuncioAluguer = (AnuncioAluguer) anuncio;
                        System.out.println(anuncioAluguer.toString());
                    }
                }
            }
            case 3 -> {
                for (Anuncio anuncio : anuncios) {
                        if (anuncio instanceof AnuncioAluguer) {
                            System.out.println(((AnuncioAluguer) anuncio).toString());
                        } else {
                            System.out.println(((AnuncioVenda) anuncio).toString());
                    }
                }
            }
        }
    }

    private <E> void listarAnuncio(E anuncio) {
        anuncio.toString();
    }

    /**
     * @inserirAnuncio
     * @param
     * @return
     */
    private void inserirAnuncio() {
        System.out.println("INSERIR ANÚNCIO");
        Anuncio novoAnuncio = ImobiliariaUtils.criarAnuncio();
        imobiliariaAtiva.adicionarAnuncio(novoAnuncio);
        System.out.println("Anúncio criado com sucesso!\n");
    }

    private void concretizarAnuncio() {
        System.out.println("CONCRETIZAR ANÚNCIOS\nAnúncios Ativos:");
        LinkedList<Anuncio> anunciosAtivos = imobiliariaAtiva.getAnunciosAtivos();

        // Listar anúncios ativos
        int totalAnunciosAtivos = imobiliariaAtiva.getTotalAnunciosAtivos();
        for (int i = 0; i < totalAnunciosAtivos; i++) {
            System.out.println((i + 1) + anunciosAtivos.get(i).toString());
        }

        System.out.print("\nAnúncio a concretizar (0 para cancelar): ");
        int index = Utils.lerInteiro(0, totalAnunciosAtivos, "Escolha um número entre 0 e " + totalAnunciosAtivos + ": ");
        if (index != 0) {   // 0 é a escolha do utilizador para não concretizar algum anúncio
            // Como a listagem dos anúncios começa pelo 1, para obter o anúncio selecionado é necessário subtrair 1
            Anuncio anuncioAConcretizar = anunciosAtivos.get(index - 1);
            imobiliariaAtiva.concretizarAnuncio(anuncioAConcretizar);
        }
    }

    private void estatisticasImobiliaria() {
        System.out.println("\nESTATÍSTICAS DE " + imobiliariaAtiva.getDescricao() + "\n");
        System.out.print("Anúncios de venda ativos: " + imobiliariaAtiva.getTotalAnunciosVendaAtivos());
        System.out.println("\t\tAnúncios de aluguer ativos: " + imobiliariaAtiva.getTotalAnunciosAluguerAtivos());
        System.out.print("Anúncios de venda concretizados: " + imobiliariaAtiva.getTotalAnunciosVendaConcretizados());
        System.out.print("\t\tAnúncios de aluguer concretizados: " + imobiliariaAtiva.getTotalAnunciosAluguerConcretizados());
        System.out.println(imobiliariaAtiva.getPercAnunciosConcretizados() + "% de " + imobiliariaAtiva.getTotalAnuncios() + " anúncios estão concretizados");
        System.out.println(imobiliariaAtiva.getPercAnunciosAtivos() + "% de " + imobiliariaAtiva.getTotalAnuncios() + " anúncios estão ativos");
        System.out.println("Receita em vendas: " + imobiliariaAtiva.getReceitaVendas() + "€");
        System.out.println("Receita em alugueres: " + imobiliariaAtiva.getReceitaAlugueres() + "€");
        System.out.println("Total de receitas: " + imobiliariaAtiva.getTotalReceitas() + "€");
    }

    private void gravarDadosImobiliaria() {
        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(NOME_FICH_IMOBILIARIAS))) {
            oout.writeObject(imobiliariaAtiva); /* objeto a gravar no ficheiro */
            System.out.println("Dados de " + imobiliariaAtiva.getDescricao() + " guardados com sucesso!");
        } catch (IOException e) {
            System.out.println("Falha ao gravar " + imobiliariaAtiva.getDescricao() + ".");
        }
    }

    private void lerFicheiroImobiliaria() {
        Imobiliaria imobiliaria = null;

        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(NOME_FICH_IMOBILIARIAS))) {
            imobiliaria = (Imobiliaria) oin.readObject(); /* objeto a ler do ficheiro */
            System.out.println("\nImobiliárias carregadas com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Falha ao carregar dados.");
        } finally {
            imobiliariaAtiva = imobiliaria;
        }
    }
}