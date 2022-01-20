package pt.ipl.ti.poo.imobiliaria;

public class Terreno extends Imovel implements ImoveisQuePodemSerVendidos{

    public Terreno(String descricao, Localizacao localizacao, double area) {
        super(descricao, localizacao, area);
    }

    @Override
    public String toString() {
        return "\tTipo: Terreno " + super.toString() + "\n";
    }
}