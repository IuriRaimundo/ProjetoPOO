package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

import java.io.Serial;
import java.io.Serializable;

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
        return "\nAnúncio Aluguer" + super.toString() + "\tDuração: " + duracao + " meses\n" + "\n" + imovel.toString() + "\n";
    }
}
