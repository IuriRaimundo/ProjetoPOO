package pt.ipl.ti.poo;

public class Moradia extends Habitacao {
    private final int pisos;

    public Moradia (String descricao, Localizacao localizacao, double area, int quartos,int pisos){
        super(descricao, localizacao, area, quartos);
        this.pisos = pisos;
    }

    public int getPisos(){
        return pisos;
    }
}