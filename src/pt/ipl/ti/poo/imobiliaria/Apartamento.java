package pt.ipl.ti.poo.imobiliaria;

public class Apartamento extends Habitacao implements ImoveisQuePodemSerVendidos, ImoveisQuePodemSerAlugados {

    public Apartamento(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area, quartos);
    }

    /**
    * Função para listar os parâmetros no Main
    * @return do super
    */
    @Override
    public String toString() {
        return "\tTipo: Apartamento\n" + super.toString() + "\n";
    }
}
