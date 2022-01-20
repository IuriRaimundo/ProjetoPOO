package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;
import pt.ipl.ti.poo.imobiliaria.excecoes.AnuncioJaConcretizadoException;

import java.io.Serial;
import java.io.Serializable;

public abstract class Anuncio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private double preco;
    private final Data dataPublicacao;
    private Data dataConcretizacao;

    /**
     * Construtor da Classe Anuncio
     * @param preco preço do anúncio
     */
    public Anuncio(double preco){
        this.preco = preco;
        this.dataPublicacao = Data.getDataAtual();
        this.dataConcretizacao = null;
    }

    /**
     * Função para obter o preço do anúncio
     * @return preço
     */
    public double getPreco(){
        return preco;
    }

    /**
     * Função para obter a data de publicação
     * @return data de publicação
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
     * @return data de concretização
     */
    public Data getDataConcretizacao() {
        return dataConcretizacao;
    }

    /**
     * Função para registar concretizado
     * Se o anuncio já estiver concretizado, é lançada uma exceção
     * @throws AnuncioJaConcretizadoException
     */
    public void registarConcretizado() throws AnuncioJaConcretizadoException {
        if (dataConcretizacao != null) throw new AnuncioJaConcretizadoException();
        dataConcretizacao = Data.getDataAtual();
    }

    /**
     * Função para listar os parâmetros no Main
     * @return data de publicação e preço
     */
    @Override
    public String toString() {
        return "Publicado em " + dataPublicacao.toString() + "\n\tPreço: " + preco + "\n";
    }
}
