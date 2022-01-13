package pt.ipl.ti.poo;

public class Terreno extends Imovel implements ImoveisQuePodemSerVendidos{

    public Terreno(String descricao, Localizacao localizacao, double area) {
        super(descricao, localizacao, area);
    }
}