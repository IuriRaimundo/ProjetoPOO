package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

public class Localizacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String pais;
    private String cidade;
    private String morada;
    private String codigoPostal;

    /**
     * Construtor da Localização
     * @param pais onde se situa o imóvel
     * @param cidade onde se situa o imóvel
     * @param morada onde se situa o imóvel
     * @param codigoPostal do imóvel
     */
    public Localizacao(String pais, String cidade, String morada, String codigoPostal) {
        this.pais = pais;
        this.cidade = cidade;
        this.morada = morada;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Função para obter o país
     * @return país
     */
    public String getPais() {
        return pais;
    }

    /**
     * Função para obter a cidade
     * @return cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Função para obter a morada
     * @return morada
     */
    public String getMorada(){
        return morada;
    }

    /**
     * Função para obter o código postal
     * @return código postal
     */
    public String getCodigoPostal(){
        return codigoPostal;
    }

    /**
     * Função para listar os parâmetros no Main
     * @return da morada, cidade, código postal e país
     */
    @Override
    public String toString() {
        return "\tLocalização: " + morada + ", " + cidade + ", " + codigoPostal + " " + pais + "\n";
    }
}
