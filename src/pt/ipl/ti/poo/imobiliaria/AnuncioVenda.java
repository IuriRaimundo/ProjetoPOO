package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Anúncio de venda de um imóvel
 */
public class AnuncioVenda extends Anuncio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Imóvel listado no anúncio
     */
    private final ImoveisQuePodemSerVendidos imovel;

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

    @Override
    public String toString() {
        return "Anúncio Venda - " + super.toString() + imovel.toString();
    }

    /**
     * Dois anúncios são considerados iguais quando têm o mesmo imóvel. <br>
     * Caso seja necessário efetuar um novo anúncio com outro preço, <br>
     * primeiro deve ser concretizado o anúncio ativo.
     * @param o Anúncio de venda a ser comparado
     * @return Verdadeiro se forem iguais, falso se forem diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnuncioVenda)) return false;
        AnuncioVenda anuncio = (AnuncioVenda) o;
        return Objects.equals(imovel, anuncio.getImovel());
    }
}
