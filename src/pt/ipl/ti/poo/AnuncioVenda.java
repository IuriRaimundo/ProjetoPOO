package pt.ipl.ti.poo;

public class AnuncioVenda extends Anuncio {

    public AnuncioVenda(double preco, Data data, ImoveisQuePodemSerVendidos imovel){
        super(preco, data, (Imovel) imovel);
    }

}
