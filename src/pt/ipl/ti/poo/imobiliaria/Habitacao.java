package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

/**
 * Uma habitação é um imóvel com número de quartos.
 * Uma habitação pode ser vendida e alugada.
 */
public class Habitacao extends Imovel
        implements ImoveisQuePodemSerAlugados, ImoveisQuePodemSerVendidos, Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Número de quartos do imóvel
     */
    private final int quartos;

    /**
     * Construtor da Habitação
     * @param dadosImovel da habitação
     * @param quartos da habitação
     */
    public Habitacao(Imovel dadosImovel, int quartos) {
        this(dadosImovel.getDescricao(), dadosImovel.getLocalizacao(), dadosImovel.getArea(), quartos);
    }

    /**
     * Construtor da Habitação
     * @param descricao da habitação
     * @param localizacao da habitação
     * @param area da habitação
     * @param quartos da habitação
     */
    public Habitacao(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area);
        this.quartos = quartos;
    }

    /**
     * Função para obter os quartos
     * @return número de quartos
     */
    public int getQuartos(){
        return quartos;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tQuartos: " + quartos;
    }
}
