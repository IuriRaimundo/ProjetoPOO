package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

import java.io.Serial;
import java.io.Serializable;

public class AnuncioVenda extends Anuncio implements Serializable {
    private ImoveisQuePodemSerVendidos imovel;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe anúncio de venda
     * @param preco do anúncio de venda
     * @param imovel do tipo imóveis que podem ser vendidos
     */
    public AnuncioVenda(double preco, ImoveisQuePodemSerVendidos imovel){
        super(preco);
        this.imovel = imovel;
    }

    /**
     * Função para obter imóvel do tipo que podem ser vendidos
     * @return imóvel do mesmo tipo
     */
    public ImoveisQuePodemSerVendidos getImovel() {
        return imovel;
    }

    /**
     * Função para listar os parâmetros no Main
     * @return do super e do imóvel
     */
    @Override
    public String toString() {
        return "\nAnúncio Venda" + super.toString() + imovel.toString();
    }
}
