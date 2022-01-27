package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
        return "Anúncio Venda - " + super.toString() + imovel.toString();
    }

    /**
     * Dois anúncios são considerados iguais quando têm o mesmo imóvel. <br>
     * Caso seja necessário efetuar um novo anúncio com outro preço, <br>
     * primeiro deve ser concretizado o anúncio ativo.
     * @param o Anúncio de venda a ser comparado
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AnuncioVenda)) return false;
        AnuncioVenda anuncio = (AnuncioVenda) o;
        return Objects.equals(imovel, anuncio.getImovel());
    }
}
