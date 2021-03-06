package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

/**
 * Serve para efetuar classificar uma habitação como Apartamento.
 */
public class Apartamento extends Habitacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe Apartamento
     * @param imovel do tipo habitação
     */
    public Apartamento(Habitacao imovel) {
        this(imovel.getDescricao(), imovel.getLocalizacao(), imovel.getArea(), imovel.getQuartos());
    }

    /**
     * Construtor da classe Apartamento
     * @param descricao do apartamento
     * @param localizacao do apartamento
     * @param area do apartamento
     * @param quartos do apartamento
     */
    public Apartamento(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area, quartos);
    }

    @Override
    public String toString() {
        return "\n\tTipo: Apartamento\n" + super.toString() + "\n";
    }
}
