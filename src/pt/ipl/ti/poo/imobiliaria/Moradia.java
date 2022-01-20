package pt.ipl.ti.poo.imobiliaria;

public class Moradia extends Habitacao implements ImoveisQuePodemSerVendidos, ImoveisQuePodemSerAlugados{
    private final int pisos;

    public Moradia (String descricao, Localizacao localizacao, double area, int quartos,int pisos){
        super(descricao, localizacao, area, quartos);
        this.pisos = pisos;
    }

    /**
     * Função para obter o número de pisos na moradia
     * @return pisos Número de pisos na moradia
     */
    public int getPisos(){
        return pisos;
    }

    @Override
    public String toString() {
        return "\tTipo: Moradia " + "\n" + super.toString() + "Pisos: " + pisos + "\n";
    }
}
