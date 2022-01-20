package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

public  class Imovel extends Descritor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final double area;

    /**
     * Construtor do Imóvel
     * @param descricao do imóvel
     * @param localizacao do imóvel
     * @param area do imóvel
     */
    public Imovel(String descricao, Localizacao localizacao, double area){
        super(descricao, localizacao);
        this.area = area;
    }

    /**
     * Função para obter a área
     * @return área
     */
    public double getArea(){
        return area;
    }

    /**
     * Função para listar os parâmetros no Main
     * @return do super e da área
     */
    @Override
    public String toString() {
        return super.toString() + "\tÁrea = " + area + "m²";
    }
}
