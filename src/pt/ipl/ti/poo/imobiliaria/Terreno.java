package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

public class Terreno extends Imovel
        implements ImoveisQuePodemSerVendidos, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor do Terreno, converte um objeto do tipo Imovel em um Terreno
     * @param imovel Imovel a ser convertido para Terreno
     */
    public Terreno(Imovel imovel) {
        this(imovel.getDescricao(), imovel.getLocalizacao(), imovel.getArea());
    }

    /**
     * Construtor do Terreno
     * @param descricao Descrição / Nome do terreno
     * @param localizacao Localização do terreno
     * @param area Área, em metros quadrados, do terreno
     */
    public Terreno(String descricao, Localizacao localizacao, double area) {
        super(descricao, localizacao, area);
    }

    @Override
    public String toString() {
        return "\tTipo: Terreno " + super.toString() + "\n";
    }
}