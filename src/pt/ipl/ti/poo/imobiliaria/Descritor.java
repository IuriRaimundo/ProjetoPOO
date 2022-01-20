package pt.ipl.ti.poo.imobiliaria;

public abstract class Descritor {
    private final String descricao;
    private final Localizacao localizacao;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor do Descritor
     * @param descricao do imóvel
     * @param localizacao do imóvel
     */
    public Descritor(String descricao, Localizacao localizacao){
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    /**
     * Função para obter a descrição
     * @return descrição
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * Função para obter a localização
     * @return localização
     */
    public Localizacao getLocalizacao(){
        return localizacao;
    }

    /**
     * Função para listar os parâmetros no Main
     * @return da descrição e da localização
     */
    @Override
    public String toString() {
        return "Nome: " + descricao + "\n" + localizacao.toString();
    }
}
