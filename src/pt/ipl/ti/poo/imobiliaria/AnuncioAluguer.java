package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AnuncioAluguer extends Anuncio implements Serializable {
    private final int duracao;
    private ImoveisQuePodemSerAlugados imovel;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe Anuncio de Aluguer
     * @param preco Preço do anuncio de aluguer
     * @param imovel Imóvel a ser alugado
     * @param duracao Duração do aluguer em meses
     */
    public AnuncioAluguer(double preco, ImoveisQuePodemSerAlugados imovel, int duracao){
        super(preco);
        this.duracao = duracao;
        this.imovel = imovel;
    }

    /**
     * Função para obter a duração do aluguer
     * @return Duração do aluguer, em meses.
     */
    public int getDuracao(){
        return duracao;
    }

    /**
     * Função para obter o imovel listado no anúncio
     * @return Imóvel listado no anúncio
     */
    public ImoveisQuePodemSerAlugados getImovel() {
        return imovel;
    }

    @Override
    public String toString() {
        return "Anúncio Aluguer - " + super.toString() + "\n\tDuração: " + duracao + " meses" + imovel.toString();
    }

    /**
     * Dois anúncios são considerados iguais quando têm o mesmo imóvel.
     * Caso seja necessário efetuar um novo anúncio com outro preço / duração deve ser concretizado o mesmo
     * @param o Anúncio de aluguer a ser comparado
     * @return Verdadeiro se forem iguais, falso se forem diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AnuncioAluguer)) return false;
        AnuncioAluguer anuncio = (AnuncioAluguer) o;
        return Objects.equals(imovel, anuncio.getImovel());
    }
}
