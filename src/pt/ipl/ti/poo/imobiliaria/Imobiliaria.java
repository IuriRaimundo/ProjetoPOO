package pt.ipl.ti.poo.imobiliaria;

import java.util.LinkedList;

/**
 * Classe Imobiliária, guarda uma lista de anúncios ativos e outra de concretizados.
 */
public class Imobiliaria extends Descritor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final LinkedList<Anuncio> anunciosAtivos;
    private final LinkedList<Anuncio> anunciosConcretizados;

    public Imobiliaria(String descricao, Localizacao localizacao){
        super(descricao, localizacao);
        anunciosAtivos = new LinkedList<>();
        anunciosConcretizados = new LinkedList<>();
    }

    /**
     * Função para obter uma cópia da lista dos anúncios ativos
     * @return Cópia da lista dos anúncios ativos
     */
    public LinkedList<Anuncio> getAnunciosAtivos(){
        return new LinkedList<>(anunciosAtivos);
    }

    /**
     * Função para obter uma cópia da lista dos anúncios concretizados
     * @return Cópia da lista dos anúncios concretizados
     */
    public LinkedList<Anuncio> getAnunciosConcretizados(){
        return new LinkedList<>(anunciosConcretizados);
    }

    /**
     * Função para obter uma lista apenas dos anúncios de venda ativos
     * @return Lista dos anúncios de venda ativos
     */
    public LinkedList<AnuncioVenda> getAnunciosVendaAtivos() {
        LinkedList<AnuncioVenda> anunciosVenda = new LinkedList<>();
        for (Anuncio anuncio : anunciosAtivos) {
            if (anuncio instanceof AnuncioVenda) {
                anunciosVenda.add((AnuncioVenda) anuncio);
            }
        }
        return anunciosVenda;
    }

    /**
     * Função para obter uma lista apenas dos anúncios de venda concretizados
     * @return Lista dos anúncios de venda concretizados
     */
    public LinkedList<AnuncioVenda> getAnunciosVendaConcretizados() {
        LinkedList<AnuncioVenda> anunciosVenda = new LinkedList<>();
        for (Anuncio anuncio : anunciosConcretizados) {
            if (anuncio instanceof AnuncioVenda) {
                anunciosVenda.add((AnuncioVenda) anuncio);
            }
        }
        return anunciosVenda;
    }

    /**
     * Função para obter uma lista dos anúncios de aluguer ativos
     * @return Lista dos anuncios de aluguer ativos
     */
    public LinkedList<AnuncioAluguer> getAnunciosAluguerAtivos() {
        LinkedList<AnuncioAluguer> anunciosAluguer = new LinkedList<>();
        for (Anuncio anuncio : anunciosAtivos) {
            if (anuncio instanceof AnuncioAluguer) {
                anunciosAluguer.add((AnuncioAluguer) anuncio);
            }
        }
        return anunciosAluguer;
    }

    /**
     * Função para obter uma lista de anúncios de aluguer concretizados
     * @return Lista de anúncios de aluguer concretizados
     */
    public LinkedList<AnuncioAluguer> getAnunciosAluguerConcretizados() {
        LinkedList<AnuncioAluguer> anunciosAluguer = new LinkedList<>();
        for (Anuncio anuncio : anunciosConcretizados) {
            if (anuncio instanceof AnuncioAluguer) {
                anunciosAluguer.add((AnuncioAluguer) anuncio);
            }
        }
        return anunciosAluguer;
    }

    /**
     * Função para adicionar anúncios à imobiliária
     * Os contadores de anúncios ativos do tipo correspondente ao anúncio adicionado são atualizados.
     * @param anuncio Anúncio a ser adicionado à imobiliária
     */
    public void adicionarAnuncio(Anuncio anuncio) {
        if (anuncio == null || anunciosAtivos.contains(anuncio)) return;
        anunciosAtivos.add(anuncio);
        // Atualizar contadores
        if (anuncio instanceof AnuncioVenda) {
            contAnunciosVendaAtivos++;
        } else {
            contAnunciosAluguerAtivos++;
        }
    }

    /**
     * Função para concretizar um anúncio na imobiliária
     * A função atualiza os contadores correspondentes ao tipo de anúncio concretizado
     * É atribuida a data de concretização ao anúncio para a mesma ser usada em estatísticas.
     * Se o anúncio já estiver concretizado, não é adicionado à lista.
     * @param anuncio Anúncio para concretizar
     */
    public void concretizarAnuncio(Anuncio anuncio) {
        if (anuncio == null || !anunciosAtivos.contains(anuncio) || anunciosConcretizados.contains(anuncio)) return;

        // Remover anúncio da lista de anúncios ativos
        anunciosAtivos.remove(anuncio);

        try {
            anuncio.registarConcretizado();
        } catch (AnuncioJaConcretizadoException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Atualizar contadores
        if (anuncio instanceof AnuncioVenda) {
            contAnunciosVendaAtivos--;
            contAnunciosVendaConcretizados++;
        } else {
            contAnunciosAluguerAtivos--;
            contAnunciosAluguerConcretizados++;
        }

        // Adicionar à lista de anúncios concretizados
        anunciosConcretizados.add(anuncio);
    }
}
