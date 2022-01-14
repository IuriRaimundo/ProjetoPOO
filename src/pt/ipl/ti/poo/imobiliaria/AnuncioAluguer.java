package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

public class AnuncioAluguer extends Anuncio {
    private final int duracao;

    public AnuncioAluguer(double preco, Data data, ImoveisQuePodemSerAlugados imovel, int duracao){
        super(preco, data, (Imovel) imovel);
        this.duracao = duracao;
    }

    public int getDuracao(){
        return duracao;
    }
}
