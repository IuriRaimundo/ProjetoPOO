package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

public class Moradia extends Habitacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int pisos;

    /**
     * Construtor da Moradia
     * @param imovel Dados da Moradia num objeto do tipo Habitacao
     * @param pisos Número de pisos na moradia.
     */
    public Moradia(Habitacao imovel, int pisos) {
        this(imovel.getDescricao(), imovel.getLocalizacao(), imovel.getArea(), imovel.getQuartos(), pisos);
    }

    /**
     * Construtor da Moradia
     * @param descricao Descrição / Nome da moradia
     * @param localizacao Localização da moradia
     * @param area Área, em metros quadrados, da moradia
     * @param quartos Número de quartos na moradia
     * @param pisos Número de pisos na moradia.
     */
    public Moradia(String descricao, Localizacao localizacao, double area, int quartos, int pisos){
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
