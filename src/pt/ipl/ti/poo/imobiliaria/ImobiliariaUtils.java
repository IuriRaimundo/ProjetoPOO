package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Utils;
import pt.ipl.ti.poo.menu.Menu;

import java.io.*;
import java.util.LinkedList;

/**
 * Esta classe contém funções de utilidade para efetuar operações relacionadas com a Imobiliária. <br>
 * Contém métodos para criar os objetos pertencentes ao package imobiliaria e métodos para alterar uma mobiliária com as devidas proteções.
 */
public abstract class ImobiliariaUtils {

    /**
     * Nome do ficheiro das imobiliárias
     */
    public static final String NOME_FICH_IMOBILIARIAS = "imobiliarias.dat";
    /**
     * Mínimo de área para imóveis, em kilometros quadrados.
     */
    private static final double MIN_AREA_KM2 = 0;
    /**
     * Máximo de área para imóveis, em kilometros quadrados.
     */
    private static final double MAX_AREA_KM2 = 10000;
    /**
     * Máximo de quartos para habitações
     */
    private static final int MAX_QUARTOS = 30;
    /**
     * Máximo de pisos para moradias
     */
    private static final int MAX_PISOS = 5;

    private static final int MIN_TAM_PW = 5;
    private static final int MAX_TAM_PW = 16;


    /**
     * Função para criar anúncio, solicita-se ao utilizador
     * o tipo de anúncio a ser criado e os dados correspondentes ao anúncio. <br>
     * O anúncio criado é adicionado à lista de anúncios ativos da imobiliária recebida.
     * @param imobiliaria Imobiliária a ser adicionado o anúncio
     */
    public static void criarAnuncio(Imobiliaria imobiliaria) {
        Anuncio novoAnuncio;

        System.out.println("INSERIR ANÚNCIO");
        // Obter escolha do tipo de anúncio
        Menu menu = new Menu("Anúncio de aluguer", "Anúncio de venda");
        menu.apresentar();
        int escolhaAnuncio = menu.obterEscolha();

        System.out.print("Preço (€): ");
        double preco = Utils.lerDouble(0, Double.MAX_VALUE);

        // Criar imóvel
        System.out.println("Tipo de imóvel");

        // Opções do imóvel, a 3ª opção é Quarto quando se trata de um anúncio de aluguer, e Terreno quando é um anúncio de venda
        String[] opcoesImovel = escolhaAnuncio == 1 ? OpcoesImovel.getOpcoesAluguerString() : OpcoesImovel.getOpcoesVendaString();
        menu = new Menu(opcoesImovel);
        menu.apresentar();
        int escolhaImovel = menu.obterEscolha();

        // Ler dados imóvel
        Imovel dadosImovel = criarImovel();

        // Criar o anúncio com base na escolha
        // Se a escolha for habitação
        if (escolhaImovel == OpcoesImovel.MORADIA.getNumeroOpcao() || escolhaImovel == OpcoesImovel.APARTAMENTO.getNumeroOpcao()) {
            Habitacao novaHabitacao = criarHabitacao(dadosImovel);
            // Criar moradia ou apartamento
            novaHabitacao = escolhaImovel == OpcoesImovel.MORADIA.getNumeroOpcao() ? criarMoradia(novaHabitacao) : criarApartamento(novaHabitacao);
            // Criar anúncio de aluguer ou venda
            novoAnuncio = escolhaAnuncio == 1 ? criarAnuncioAluguer(preco, novaHabitacao) : criarAnuncioVenda(preco, novaHabitacao);
        }  // Se a escolha for um quarto ou terreno
        else if (escolhaImovel == OpcoesImovel.QUARTO.getNumeroOpcao() || escolhaImovel == OpcoesImovel.TERRENO.getNumeroOpcao()) {
            // Criar anúncio de aluguer ou de venda, o de aluguer será um quarto e de venda será terreno.
            novoAnuncio = escolhaAnuncio == 1 ? criarAnuncioAluguer(preco, criarQuarto(dadosImovel)) : criarAnuncioVenda(preco, criarTerreno(dadosImovel));
        } else { // Escolha inválida
            throw new IllegalArgumentException("Escolha de imóvel incorreta.");
        }

        imobiliaria.adicionarAnuncio(novoAnuncio);
        System.out.println("Anúncio adicionado!");
    }

    /**
     * Esta função cria um objeto da classe AnuncioAluguer. <br>
     * Ela solicita ao utilizador a duração do aluguer.
     *
     * @param preco  Preço do aluguer
     * @param imovel Imóvel a ser anunciado
     * @return Anúncio de aluguer criado.
     */
    private static AnuncioAluguer criarAnuncioAluguer(double preco, ImoveisQuePodemSerAlugados imovel) {
        System.out.print("Duracao do aluguer (meses): ");
        int duracao = Utils.lerInteiro(0, 999);
        return new AnuncioAluguer(preco, imovel, duracao);
    }

    private static AnuncioVenda criarAnuncioVenda(double preco, ImoveisQuePodemSerVendidos imovel) {
        return new AnuncioVenda(preco, imovel);
    }

    /**
     * Esta função solicita ao utilizador os dados do imóvel (nome, área, localização) <br>
     * e cria um objeto da classe imóvel.
     *
     * @return Imóvel com os dados inseridos.
     */
    private static Imovel criarImovel() {
        System.out.print("Nome do imóvel: ");
        String nome = Utils.lerString();

        System.out.print("Área (M²): ");
        double area = Utils.lerDouble(MIN_AREA_KM2, MAX_AREA_KM2, "Insira uma área entre " + MIN_AREA_KM2 + " e " + MAX_AREA_KM2 + ": ");

        Localizacao localizacao = criarLocalizacao();

        return new Imovel(nome, localizacao, area);
    }

    private static Habitacao criarHabitacao(Imovel imovel) {
        System.out.print("Quartos: ");
        int quartos = Utils.lerInteiro(1, MAX_QUARTOS);
        return new Habitacao(imovel, quartos);
    }

    private static Moradia criarMoradia(Habitacao imovel) {
        System.out.print("Pisos: ");
        int pisos = Utils.lerInteiro(1, MAX_PISOS);
        return new Moradia(habitacao, pisos);
    }

    private static Apartamento criarApartamento(Habitacao imovel) {
        return new Apartamento(imovel);
    }

    private static Terreno criarTerreno(Imovel imovel) {
        return new Terreno(imovel);
    }

    private static Quarto criarQuarto(Imovel imovel) {
        return new Quarto(imovel);
    }

    public static Localizacao criarLocalizacao() {
        System.out.print("Morada: ");
        String morada = Utils.lerString();
        System.out.print("Cidade: ");
        String cidade = Utils.lerString();

        String codigoPostal;
        boolean cpValido;
        do {
            System.out.print("Código Postal: ");
            codigoPostal = Utils.lerString();
            cpValido = verificarCodigoPostal(codigoPostal);
            if (!cpValido) {
                System.out.println("Código postal inválido, repita a inserção.");
            }
        } while (!cpValido);

        System.out.print("País: ");
        String pais = Utils.lerString();
        return new Localizacao(pais, cidade, morada, codigoPostal);
    }

    /**
     * Esta função apresenta os dados dos anúncios de uma lista no ecrã. <br>
     * Solicita-se ao utilizador o tipo de anúncio que pretende ver, e apresenta os anúncios do tipo escolhido.
     *
     * @param anuncios Lista de anúncios a apresentar
     */
    public static void listarAnuncios(LinkedList<Anuncio> anuncios) {
        Menu menu = new Menu("Anúncios de venda", "Anúncios de aluguer", "Todos");
        menu.apresentar();
        int escolha = menu.obterEscolha();
        switch (escolha) {
            case 1 -> {
                for (Anuncio anuncio : anuncios) {
                    if (anuncio instanceof AnuncioVenda) {
                        System.out.println("\n" + anuncio.toString());
                    }
                }
            }
            case 2 -> {
                for (Anuncio anuncio : anuncios) {
                    if (anuncio instanceof AnuncioAluguer) {
                        System.out.println("\n" + anuncio.toString());
                    }
                }
            }
            case 3 -> {
                for (Anuncio anuncio : anuncios) {
                    System.out.println("\n" + anuncio.toString());
                }
            }
        }
    }

    /**
     * Esta função apresenta uma lista dos anúncios ativos <br>
     * Solicita-se ao utilizador para escolher um dos anúncios e o anúncio escolhido é concretizado.
     * Caso o utilizador escolha 0 nenhum anúncio vai ser concretizado.
     *
     * @param imobiliaria Imobiliaria a ser efetuada a concretização.
     */
    public static void concretizarAnuncio(Imobiliaria imobiliaria) {
        System.out.println("CONCRETIZAR ANÚNCIOS\n\nAnúncios Ativos:\n");
        LinkedList<Anuncio> anunciosAtivos = imobiliaria.getAnunciosAtivos();

        // Listar anúncios ativos
        int totalAnunciosAtivos = imobiliaria.getTotalAnunciosAtivos();
        for (int i = 0; i < totalAnunciosAtivos; i++) {
            System.out.println((i + 1) + " - " + anunciosAtivos.get(i).toString() + "\n");
        }

        System.out.print("Anúncio a concretizar (0 para cancelar): ");
        int index = Utils.lerInteiro(0, totalAnunciosAtivos, "Escolha um número entre 0 e " + totalAnunciosAtivos + ": ");
        if (index != 0) {   // 0 é a escolha do utilizador para não concretizar algum anúncio
            // Como a listagem dos anúncios começa pelo 1, para obter o anúncio selecionado é necessário subtrair 1
            Anuncio anuncioAConcretizar = anunciosAtivos.get(index - 1);
            imobiliaria.concretizarAnuncio(anuncioAConcretizar);
        }
    }

    /**
     * Esta função imprime o relatório da Imobiliária, na consola.
     *
     * @param imobiliaria Imobiliaria para a realização do relatório
     */
    public static void imprimirRelatorioImobiliaria(Imobiliaria imobiliaria) {
        System.out.println("\nESTATÍSTICAS DE " + imobiliaria.getDescricao() + "\n");
        System.out.println("ANÚNCIOS\n");
        System.out.print("Anúncios de venda ativos: " + imobiliaria.getTotalAnunciosVendaAtivos());
        System.out.println("\t\tAnúncios de venda concretizados: " + imobiliaria.getTotalAnunciosVendaConcretizados());
        System.out.print("Anúncios de aluguer ativos: " + imobiliaria.getTotalAnunciosAluguerAtivos());
        System.out.println("\tAnúncios de aluguer concretizados: " + imobiliaria.getTotalAnunciosAluguerConcretizados());
        System.out.println(imobiliaria.getPercAnunciosConcretizados() + "% de " + imobiliaria.getTotalAnuncios() + " anúncios foram concretizados");
        System.out.println(imobiliaria.getPercAnunciosAtivos() + "% de " + imobiliaria.getTotalAnuncios() + " anúncios estão ativos");
        System.out.println("\nRECEITAS\n");
        System.out.println("Receita em vendas: " + imobiliaria.getReceitaVendas() + "€");
        System.out.println("Receita em alugueres: " + imobiliaria.getReceitaAlugueres() + "€");
        System.out.println("Total de receitas: " + imobiliaria.getTotalReceitas() + "€");
        System.out.println();
    }

    /**
     * Esta função grava dados das Imobiliárias em ficheiro. <br>
     * O resultado a operação é apresentado ao utilizador
     *
     * @param imobiliarias Lista das imobiliárias
     */
    public static void gravarDadosImobiliarias(LinkedList<Imobiliaria> imobiliarias) {
        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(NOME_FICH_IMOBILIARIAS))) {
            oout.writeObject(imobiliarias); /* objeto a gravar no ficheiro */
            System.out.println("Os dados foram guardados com sucesso!");
        } catch (IOException e) {
            System.out.println("Falha ao gravar os dados das imobiliárias.");
        }
    }

    /**
     * Esta função vai ler os dados do ficheiro da Imobiliária e popular uma lista com os objetos lidos. <br>
     * O resultado a operação é apresentado ao utilizador.
     *
     * @return Lista com os dados lidos, pode ser vazia caso não existam dados gravados ou occorra um erro de leitura.
     */
    public static LinkedList<Imobiliaria> lerFicheiroImobiliaria() {
        LinkedList<Imobiliaria> tmp = null;
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(NOME_FICH_IMOBILIARIAS))) {
            tmp = (LinkedList<Imobiliaria>) oin.readObject(); /* objeto a ler do ficheiro */
            System.out.println("\nImobiliárias carregadas com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Falha ao carregar dados.");
        }
        // Se não forem lidos dados inicializar a Linked List.
        return tmp != null ? tmp : new LinkedList<>();
    }

    /**
     * Função para criar uma palavra-passe, é solicitada ao utilizdor a inserção de uma palavra passe. <br>
     * Verifica-se se o comprimento da mesma encontra-se entre um intervalo definido pelas constantes <br>
     * <b>MIN_TAM_PW</b> e <b>MAX_TAM_PW</b>.
     *
     * @return Palavra-passe introduzida
     */
    public static String criarPalavraPasse() {
        String palavraPasse = "", confirmacao = "";
        int tamanho;
        do {
            System.out.print("Palavra-passe: ");
            palavraPasse = Utils.lerString();
            tamanho = palavraPasse.length();

            if (tamanho < MIN_TAM_PW || tamanho > MAX_TAM_PW) {
                System.out.println("A palavra passe tem de ter entre " + MIN_TAM_PW + " e " + MAX_TAM_PW + " carateres.");
                continue;
            }

            System.out.print("Confirme a palavra-passe: ");
            confirmacao = Utils.lerString();

            if (!palavraPasse.equals(confirmacao)) {
                System.out.println("As palavras passe não correspondem.");
            }
        } while (tamanho < MIN_TAM_PW || tamanho > MAX_TAM_PW || !palavraPasse.equals(confirmacao));

        return palavraPasse;
    }

    /**
     * Função para validar código postal, através de Regex. <br>
     * Regex retirado <a href="https://www.portugal-a-programar.pt/forums/topic/70562-regex-para-c%C3%B3digo-postal-portugu%C3%AAs/?do=findComment&comment=581500">daqui</a>
     *
     * @param codigoPostal Código postal a ser verificado.
     * @return True se o código postal for válido, False se não for.
     */
    public static boolean verificarCodigoPostal(String codigoPostal) {
        return codigoPostal.matches("^\\d{4}-\\d{3}$");
    }

    /**
     * Esta função solicita uma palavra-passe ao utilizador e compara a sua hash com a hash <br>
     * da imobiliária passada por parâmetro.
     * @param imobiliaria Imobiliaria a iniciar sessão
     * @return Verdadeiro se for permitido o acesso, falso se não.
     */
    public static boolean iniciarSessao(Imobiliaria imobiliaria) {
        // Solicitar uma palavra-passe
        System.out.println("\n" + imobiliaria.getDescricao());
        System.out.print("Palavra-passe: ");
        String palavraPasse = Utils.lerString();

        // Comparar hash da palavra-passe inserida com a da imobiliária
        // Devolver verdadeiro se corresponderem e falso se não.
        if (Utils.compararHash(imobiliaria.getHash(), Utils.hashString(palavraPasse))) {
            return true;
        } else {
            System.out.println("Palavra-passe incorreta.");
            return false;
        }
    }
}
