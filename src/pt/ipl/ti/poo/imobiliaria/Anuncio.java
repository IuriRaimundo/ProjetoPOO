package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;

public abstract class  Anuncio {
    private double preco;
    private final Data data;
    private final Imovel imovel;

    public Anuncio(double preco, Data data, Imovel imovel){
        this.preco = preco;
        this.data = data;
        this.imovel = imovel;
    }

    /**
     * Função para obter o preço do anúncio
     * @return preço
     */
    public double getPreco(){
        return preco;
    }

    public Data getData(){
        return data;
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
