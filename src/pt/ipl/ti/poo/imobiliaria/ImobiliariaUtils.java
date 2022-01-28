package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Utils;
import pt.ipl.ti.poo.menu.Menu;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * Esta classe contém funções de utilidade para efetuar operações relacionadas com a Imobiliária. <br>
 * Contém métodos para criar os objetos do package imobiliaria e métodos para alterar uma mobiliária com as devidas proteções.
 */
public abstract class ImobiliariaUtils {

    /**
     * Nome do ficheiro das imobiliárias
     */
    public static final String NOME_FICH_IMOBILIARIAS = "imobiliarias.dat";
    /**
     * Nome do ficherio temporário utilizado para detetar alterações no programa
     */
    public static final String NOME_FICH_TMP = "tmp.dat";
    /**
     * Nome do ficheiro temporário utilizado para guardar o estado da imobiliária no inicio de uma sessão
     */
    public static final String NOME_FICH_IMOBILIARIAS_TMP = "imobTmp.dat";
    /**
     * Mínimo de área para imóveis, em metros quadrados.
     */
    private static final double MIN_AREA_M2 = 0;
    /**
     * Máximo de área para imóveis, em metros quadrados.
     */
    private static final double MAX_AREA_M2 = 10000000;
    /**
     * Máximo de quartos para habitações
     */
    private static final int MAX_QUARTOS = 30;
    /**
     * Máximo de pisos para moradias
     */
    private static final int MAX_PISOS = 5;
    /**
     * Tamanho mínimo para a palavra-passe de uma imobiliária
     */
    private static final int MIN_TAM_PW = 5;

    /**
     * Tamanho máximo para a palavra-passe de uma imobiliária
     */
    private static final int MAX_TAM_PW = 16;


    /**
     * Função para criar anúncio, solicita-se ao utilizador
     * o tipo de anúncio a ser criado e os dados correspondentes ao anúncio. <br>
     * O anúncio criado é adicionado à lista de anúncios ativos da imobiliária recebida.
     *
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

    /**
     * Esta função cria um anúncio de venda
     *
     * @param preco  Preço do imóvel
     * @param imovel Imovél a ser vendido
     * @return Anúncio de venda criado.
     */
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
        double area = Utils.lerDouble(MIN_AREA_M2, MAX_AREA_M2, "Insira uma área entre " + MIN_AREA_M2 + " e " + MAX_AREA_M2 + ": ");

        Localizacao localizacao = criarLocalizacao();

        return new Imovel(nome, localizacao, area);
    }

    /**
     * Função para criar Habitação <br>
     * Recebe um imóvel e cria uma habitação com os dados do imóvel e o número de quartos solicitado ao utilizador.
     * Esta função pede o número de quartos ao utilizador
     *
     * @param imovel Dados do imóvel.
     * @return Nova Habitação.
     */
    private static Habitacao criarHabitacao(Imovel imovel) {
        System.out.print("Quartos: ");
        int quartos = Utils.lerInteiro(1, MAX_QUARTOS);
        return new Habitacao(imovel, quartos);
    }

    /**
     * Função para criar a Moradia <br>
     * Recebe uma habitação e cria uma moradia com os dados da habitação e o número de pisos solicitado ao utilizador.
     *
     * @param habitacao Dados da habitação.
     * @return Nova Moradia.
     */
    private static Moradia criarMoradia(Habitacao habitacao) {
        System.out.print("Pisos: ");
        int pisos = Utils.lerInteiro(1, MAX_PISOS);
        return new Moradia(habitacao, pisos);
    }

    /**
     * Função para criar novo Apartamento, apenas converte uma Habitacao para Apartamento, uma vez que contêm os mesmos atributos e métodos <br>
     * É efetuada esta conversão para fazer distinção entre os apartamentos e moradias.
     *
     * @param habitacao Habitacao a ser convertida.
     * @return Novo Apartamento.
     */
    private static Apartamento criarApartamento(Habitacao habitacao) {
        return new Apartamento(habitacao);
    }

    /**
     * Função para criar novo Terreno, apenas converte um Imovel para Terreno, uma vez que contêm os mesmos atributos e métodos. <br>
     * É efetuada esta conversão para fazer distinção entre os terrenos e quartos, que são admitidos em tipos de anúncios diferentes.
     *
     * @param imovel Imovel a converter
     * @return novo Terreno
     */
    private static Terreno criarTerreno(Imovel imovel) {
        return new Terreno(imovel);
    }

    /**
     * Função para criar novo Quarto, apenas converte um Imovel em Quarto, uma vez que contêm os mesmos atributos e métodos. <br>
     * É efetuada esta conversão para fazer distinção entre os terrenos e quartos, que são admitidos em tipos de anúncios diferentes.
     *
     * @param imovel Imovel a ser convertido
     * @return Novo Quarto
     */
    private static Quarto criarQuarto(Imovel imovel) {
        return new Quarto(imovel);
    }

    /**
     * Função para criar nova Localização <br>
     * Esta função pede os diferentes parâmetros ao utilizador
     *
     * @return Nova Localização
     */
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
                        System.out.println("\n" + anuncio);
                    }
                }
            }
            case 2 -> {
                for (Anuncio anuncio : anuncios) {
                    if (anuncio instanceof AnuncioAluguer) {
                        System.out.println("\n" + anuncio);
                    }
                }
            }
            case 3 -> {
                for (Anuncio anuncio : anuncios) {
                    System.out.println("\n" + anuncio);
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

        if (imobiliaria.getTotalAnunciosAtivos() == 0) {
            System.out.println("Não existem anúncios ativos.");
            return;
        }

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
        System.out.println("Receita de alugueres prevista: " + imobiliaria.getReceitaPrevistaAlugueres() + "€");
        System.out.println("Receitas previstas: " + imobiliaria.getTotalReceitasPrevistas() + "€");
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
            oout.close();
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
     * @return Palavra-passe introduzida
     */
    public static String criarPalavraPasse() {
        String palavraPasse, confirmacao = "";
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
     *
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

    /**
     * Esta função apaga uma imobiliária de uma lista de imobiliárias,
     * é solicitado a palavra passe da imobiliária e uma confirmação para evitar que a imobiliária seja apagada
     * por engano.
     *
     * @param imobiliarias Lista de imobiliárias
     * @param imobiliaria  Imobiliária a ser removida
     * @return Verdadeiro se apagou, falso se não apagou
     */
    public static boolean apagarImobiliaria(LinkedList<Imobiliaria> imobiliarias, Imobiliaria imobiliaria) {
        if (iniciarSessao(imobiliaria)) {
            System.out.print("Está prestes a apagar esta imobiliária, esta ação é automaticamente gravada e irreversível,\nescreva CONFIRMAR para proceder: ");
            String confirmacao = Utils.lerString();
            if (confirmacao.equals("CONFIRMAR")) {
                imobiliarias.remove(imobiliaria);
                System.out.println("\nImobiliária " + imobiliaria.getDescricao() + " foi removida.\n");
                return true;
            } else {
                System.out.println("Operação de apagar cancelada.");
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Função para verificar se existem diferenças entre uma lista de imobiliárias e a lista armazenada no ficheiro NOME_FICH_IMOBILIARIAS
     * @param imobiliarias Lista de imobiliaria a ser comparada
     * @return Verdadeiro se existir diferenças, falso se forem iguais
     */
    public static boolean verificarAlteracoesDados(LinkedList<Imobiliaria> imobiliarias) {

        boolean alteracoes = false;

        try {
            // Abrir ficheiro temporário
            File tmp = new File(NOME_FICH_TMP);

            if (!tmp.exists()) {
                tmp.createNewFile();
            }

            // Obter ficheiro utilizado para guardar os dados do programa.
            File fichImob = new File(NOME_FICH_IMOBILIARIAS);

            // Gravar os dados das imobiliárias para o ficherio temporário
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(tmp));
            oout.writeObject(imobiliarias);
            oout.close();

            // Obter hash dos dois ficheiros
            MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
            byte[] tmpHash, fichImobHash;
            tmpHash = Utils.getFileChecksum(shaDigest, tmp);
            fichImobHash = Utils.getFileChecksum(shaDigest, fichImob);

            // Se as hash forem diferentes, houveram alterações
            alteracoes = !Utils.compararHash(tmpHash, fichImobHash);

        } catch (IOException | NoSuchAlgorithmException e) {
            if (!(e instanceof FileNotFoundException)) {
                System.out.println("Falha a verificar alterações nos dados.");
            }
        }

        return alteracoes;
    }
}
