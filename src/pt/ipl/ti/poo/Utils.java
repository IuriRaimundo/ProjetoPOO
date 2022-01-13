package pt.ipl.ti.poo;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Utils {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void fecharScanner() {
        SCANNER.close();
    }

    /*
    lerInteiro sem parametros simplesmente chama o lerInteiro com os limites
    dos números inteiros
    */
    public static int lerInteiro() {
        return lerInteiro(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int lerInteiro(int min, int max) {
        return lerInteiro(min, max, "Número inválido, repita a inserção");
    }
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
            SCANNER.nextLine(); // Limpar '\n'
        } while (!isValid);

        return n;
    }

    public static double lerDouble() {
        return lerDouble(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public static double lerDouble(double min, double max) {
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
                    System.out.print("Número inválido, repita a inserção: ");
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

}
