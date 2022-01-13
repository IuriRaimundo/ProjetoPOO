package pt.ipl.ti.poo;

public class Apartamento extends Habitacao implements ImoveisQuePodemSerVendidos, ImoveisQuePodemSerAlugados {

    public Apartamento(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area, quartos);
    }

}
