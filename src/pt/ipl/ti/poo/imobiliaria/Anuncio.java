package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

public abstract class  Anuncio {
    private double preco;
    private final Data data;
    private final Imovel imovel;

    public Anuncio(double preco, Data data, Imovel imovel){
        this.preco = preco;
        this.data = data;
        this.imovel = imovel;
    }

    public double getPreco(){
        return preco;
    }

    public Data getData(){
        return data;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Imovel getImovel(){
        return imovel;
    }
}
