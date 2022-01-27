package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

public class Habitacao extends Imovel
        implements ImoveisQuePodemSerAlugados, ImoveisQuePodemSerVendidos, Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
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

    /**
     * Função para listar os parâmetros no Main
     * @return do super e dos quartos
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tQuartos: " + quartos;
    }
}
