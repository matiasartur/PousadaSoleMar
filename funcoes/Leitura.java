package funcoes;

import java.time.format.ResolverStyle;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Lê os dados digitados no teclado e testa cada entrada.
 * Se o usuário digitar algo inválido, a pergunta é repetida
 * e o programa não quebra.
 */

public class Leitura {

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        String i = scanner.nextLine();
        return i;
    }

    // LÊ UM TEXTO QUE NÃO PODE FICAR VAZIO (login, senha)
    public static String lerTextoObrigatorio(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Este campo não pode ficar vazio!");
        }
    }

    // FORMATAÇÃO DATA

    public static LocalDate lerData(String mensagem) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        while (true) {
            System.out.print(mensagem);

            try {
                return LocalDate.parse(scanner.nextLine(), formato);

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/aaaa (ex: 25/12/2030).");
            }
        }
    }

    // FORMATAÇÃO CPF
    public static String lerCPF(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String cpf = scanner.nextLine().replaceAll("[^0-9]", "");

            if (cpf.matches("\\d{11}")) {
                return cpf;
            }

            System.out.println("CPF inválido! Digite os 11 números do CPF.");
        }
    }

    // CONFERIR NUMERO DIGITADO PELO USUARIO

    private static Scanner scanner = new Scanner(System.in);

    public static int lerInteiro(String mensagem, int minimo) {

        while (true) {
            System.out.print(mensagem);

            try {
                int numero = Integer.parseInt(scanner.nextLine());

                if (numero >= minimo) {
                    return numero;
                }

                System.out.println("Número abaixo do mínimo permitido!");

            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }

    // CONFERIR VALOR DO QUARTO
    public static double lerDouble(String mensagem, double minimo) {

        while (true) {
            System.out.print(mensagem);

            try {
                double numero = Double.parseDouble(
                        scanner.nextLine().replace(",", "."));

                if (numero >= minimo) {
                    return numero;
                }

                System.out.println("Valor abaixo do mínimo permitido!");

            } catch (NumberFormatException e) {
                System.out.println("Digite um valor decimal válido!");
            }
        }
    }

    // CONFERIR ENTRADA QUARTO DE LUXO (S == TRUE E N == FALSE)
    public static boolean lerBoolean(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String resposta = scanner.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                return true;
            } else if (resposta.equals("N")) {
                return false;
            } else {
                System.out.println("Resposta inválida! Digite S para Sim ou N para Não.");
            }
        }
    }

    public static void fechar() {
        scanner.close();
    }

    /*
     * equals e toString exigidos pelo trabalho (requisito 5). Esta classe só tem
     * métodos estáticos e não guarda dados próprios, então dois objetos Leitura
     * são sempre iguais entre si.
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return Leitura.class.hashCode();
    }

    @Override
    public String toString() {
        return "Leitura: lê e valida os dados digitados no teclado";
    }
}
