package pt.ipl.ti.poo.imobiliaria;

import java.io.Serial;
import java.io.Serializable;

/**
 * O descritor serve para identificar, contém uma descrição / nome e uma localização.
 */
public abstract class Descritor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Descrição / Nome
     */
    private final String descricao;

    /**
     * Localização
     */
    private final Localizacao localizacao;

    /**
     * Construtor do Descritor
     * @param descricao Descrição / Nome
     * @param localizacao Localização
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

    @Override
    public String toString() {
        return "\tDescrição: " + descricao + "\n" + localizacao.toString();
    }
}
