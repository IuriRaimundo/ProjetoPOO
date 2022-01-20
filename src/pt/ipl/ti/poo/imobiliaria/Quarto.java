package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

public class Quarto extends Imovel implements ImoveisQuePodemSerAlugados {

    public Quarto(String descricao, Localizacao localizacao, double area) {
        super(descricao, localizacao, area);
    }

    /**
     * Função para listar os parâmetros no Main
     * @return do quarto e do super
     */
    @Override
    public String toString() {
        return "\t Quarto " + "\n" + super.toString();
    }
}

