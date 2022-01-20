package pt.ipl.ti.poo.imobiliaria;

import pt.ipl.ti.poo.Data;
import pt.ipl.ti.poo.Utils;
import pt.ipl.ti.poo.imobiliaria.excecoes.AnuncioJaConcretizadoException;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe Imobiliária, guarda uma lista de anúncios ativos e outra de concretizados.
 */
public class Imobiliaria extends Descritor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final LinkedList<Anuncio> anunciosAtivos;
    private final LinkedList<Anuncio> anunciosConcretizados;

    private int contAnunciosVendaAtivos;
    private int contAnunciosVendaConcretizados;
    private int contAnunciosAluguerAtivos;
    private int contAnunciosAluguerConcretizados;

    /**
     * @param  descricao  Descrição / Nome da Imobiliária
     * @param  localizacao Localização da imobiliária
     */
    public Imobiliaria(String descricao, Localizacao localizacao){
        super(descricao, localizacao);
        anunciosAtivos = new LinkedList<>();
        anunciosConcretizados = new LinkedList<>();
        contAnunciosVendaAtivos = 0;
        contAnunciosVendaConcretizados = 0;
        contAnunciosAluguerAtivos = 0;
        contAnunciosAluguerConcretizados = 0;
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
        System.out.println("Anúncio concretizado com sucesso!");
    }

    /**
     * Função para obter o total de anúncios de venda ativos
     * @return Número de anúncios de venda ativos
     */
    public int getTotalAnunciosVendaAtivos() {
        return contAnunciosVendaAtivos;
    }

    /**
     * Função para obter o total de anúncios de venda concretizados
     * @return Número de anúncios de venda concretizados
     */
    public int getTotalAnunciosVendaConcretizados() {
        return contAnunciosVendaConcretizados;
    }

    /**
     * Função para obter o total de anúncios de aluguer ativos
     * @return Número de anúncios de aluguer ativos
     */
    public int getTotalAnunciosAluguerAtivos() {
        return contAnunciosAluguerAtivos;
    }

    /**
     * Função para obter o total de anúncios de aluguer concretizados
     * @return Número de anúncios de aluguer concretizados
     */
    public int getTotalAnunciosAluguerConcretizados() {
        return contAnunciosAluguerConcretizados;
    }

    /**
     * Função para obter o total de anúncios ativos
     * @return Número total de anúncios ativos
     */
    public int getTotalAnunciosAtivos() {
        return contAnunciosVendaAtivos + contAnunciosAluguerAtivos;
    }

    /**
     * Função para obter o total de anúncios concretizados
     * @return Número total de anúncios concretizados
     */
    public int getTotalAnunciosConcretizados() {
        return contAnunciosVendaConcretizados + contAnunciosAluguerConcretizados;
    }

    /**
     * Função para obter o total de anúncios
     * @return Número total de anúncios
     */
    public int getTotalAnuncios() {
        return getTotalAnunciosAtivos() + getTotalAnunciosConcretizados();
    }

    /**
     * Função para obter a percentagem de anúncios ativos
     * @return A percentagem obtida pelo total de anúncios ativos a dividir pelo total de anúncios a multiplicar por 100
     * Percentagem de anúncios ativos
     */
    public float getPercAnunciosAtivos() {
        int totalAnuncios = getTotalAnuncios() + getTotalAnunciosConcretizados();
        return Utils.calcularPercentagem(getTotalAnunciosAtivos(), totalAnuncios);
    }

    /**
     * Função para obter a percentagem de anúncios concretizados
     * A percentagem obtida pelo total de anúncios concretizados a dividir pelo total de anúncios a multiplicar por 100
     * @return Percentagem de anúncios concretizados
     */
    public float getPercAnunciosConcretizados() {
        int totalAnuncios = getTotalAnuncios();
        return Utils.calcularPercentagem(getTotalAnunciosConcretizados(), totalAnuncios);

    }

    /**
     * Função para obter o valor de receita de vendas
     * @return Valor das receitas das vendas
     */
    public double getReceitaVendas() {
        double receita = 0;
        for (AnuncioVenda anuncio : getAnunciosVendaConcretizados()) {
            receita += anuncio.getPreco();
        }
        return receita;
    }

    /**
     * Função para obter receita dos alugueres até à presente data <br>
     * A formula do cálculo é: <b>Diferença de meses entre a data de concretização e a atual  * Preço do aluguer</b>
     * @return Valor das receitas de aluguer
     */
    public double getReceitaAlugueres() {
        double receita = 0;
        for (AnuncioAluguer anuncio : getAnunciosAluguerConcretizados()) {
            Data d1 = anuncio.getDataConcretizacao();
            Data d2 = Data.getDataAtual();
            int mesesPassados = d2.getMes() - d1.getMes() + 12 * (d2.getAno() - d1.getAno()) - (d2.getDia() < d1.getDia() ? 1 : 0);
            if (mesesPassados > 0) {
                receita += mesesPassados * anuncio.getPreco();
            }
        }
        return receita;
    }

    /**
     * Função para obter a receita prevista de aluguer
     * A fórmula do cálculo é: <b>Duração * Preço do aluguer</b>
     * @return Receita prevista dos alugueres
     */
    public double getReceitaPrevistaAlugueres() {
        double receita = 0;
        for (AnuncioAluguer anuncio : getAnunciosAluguerConcretizados()) {
            receita += anuncio.getPreco() * anuncio.getDuracao();
        }
        return receita;
    }

    /**
     * Função para obter o total das receitas obtidas pelas vendas e alugueres
     * @return Total de receitas das vendas e alugueres
     */
    public double getTotalReceitas() {
        return getReceitaVendas() + getReceitaAlugueres();
    }

    /**
     * Função para obter o total das receitas <b>previstas</b> pelas vendas e alugueres
     * @return Total de receitas previstas das vendas e alugueres
     */
    public double getTotalReceitasPrevistas() {
        return getReceitaVendas() + getReceitaPrevistaAlugueres();
    }
}
