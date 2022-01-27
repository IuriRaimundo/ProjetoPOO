package pt.ipl.ti.poo;

import pt.ipl.ti.poo.imobiliaria.*;
import pt.ipl.ti.poo.menu.Menu;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;


public class Main {

    /**
     * Imobiliaria a ser utilizada no momento.
     */
    Imobiliaria imobiliariaAtiva = null;

    /**
     * Lista das imobiliárias no programa.
     */
    LinkedList<Imobiliaria> imobiliarias = new LinkedList<>();

    public Main() {

        /*
        Imobiliaria i = new Imobiliaria(
                "TioMax",
                new Localizacao("Portugal", "Leiria", "ESTG", "2400"
                ),
                "teste");

        imobiliariaAtiva = i;
        imobiliarias.add(i);

        // Temp
        Terreno t1 = new Terreno("T1", new Localizacao("Portugal", "leiria", "rua do t1", "2400-100"), 500);
        AnuncioVenda av1 = new AnuncioVenda(25700, t1);
        imobiliariaAtiva.adicionarAnuncio(av1);

        Terreno t2 = new Terreno("T2", new Localizacao("Portugal", "leiria", "rua do t2", "2400-144"), 200);
        AnuncioVenda av2 = new AnuncioVenda(17800, t2);
        imobiliariaAtiva.adicionarAnuncio(av2);

        Terreno t3 = new Terreno("T3", new Localizacao("Portugal", "Coimbra", "rua do t3", "2400-145"), 320);
        AnuncioVenda av3 = new AnuncioVenda(24000, t3);
        imobiliariaAtiva.adicionarAnuncio(av3);

        Terreno t4 = new Terreno("T4", new Localizacao("Portugal", "Algarve", "rua do t4", "2400-148"), 520);
        AnuncioVenda av4 = new AnuncioVenda(32000, t4);
        imobiliariaAtiva.adicionarAnuncio(av4);

        Quarto q1 = new Quarto("q1", new Localizacao("Portugal", "leiria", "rua do q1", "2400-054"), 5);
        AnuncioAluguer aa1 = new AnuncioAluguer(300, q1, 14);
        imobiliariaAtiva.adicionarAnuncio(aa1);

        Quarto q2 = new Quarto("q2", new Localizacao("Portugal", "leiria", "rua do q2", "2400-544"), 12);
        AnuncioAluguer aa2 = new AnuncioAluguer(600, q2, 6);
        imobiliariaAtiva.adicionarAnuncio(aa2);

        Quarto q3 = new Quarto("q3", new Localizacao("Portugal", "leiria", "rua do q", "2400-054"), 5);
        AnuncioAluguer aa3 = new AnuncioAluguer(350, q3, 14);
        imobiliariaAtiva.adicionarAnuncio(aa3);

        Quarto q4 = new Quarto("q4", new Localizacao("Portugal", "Aveiro", "rua do q4", "2400-054"), 5);
        AnuncioAluguer aa4 = new AnuncioAluguer(400, q4, 14);
        imobiliariaAtiva.adicionarAnuncio(aa4);

        Apartamento a1 = new Apartamento("a1", new Localizacao("Portugal", "leiria", "rua do a1", "2400-076"), 46, 5);
        AnuncioAluguer aa5 = new AnuncioAluguer(700, a1, 8);
        imobiliariaAtiva.adicionarAnuncio(aa5);

        Apartamento a2 = new Apartamento("a2", new Localizacao("Portugal", "leiria", "rua do a2", "2400-244"), 24, 1);
        AnuncioAluguer aa6 = new AnuncioAluguer(500, a2, 10);
        imobiliariaAtiva.adicionarAnuncio(aa6);

        Apartamento a3 = new Apartamento("a2", new Localizacao("Portugal", "Viana", "rua do a3", "2400-223"), 30, 2);
        AnuncioAluguer aa7 = new AnuncioAluguer(500, a3, 10);
        imobiliariaAtiva.adicionarAnuncio(aa7);

        Moradia m1 = new Moradia("m1", new Localizacao("Portugal", "leiria", "rua do m1", "2400"), 150, 5, 2);
        AnuncioAluguer aa8 = new AnuncioAluguer(900, m1, 32);
        imobiliariaAtiva.adicionarAnuncio(aa8);

        Moradia m2 = new Moradia("m2", new Localizacao("Portugal", "leiria", "rua do m2", "2400"), 150, 8, 3);
        AnuncioVenda av5 = new AnuncioVenda(76000, m2);
        imobiliariaAtiva.adicionarAnuncio(av5);

        Moradia m3 = new Moradia("m3", new Localizacao("Portugal", "Braga", "rua do m3", "2600"), 200, 3, 1);
        AnuncioVenda av6 = new AnuncioVenda(80000, m3);
        imobiliariaAtiva.adicionarAnuncio(av6);

        Moradia m4 = new Moradia("m4", new Localizacao("Portugal", "Amadora", "rua do m4", "2700"), 150, 4, 2);
        AnuncioVenda av7 = new AnuncioVenda(90000, m4);
        imobiliariaAtiva.adicionarAnuncio(av7);

        */

        /*
            O programa irá executar num ciclo, até que o utilizador escolha a opção de sair.
            Quando o utilizador iniciar sessão em uma imobiliária, ou adicionar uma nova, é
            apresentado o menu para efetuar operações à imobiliária selecionada.
         */

        int escolha, opcaoSairPrograma;

        do {
            System.out.println("-------------------");
            System.out.println("GESTOR IMOBILIÁRIAS");
            System.out.println("-------------------");

            // Lista de opções, para criar o menu.
            LinkedList<String> opcoes = new LinkedList<>();

            // Adicionar o nome das imobiliárias às opções
            for (Imobiliaria imobiliaria : imobiliarias) {
                opcoes.add(imobiliaria.getDescricao());
            }

            //Adicionar opções adicionais, o número de cada opcão corresponde exatamente ao tamanho da lista após a sua inserção.
            opcoes.add("Adicionar Imobiliária");
            int opcaoCriarImob = opcoes.size();
            opcoes.add("Carregar dados");
            int opcaoCarregarDados = opcoes.size();
            opcoes.add("Sair do programa");
            opcaoSairPrograma = opcoes.size();

            // Criar um menu com as opções e recolher a escolha do utilizador.
            Menu menu = new Menu(opcoes);
            menu.apresentar();
            escolha = menu.obterEscolha();


            // Executar operação escolhida pelo utilizador
            if (escolha == opcaoCriarImob) {
                // Criar uma nova imobiliária e adicionar à lista de imobiliárias caso não seja repetida.
                Imobiliaria novaImobiliaria = criarImobiliaria();
                if (adicionarImobiliaria(novaImobiliaria)) {
                    System.out.println("\nImobiliária adicionada com sucesso!\n");
                    imobiliariaAtiva = novaImobiliaria;
                    menuImobiliaria();
                } else {
                    System.out.println("\nImobiliária não adicionada. Não podem ser adicionadas imobiliárias com nomes repetidos.\n");
                }

            } else if (escolha == opcaoCarregarDados) {
                // Carregar os dados armazenados em ficheiro no programa.
                imobiliarias = ImobiliariaUtils.lerFicheiroImobiliaria();

            } else if (escolha == opcaoSairPrograma) {
                // Fechar o programa
                fecharPrograma();

            } else {
                /*
                Tentar entrar em uma das imobiliárias, é solicitada uma palavra-passe e caso a hash da mesma corresponda
                à hash armazenada na imobiliária é atualizada a imobiliária ativa, se não corresponder o utilizador é
                notificado e o programa regressa ao menu.
                 */
                Imobiliaria imobiliariaEscolhida = imobiliarias.get(escolha - 1);
                boolean iniciadaSessao = ImobiliariaUtils.iniciarSessao(imobiliariaEscolhida);
                if (iniciadaSessao) {
                    imobiliariaAtiva = imobiliariaEscolhida;
                    menuImobiliaria();
                }
            }
        } while (escolha != opcaoSairPrograma);
    }

    public static void main(String[] args) {
        new Main();
    }

    /**
     * Esta função adiciona uma imobiliária à lista de imobiliárias.
     * @param imobiliaria Imobiliária a ser adicionada.
     */
    private boolean adicionarImobiliaria(Imobiliaria imobiliaria) {
        if (imobiliaria == null || imobiliarias.contains(imobiliaria)) {
            return false;
        } else {
            imobiliarias.add(imobiliaria);
            return true;
        }
    }

    /**
     * Esta função apresenta o menu para efetuar operações sobre a imobiliária ativa. <br>
     * Termina quando o utilizador seleciona a opção de terminar sessão.
     */
    private void menuImobiliaria() {
        if (imobiliariaAtiva == null) return;

        final String[] opcoesMenu = {
                "Listar anúncios abertos", "Listar anúncios concretizados", "Inserir anúncio",
                "Registar anúncio como concretizado", "Estatísticas", "Gravar dados", "Terminar sessão"
        };

        Menu menu = new Menu(opcoesMenu);

        int escolha;

        do {
            System.out.println("-----------------------------");
            System.out.println("GESTOR IMOBILIÁRIA - " + imobiliariaAtiva.getDescricao());
            System.out.println("-----------------------------");

            menu.apresentar();
            escolha = menu.obterEscolha();

            switch (escolha) {
                case 1 -> // Listar anúncios abertos
                        ImobiliariaUtils.listarAnuncios(imobiliariaAtiva.getAnunciosAtivos());
                case 2 -> // Listar anúncios concretizados
                        ImobiliariaUtils.listarAnuncios(imobiliariaAtiva.getAnunciosConcretizados());
                case 3 -> // Criar novo anúncio
                        ImobiliariaUtils.criarAnuncio(imobiliariaAtiva);
                case 4 -> // Registar anúncio como concretizado
                        ImobiliariaUtils.concretizarAnuncio(imobiliariaAtiva);
                case 5 -> // Estatísticas
                        ImobiliariaUtils.imprimirRelatorioImobiliaria(imobiliariaAtiva);
                case 6 -> // Gravar dados
                        ImobiliariaUtils.gravarDadosImobiliarias(imobiliarias);
                case 7 -> // Terminar sessão
                        imobiliariaAtiva = null;
            }
        } while (escolha != 7);
    }

    /**
     * Função para criar imobiliárias, solicita ao utilizador um nome e uma localização <br>
     * e devolve uma imobiliária com esses dados.
     */
    private Imobiliaria criarImobiliaria() {
        System.out.println("\nNOVA IMOBILIÁRIA\n");
        System.out.print("Nome: ");
        String nome = Utils.lerString();
        Localizacao localizacao = ImobiliariaUtils.criarLocalizacao();
        String palavraPasse = ImobiliariaUtils.criarPalavraPasse();
        return new Imobiliaria(nome, localizacao, palavraPasse);
    }

    /**
     * Esta função prepara o programa para ser fechado.
     */
    private void fecharPrograma() {

        System.out.println("\nA sair do programa...");

        // Verificar se existem alterações entre o estado atual das imobiliárias e os dados das imobiliárias gravados.
        // Se existir diferença, perguntar ao utilizador se ele pretende guardar os dados de forma a não perder as últimas alterações.
        try {
            // Criar ficheiro temporário
            File tmp = new File("tmp.dat");
            tmp.createNewFile();

            // Obter ficheiro utilizado para guardar os dados do programa.
            File fichImob = new File(ImobiliariaUtils.NOME_FICH_IMOBILIARIAS);

            // Gravar os dados das imobiliárias para o ficherio temporário
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(tmp));
            oout.writeObject(imobiliarias);

            // Obter hash dos dois ficheiros

            MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");

            byte[] tmpHash, fichImobHash;
            tmpHash = Utils.getFileChecksum(shaDigest, tmp);
            fichImobHash = Utils.getFileChecksum(shaDigest, fichImob);

            // Apagar ficheiro temporário
            tmp.delete();

            // Comparar as hash, se forem diferentes é porque houve alterações nos dados.
            if (!Utils.compararHash(tmpHash, fichImobHash)) {
                // Perguntar ao utilizador se pretende guardar os dados
                System.out.print("Existem dados que não foram gravados. Pretende guardar os dados ? (S/N): ");
                String escolha;
                do {
                    escolha = Utils.lerString().toUpperCase();
                    if (!escolha.equals("S") && !escolha.equals("N")) {
                        System.out.print("Insira apenas um 'S' ou 'N' sem plicas: ");
                    }
                } while (!escolha.equals("S") && !escolha.equals("N"));

                // Se a escolha for sim, gravar os dados.
                if (escolha.equals("S")) {
                    ImobiliariaUtils.gravarDadosImobiliarias(imobiliarias);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | NoSuchAlgorithmException e) {
            System.out.println("Falha a verificar alterações nos dados.");
        }

        Utils.fecharScanner();
    }
}