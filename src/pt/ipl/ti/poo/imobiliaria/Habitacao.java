package pt.ipl.ti.poo.imobiliaria;

public abstract class Habitacao extends Imovel {
    private final int quartos;

    public Habitacao(String descricao, Localizacao localizacao, double area, int quartos){
        super(descricao, localizacao, area);
        this.quartos = quartos;
    }

    /**
     * Função para obter os quartos
     * @return número de quartos
     */
    public int getQuartos(){
        return quartos;
    }

    /**
     * Função para listar os parâmetros no Main
     * @return do super e dos quartos
     */
    @Override
    public String toString() {
        return super.toString() + "\tQuartos: " + quartos;
    }
}
