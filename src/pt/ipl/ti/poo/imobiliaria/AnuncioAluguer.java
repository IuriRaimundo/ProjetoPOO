package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Anúncio de aluguer de um imóvel
 */
public class AnuncioAluguer extends Anuncio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Duração do aluguer, inicializado no construtor. <br>
     * Caso seja passado para o construtor um valor negativo a duração é inicializada a 0.
     */
    private final int duracao;

    /**
     * Imóvel listado no anúncio.
     */
    private final ImoveisQuePodemSerAlugados imovel;

    /**
     * Construtor da classe Anuncio de Aluguer
     * @param preco Preço do anuncio de aluguer
     * @param imovel Imóvel a ser alugado
     * @param duracao Duração do aluguer em meses
     */
    public AnuncioAluguer(double preco, ImoveisQuePodemSerAlugados imovel, int duracao){
        super(preco);
        // Caso a duracao seja menor que 0, é inicializada para 0 para não quebrar os cálculos estatísticos dos restantes anúncios.
        this.duracao = Math.max(duracao, 0);
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
        if (!(o instanceof AnuncioAluguer)) return false;
        AnuncioAluguer anuncio = (AnuncioAluguer) o;
        return Objects.equals(imovel, anuncio.getImovel());
    }
}
