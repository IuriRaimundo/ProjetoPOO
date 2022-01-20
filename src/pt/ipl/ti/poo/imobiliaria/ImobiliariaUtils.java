package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Utils;
import pt.ipl.ti.poo.menu.Menu;

public abstract class ImobiliariaUtils {

    private static final double MIN_AREA_KM2 = 0;
    private static final double MAX_AREA_KM2 = 10000;
    private static final int MAX_QUARTOS = 20;
    private static final int MAX_PISOS = 5;

    /**
     * Função para criar anúncio
     * @return
     */
    public static Anuncio criarAnuncio() {
        // Obter escolha do tipo de anúncio
        Menu menu = new Menu("Anúncio de aluguer", "Anúncio de venda");
        menu.apresentar();
        int escolhaAnuncio = menu.obterEscolha();

        System.out.print("Preço (€): " );
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

        // Se a escolha for habitação
        if (escolhaImovel == OpcoesImovel.MORADIA.getNumeroOpcao() || escolhaImovel == OpcoesImovel.APARTAMENTO.getNumeroOpcao()) {
            Habitacao novaHabitacao = criarHabitacao(dadosImovel);
            // Criar moradia ou apartamento
            novaHabitacao = escolhaImovel == OpcoesImovel.MORADIA.getNumeroOpcao() ? criarMoradia(novaHabitacao) : criarApartamento(novaHabitacao);
            // Devolver anúncio de aluguer ou venda
            return escolhaAnuncio == 1 ? criarAnuncioAluguer(preco, novaHabitacao): criarAnuncioVenda(preco, novaHabitacao);
        }
        // Se a escolha for um quarto ou terreno
        if (escolhaImovel == OpcoesImovel.QUARTO.getNumeroOpcao() || escolhaImovel == OpcoesImovel.TERRENO.getNumeroOpcao()){
            // Devolver anúncio de aluguer ou de venda, o de aluguer será um quarto e de venda será terreno.
            return escolhaAnuncio == 1 ? criarAnuncioAluguer(preco, criarQuarto(dadosImovel)) : criarAnuncioVenda(preco, criarTerreno(dadosImovel));
        }
        // Escolha inválida
        throw new IllegalArgumentException("Escolha de imóvel incorreta.");
    }

    private static AnuncioAluguer criarAnuncioAluguer(double preco, ImoveisQuePodemSerAlugados imovel) {
        System.out.print("Duracao do aluguer (meses): ");
        int duracao = Utils.lerInteiro(0, 999);
        return new AnuncioAluguer(preco, imovel, duracao);
    }

    private static AnuncioVenda criarAnuncioVenda(double preco, ImoveisQuePodemSerVendidos imovel) {
        return new AnuncioVenda(preco, imovel);
    }

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
        return new Moradia(imovel, pisos);
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
        System.out.print("Código Postal: ");
        String codigoPostal = Utils.lerString();
        System.out.print("País: ");
        String pais = Utils.lerString();
        return new Localizacao(pais, cidade, morada, codigoPostal);
    }
}
