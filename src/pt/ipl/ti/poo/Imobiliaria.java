package pt.ipl.ti.poo;

import java.util.LinkedList;

public class Imobiliaria extends Descritor {
    private LinkedList<Anuncio> anunciosAtivos;
    private LinkedList<Anuncio> anunciosConcretizados;

    public Imobiliaria(String descricao, Localizacao localizacao){
        super(descricao, localizacao);
        anunciosAtivos = new LinkedList<>();
        anunciosConcretizados = new LinkedList<>();
    }

    public LinkedList<Anuncio> getAnunciosAtivos(){
        return new LinkedList<>(anunciosAtivos);
    }

    public LinkedList<Anuncio> getAnunciosConcretizados(){
        return new LinkedList<>(anunciosConcretizados);
    }

    public void adicionarAnuncio(Anuncio anuncio) {
        if (anuncio == null || anunciosAtivos.contains(anuncio)) return;
        anunciosAtivos.add(anuncio);
    }

    public void concluirAnuncio(Anuncio anuncio) {
        if (anuncio == null || !anunciosAtivos.contains(anuncio) || anunciosConcretizados.contains(anuncio)) return;
        anunciosAtivos.remove(anuncio);
        anunciosConcretizados.add(anuncio);
    }
}
