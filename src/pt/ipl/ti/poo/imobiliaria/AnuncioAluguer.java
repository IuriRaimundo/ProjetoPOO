package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

public class AnuncioAluguer extends Anuncio {
    private final int duracao;

    public AnuncioAluguer(double preco, Data data, ImoveisQuePodemSerAlugados imovel, int duracao){
        super(preco, data, (Imovel) imovel);
        this.duracao = duracao;
    }

    /**
     * Função para obter a duração do aluguer
     * @return Duração do aluguer, em meses.
     */
    public int getDuracao(){
        return duracao;
    }
}
