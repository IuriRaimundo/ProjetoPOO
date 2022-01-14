package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

public class AnuncioVenda extends Anuncio {

    public AnuncioVenda(double preco, Data data, ImoveisQuePodemSerVendidos imovel){
        super(preco, data, (Imovel) imovel);
    }

}
