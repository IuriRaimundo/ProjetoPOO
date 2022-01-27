package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Esta classe armazena os dados de uma localização, a morada, cidade, país e código postal.<br>
 * Ela é usada para localizar imobiliárias e imóveis.
 */
public class Localizacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String pais;
    private String cidade;
    private String morada;
    private String codigoPostal;

    /**
     * Construtor da Localização
     * @param pais Nome do país
     * @param cidade Nome da cidade
     * @param morada Lote / Número / Andar e nome de rua
     * @param codigoPostal Código postal
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
     * Devolve a uma string com a localização formatada
     * @return Localização formatada
     */
    @Override
    public String toString() {
        return "\tLocalização: " + morada + ", " + cidade + ", " + codigoPostal + " " + pais + "\n";
    }

    /**
     * Duas localizações são iguais se o país, morada, cidade e código postal são iguais.
     * @param o Localização a comparar
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Localizacao)) return false;
        Localizacao localizacao = (Localizacao) o;
        return Objects.equals(pais, localizacao.pais)
                && Objects.equals(cidade, localizacao.cidade)
                && Objects.equals(morada, localizacao.morada)
                && Objects.equals(codigoPostal, localizacao.codigoPostal);
    }
}
