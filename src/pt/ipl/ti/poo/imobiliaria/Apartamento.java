package pt.ipl.ti.poo.imobiliaria;

public class Apartamento extends Habitacao implements ImoveisQuePodemSerVendidos, ImoveisQuePodemSerAlugados {

    public Apartamento(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area, quartos);
    }

}