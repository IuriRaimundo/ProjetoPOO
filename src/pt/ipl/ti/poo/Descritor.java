package pt.ipl.ti.poo;

public abstract class Descritor {
    private final String descricao;
    private final Localizacao localizacao;

    public Descritor(String descricao, Localizacao localizacao){
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    public String getDescricao(){
        return descricao;
    }

    public Localizacao getLocalizacao(){
        return localizacao;
    }
}
