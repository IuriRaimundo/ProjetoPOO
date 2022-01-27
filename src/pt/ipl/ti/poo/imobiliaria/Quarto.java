package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

/**
 * Serve para classificar um imóvel como um quarto.
 * É um imóvel que pode ser alugado.
 */
public class Quarto extends Imovel
        implements ImoveisQuePodemSerAlugados, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor do Quarto
     * @param imovel do tipo Imóvel
     */
    public Quarto(Imovel imovel) {
        this(imovel.getDescricao(), imovel.getLocalizacao(), imovel.getArea());
    }

    /**
     * Construtor do Quarto
     * @param descricao do quarto
     * @param localizacao do quarto
     * @param area do quarto
     */
    public Quarto(String descricao, Localizacao localizacao, double area) {
        super(descricao, localizacao, area);
    }

    /**
     * Função para listar os parâmetros no Main
     * @return do quarto e do super
     */
    @Override
    public String toString() {
        return "\n\tTipo: Quarto " + "\n" + super.toString();
    }
}

