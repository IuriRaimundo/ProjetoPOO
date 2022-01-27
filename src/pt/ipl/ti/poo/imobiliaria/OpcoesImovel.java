package pt.ipl.ti.poo.imobiliaria;

/**
 * Este enumerado contém as opções de criação de um imóvel, é usado para obter as opções
 * de imóvel válidas para um determinado tipo de anúncio.
 */
public enum OpcoesImovel {
    /**
     * Opção de uma moradia
     */
    MORADIA(1, "Moradia"),
    /**
     * Opção de um apartamento
     */
    APARTAMENTO(2, "Apartamento"),
    /**
     * Opção de um quarto.
     * O terreno e o quarto partilham o mesmo número de opção uma vez
     * que nunca podem ser listados ao mesmo tempo uma vez que um quarto não pode ser vendido e o terreno não pode ser alugado.
     */
    QUARTO(3, "Quarto"),
    /**
     * Opção de um terreno
     */
    TERRENO(3, "Terreno");

    /**
     * Número da opção
     */
    private int numeroOpcao;

    /**
     * Texto da opção
     */
    private String textoOpcao;

    OpcoesImovel(int numeroOpcao, String textoOpcao) {
        this.numeroOpcao = numeroOpcao;
        this.textoOpcao = textoOpcao;
    }

    /**
     * Obter o número da opção
     * @return Número da opção
     */
    public int getNumeroOpcao() {
        return numeroOpcao;
    }

    /**
     * Obter o string da opção
     * @return String com o texto da opção
     */
    public String getTextoOpcao() {
        return textoOpcao;
    }

    /**
     * Função para obter as opções de imóvel disponíveis para um anúncio de aluguer.
     * @return Vetor de strings com o texto das opções disponíveis.
     */
    public static String[] getOpcoesAluguerString() {
        return new String[]{MORADIA.textoOpcao, APARTAMENTO.textoOpcao, QUARTO.textoOpcao};
    }

    /**
     * Função para obter as opções de imóvel disponíveis para um anúncio de venda.
     * @return Vetor de strings com o texto das opções disponíveis.
     */
    public static String[] getOpcoesVendaString() {
        return new String[]{MORADIA.textoOpcao, APARTAMENTO.textoOpcao, TERRENO.textoOpcao};
    }
}
