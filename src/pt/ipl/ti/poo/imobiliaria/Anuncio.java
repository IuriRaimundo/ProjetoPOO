package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;
import pt.ipl.ti.poo.imobiliaria.excecoes.AnuncioJaConcretizadoException;

import java.io.Serial;
import java.io.Serializable;

/**
 * A classe Anuncio é a base de qualquer tipo de anúncio da imobiliária.
 * Ela contém o preço, data de publicação do anúncio de data de concretização.
 * É abstrada para permitir apenas a criação de anúncios de venda ou aluguer.
 */
public abstract class Anuncio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Preço do imóvel, no caso de alugueres corresponde ao preço mensal.
     */
    private double preco;

    /**
     * Data de publicação do anúncio, é inicializada automáticamente com a data atual no construtor.
     */
    private final Data dataPublicacao;

    /**
     * Data de concretização do anúncio, é inicializado a null pelo construtor e a sua alteração implica
     * a concretização do anúncio, deixando este de ser ativo.
     */
    private Data dataConcretizacao;

    /**
     * Caso seja passado um preço menor do que zero, o preço é inicializado a 0.
     * @param preco Preço do anúncio
     */
    public Anuncio(double preco){
        this.preco = Math.max(preco, 0);
        this.dataPublicacao = Data.getDataAtual();
        this.dataConcretizacao = null;
    }

    /**
     * Função para obter o preço do anúncio
     * @return Preço do anúncio
     */
    public double getPreco(){
        return preco;
    }

    /**
     * Função para obter a data de publicação
     * @return Data de publicação
     */
    public Data getDataPublicacao(){
        return dataPublicacao;
    }

    /**
     * Função para alterar o preço
     * @param preco Preço do anúncio
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Função para obter a data de concretização
     * @return Data de concretização do anúncio
     */
    public Data getDataConcretizacao() {
        return dataConcretizacao;
    }

    /**
     * Função para registar concretizado
     * @throws AnuncioJaConcretizadoException Caso o anúncio já se encontre concretizado é lançada esta exceção
     */
    public void registarConcretizado() throws AnuncioJaConcretizadoException {
        if (dataConcretizacao != null) throw new AnuncioJaConcretizadoException();
        dataConcretizacao = Data.getDataAtual();
    }

    @Override
    public String toString() {
        return (dataConcretizacao == null ? "Publicado em " + getDataPublicacao().toString() : "Concretizado em " + getDataConcretizacao().toString()) + "\n\tPreço: " + preco + " €";
    }
}
