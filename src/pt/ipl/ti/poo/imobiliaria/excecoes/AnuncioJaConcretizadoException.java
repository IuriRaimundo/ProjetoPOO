package pt.ipl.ti.poo.imobiliaria.excecoes;

import pt.ipl.ti.poo.imobiliaria.Anuncio;

public class AnuncioJaConcretizadoException extends Exception {
    public AnuncioJaConcretizadoException() {
        super("Não é possível concretizar o mesmo anúncio mais de uma vez!");
    }
}
