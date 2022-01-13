package pt.ipl.ti.poo;

public class Habitacao extends Imovel {
    private final int quartos;

    public Habitacao(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area);
        this.quartos = quartos;
    }

    public int getQuartos(){
        return quartos;
    }
}
