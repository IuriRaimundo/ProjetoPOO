package pt.ipl.ti.poo;

public class Imovel extends Descritor {
    private final double area;

    public Imovel(String descricao, Localizacao localizacao, double area){
        super(descricao, localizacao);
        this.area = area;
    }

    public double getArea(){
        return area;
    }
}
