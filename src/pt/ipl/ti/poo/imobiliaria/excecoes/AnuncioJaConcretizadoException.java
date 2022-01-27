package pt.ipl.ti.poo.imobiliaria.excecoes;

/**
 * Exceção para quando é tentado concretizar um anúncio que já se encontra concretizado.
 */
public class AnuncioJaConcretizadoException extends Exception {
    public AnuncioJaConcretizadoException() {
        super("Não é possível concretizar o mesmo anúncio mais de uma vez!");
    }
}
