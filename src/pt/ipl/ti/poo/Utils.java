package pt.ipl.ti.poo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe de utilidades, contém uma instância do Scanner e métodos auxiliares ao resto do programa.
 */
public abstract class Utils {

    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Função para fechar o scanner
     */
    public static void fecharScanner() {
        SCANNER.close();
    }

    /*
     * lerInteiro sem limites
     * @return Inteiro introduzido pelo utilizador
    */
    public static int lerInteiro() {
        return lerInteiro(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * lerInteiro com limites
     * @param min Número mínimo a ser aceite
     * @param max Número máximo a ser aceite
     * @return Inteiro introduzido pelo utilizador
     */
    public static int lerInteiro(int min, int max) {
        return lerInteiro(min, max, "Número inválido, repita a inserção: ");
    }

    /**
     * Função para ler números inteiros através da consola. <br>
     * A solicitação de entrada ao utilizador repete-se até que seja inserido um valor válido.
     * @param min Número mínimo a ser aceite
     * @param max Número máximo a ser aceite
     * @param fallback Mensagem apresentada quando for introduzido um valor fora do intervalo de números aceite
     * @return Inteiro introduzido pelo utilizador
     */
    public static int lerInteiro(int min, int max, String fallback) {
        boolean isValid;
        int n = 0;
        if (max < min) {
            int aux = min;
            min = max;
            max = aux;
        }
        // Repetir até ser inserido um valor válido.
        do {
            try {
                n = SCANNER.nextInt();
                if (n < min || n > max) {
                    System.out.print(fallback);
                    isValid = false;
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                isValid = false;
                System.out.print("Insira um número inteiro: ");
            }
            SCANNER.nextLine(); // Limpar \n do buffer
        } while (!isValid);

        return n;
    }

    /**
     * lerDouble sem limites
     * @return Double introduzido pelo utilizador
     */
    public static double lerDouble() {
        return lerDouble(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    /**
     * lerDouble com limites
     * @param min Valor mínimo aceite
     * @param max Valor máximo aceite
     * @return Double introduzido pelo utilizador
     */
    public static double lerDouble(double min, double max) {
        return lerDouble(Double.MIN_VALUE, Double.MAX_VALUE, "Número inválido, repita a inserção");
    }

    /**
     * Função para ler um Double através da consola <br>
     * A solicitação de entrada ao utilizador repete-se até que seja inserido um valor válido.
     * @param min Valor mínimo aceite
     * @param max Valor máximo aceite
     * @param fallback Mensagem apresentada ao utilizador quando um valor fora do limite é inserido.
     * @return Double introduzido pelo utilizador
     */
    public static double lerDouble(double min, double max, String fallback) {
        boolean isValid;
        double n = 0;
        if (max < min) {
            double aux = min;
            min = max;
            max = aux;
        }
        // Repetir até ser inserido um valor válido.
        do {
            try {
                n = SCANNER.nextDouble();
                if (n < min || n > max) {
                    System.out.print(fallback);
                    isValid = false;
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                isValid = false;
                System.out.print("Insira um número numérico: ");
            }
            SCANNER.nextLine(); // Limpar '\n'
        } while (!isValid);

        return n;
    }

    /**
     * Função para ler uma string através da consola.
     * A solicitação de entrada ao utilizador repete-se até que seja inserido uma string não vazia.
     * @return String inserida pelo utilizador
     */
    public static String lerString() {
        String string;
        do {
            string = SCANNER.nextLine();
            if (string.isBlank()) {
                System.out.print("Deve inserir um valor, repita a inserção: ");
            }
        } while (string.isBlank());

        return string.trim();
    }

    /**
     * Calcula a percentagem da razão de dois números
     * Devolve 0 caso um dos números inseridos seja menor ou igual a 0.
     * @param numerador Numerador da fração
     * @param denominador Denominador da fração
     * @return Valor da percentagem
     */
    public static float calcularPercentagem(double numerador, double denominador) {
        if (denominador <= 0 || numerador <= 0) return 0;
        return (float) Math.round((numerador / denominador * 1000)) / 10;
    }

    /**
     * Esta função cria uma hash a partir de uma string com o algorítmo SHA-256. <br>
     * É usada para fazer a hash das palavras-passe das imobiliárias.
     * @param string String a ser transformada.
     * @return hash
     */
    public static byte[] hashString(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(string.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao criar hash.");
            return null;
        }
    }

    /**
     * Esta função compara duas hash, é utilizada no programa para iniciar sessão em imobiliárias.
     * @param p1 hash a comparar
     * @param p2 hash a comparar
     * @return Verdadeiro se as hash forem iguais, Falso se forem diferentes.
     */
    public static boolean compararHash(byte[] p1, byte[] p2) {
        return Objects.deepEquals(p1, p2);
    }

    /**
     * Esta função criar o checksum / hash de um ficheiro.
     * Código obtido em https://howtodoinjava.com/java/java-security/sha-md5-file-checksum-hash/
     * @param digest Digest com o algoritmo pretendido.
     * @param file Ficheiro para criar o checksum
     * @return Checksum / hash de um ficheiro
     * @throws IOException
     */
    public static byte[] getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        // Obter stream para ler o conteúdo do ficheiro
        FileInputStream fis = new FileInputStream(file);

        // Buffer de bytes para ler o ficheiro em bocados
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        // Atualizar a mensagem do digest com os dados do ficheiro.
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        // Fechar a stream
        fis.close();

        // Obter hash
        byte[] bytes = digest.digest();

        return bytes;
    }
}
