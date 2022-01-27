package pt.ipl.ti.poo;

import pt.ipl.ti.poo.imobiliaria.Imobiliaria;
import pt.ipl.ti.poo.imobiliaria.ImobiliariaUtils;
import pt.ipl.ti.poo.imobiliaria.Localizacao;
import pt.ipl.ti.poo.menu.Menu;

import java.io.File;
import java.util.LinkedList;


public class Main {

    /**
     * Imobiliaria a ser utilizada no momento.
     */
    Imobiliaria imobiliariaAtiva = null;

    /**
     * Lista das imobiliárias no programa.
     */
    LinkedList<Imobiliaria> imobiliarias = new LinkedList<>();

    public Main() {

        /*
            O programa irá executar num ciclo, até que o utilizador escolha a opção de sair.
            Quando o utilizador iniciar sessão em uma imobiliária, ou adicionar uma nova, é
            apresentado o menu para efetuar operações à imobiliária selecionada.
         */
        int escolha, opcaoSairPrograma;

        // Lista de opções, para criar o menu.
        LinkedList<String> opcoes = new LinkedList<>();

        do {
            System.out.println("-------------------");
            System.out.println("GESTOR IMOBILIÁRIAS");
            System.out.println("-------------------");

            opcoes.clear();

            // Adicionar o nome das imobiliárias às opções
            for (Imobiliaria imobiliaria : imobiliarias) {
                opcoes.add(imobiliaria.getDescricao());
            }

            //Adicionar opções adicionais, o número de cada opcão corresponde exatamente ao tamanho da lista após a sua inserção.
            opcoes.add("Adicionar Imobiliária");
            int opcaoCriarImob = opcoes.size();

            /*
            Se não exisitrem diferenças entre os dados atuais do programa e os dados armazenados um carregamento dos dados
            é inútil então não é disponibilizada essa opção.
             */
            boolean permitirCarregarDados = ImobiliariaUtils.verificarAlteracoesDados(imobiliarias);
            int opcaoCarregarDados = -1;
            if (permitirCarregarDados) {
                opcoes.add("Carregar dados");
                opcaoCarregarDados = opcoes.size();
            }

            opcoes.add("Sair do programa");
            opcaoSairPrograma = opcoes.size();

            // Criar um menu com as opções e recolher a escolha do utilizador.
            Menu menu = new Menu(opcoes);
            menu.apresentar();
            escolha = menu.obterEscolha();


            // Executar operação escolhida pelo utilizador
            if (escolha == opcaoCriarImob) {

                // Criar uma nova imobiliária e adicionar à lista de imobiliárias caso não seja repetida.
                Imobiliaria novaImobiliaria = criarImobiliaria();
                if (adicionarImobiliaria(novaImobiliaria)) {
                    System.out.println("\nImobiliária adicionada com sucesso!\n");
                    imobiliariaAtiva = novaImobiliaria;
                    menuImobiliaria();
                } else {
                    System.out.println("\nImobiliária não adicionada. Não podem ser adicionadas imobiliárias com nomes repetidos.\n");
                }

            } else if (permitirCarregarDados && escolha == opcaoCarregarDados) {
                // Solicitar ao utilizador se pretende continuar visto que o carregamento de dados implica a perda das alterações não guardada.
                boolean carregar = Utils.escolhaBinaria("Esta operação irá sobre-escrever todos os dados não gravados, pretende continuar?");
                if (carregar) {
                    imobiliarias = ImobiliariaUtils.lerFicheiroImobiliaria();
                }
            } else if (escolha == opcaoSairPrograma) {

                // Fechar o programa
                fecharPrograma();

            } else {
                /*
                Tentar entrar em uma das imobiliárias, é solicitada uma palavra-passe e caso a hash da mesma corresponda
                à hash armazenada na imobiliária é atualizada a imobiliária ativa, se não corresponder o utilizador é
                notificado e o programa regressa ao menu.
                 */
                Imobiliaria imobiliariaEscolhida = imobiliarias.get(escolha - 1);
                boolean iniciadaSessao = ImobiliariaUtils.iniciarSessao(imobiliariaEscolhida);
                if (iniciadaSessao) {
                    imobiliariaAtiva = imobiliariaEscolhida;
                    menuImobiliaria();
                }
            }
        } while (escolha != opcaoSairPrograma);
    }

    public static void main(String[] args) {
        new Main();
    }

    /**
     * Esta função adiciona uma imobiliária à lista de imobiliárias.
     *
     * @param imobiliaria Imobiliária a ser adicionada.
     */
    private boolean adicionarImobiliaria(Imobiliaria imobiliaria) {
        if (imobiliaria == null || imobiliarias.contains(imobiliaria)) {
            return false;
        } else {
            imobiliarias.add(imobiliaria);
            return true;
        }
    }

    /**
     * Esta função apresenta o menu para efetuar operações sobre a imobiliária ativa. <br>
     * Termina quando o utilizador seleciona a opção de terminar sessão.
     */
    private void menuImobiliaria() {
        if (imobiliariaAtiva == null) return;

        final String[] opcoesMenu = {
                "Listar anúncios ativos", "Listar anúncios concretizados", "Inserir anúncio",
                "Registar anúncio como concretizado", "Estatísticas", "Gravar dados", "Terminar sessão",
                "Apagar imobiliária"
        };

        Menu menu = new Menu(opcoesMenu);

        int escolha;

        do {
            System.out.println("-----------------------------");
            System.out.println("GESTOR IMOBILIÁRIA - " + imobiliariaAtiva.getDescricao());
            System.out.println("-----------------------------");

            menu.apresentar();
            escolha = menu.obterEscolha();

            switch (escolha) {
                case 1 -> // Listar anúncios abertos
                        ImobiliariaUtils.listarAnuncios(imobiliariaAtiva.getAnunciosAtivos());
                case 2 -> // Listar anúncios concretizados
                        ImobiliariaUtils.listarAnuncios(imobiliariaAtiva.getAnunciosConcretizados());
                case 3 -> // Criar novo anúncio
                        ImobiliariaUtils.criarAnuncio(imobiliariaAtiva);
                case 4 -> // Registar anúncio como concretizado
                        ImobiliariaUtils.concretizarAnuncio(imobiliariaAtiva);
                case 5 -> // Estatísticas
                        ImobiliariaUtils.imprimirRelatorioImobiliaria(imobiliariaAtiva);
                case 6 -> // Gravar dados
                        ImobiliariaUtils.gravarDadosImobiliarias(imobiliarias);
                case 7 -> { // Terminar sessão
                    /*
                    Verificar se existem alterações entre o estado atual das imobiliárias e os dados das imobiliárias gravados.
                    Se existir diferença, perguntar ao utilizador se ele pretende guardar os dados de forma a não perder as últimas alterações.

                    Caso o utilizador não pretende guardar os dados, as alterações são descartadas e o programa volta aos dados armazenados
                    na última gravação.
                     */
                    if (ImobiliariaUtils.verificarAlteracoesDados(imobiliarias)) {
                        // Perguntar ao utilizador se pretende guardar os dados
                        boolean gravar = Utils.escolhaBinaria("Existem dados que não foram gravados. Pretende guardar os dados ?");
                        if (gravar) {
                            ImobiliariaUtils.gravarDadosImobiliarias(imobiliarias);
                        } else {
                            imobiliarias = ImobiliariaUtils.lerFicheiroImobiliaria();
                        }
                    }
                    imobiliariaAtiva = null;
                }
                case 8 -> {
                    // Apagar Imobiliária
                    boolean apagou = ImobiliariaUtils.apagarImobiliaria(imobiliarias, imobiliariaAtiva);
                    if (apagou) {
                        // Se foi apagada a imobiliária, grava-se os dados para guardar as alterações.
                        ImobiliariaUtils.gravarDadosImobiliarias(imobiliarias);
                        imobiliariaAtiva = null;
                    }
                }
            }
        } while (escolha != 7 && escolha != 8);
    }

    /**
     * Função para criar imobiliárias, solicita ao utilizador um nome e uma localização <br>
     * e devolve uma imobiliária com esses dados.
     */
    private Imobiliaria criarImobiliaria() {
        System.out.println("\nNOVA IMOBILIÁRIA\n");
        System.out.print("Nome: ");
        String nome = Utils.lerString();
        Localizacao localizacao = ImobiliariaUtils.criarLocalizacao();
        String palavraPasse = ImobiliariaUtils.criarPalavraPasse();
        return new Imobiliaria(nome, localizacao, palavraPasse);
    }

    /**
     * Esta função prepara o programa para ser fechado.
     * O ficheiro temporário é apenas eliminado ao fechar o programa para evitar repetida operações de criar
     * e eliminar ficheiros durante o decorrer do programa.
     */
    private void fecharPrograma() {
        System.out.println("\nA sair do programa...");

        // Eliminar ficheiro temporário caso exista
        File tmp = new File(ImobiliariaUtils.NOME_FICH_TMP);
        if (tmp.exists()) {
           tmp.delete();
        }

        Utils.fecharScanner();
    }
}