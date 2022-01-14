package pt.ipl.ti.poo.imobiliaria;

public class Moradia extends Habitacao implements ImoveisQuePodemSerVendidos, ImoveisQuePodemSerAlugados{
    private final int pisos;

    public Moradia (String descricao, Localizacao localizacao, double area, int quartos,int pisos){
        super(descricao, localizacao, area, quartos);
        this.pisos = pisos;
    }

    public int getPisos(){
        return pisos;
    }
}
