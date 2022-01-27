package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Esta classe representa um imóvel, contém informações como uma descrição, localização do imóvel, área do imóvel.
 */
public  class Imovel extends Descritor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Área do imóvel, em metros quadrados.
     */
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

    @Override
    public String toString() {
        return super.toString() + "\tÁrea = " + area + "m²";
    }

    /**
     * Dois imóveis são considerados iguais quando têm a mesma localização, <br>
     * ignorando os restantes atributos.
     * @param o Objeto a comparar
     * @return Verdadeiro se forem iguais, falso se forem diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imovel)) return false;
        Imovel imovel = (Imovel) o;
        return Objects.equals(getLocalizacao(), imovel.getLocalizacao());
    }
}
