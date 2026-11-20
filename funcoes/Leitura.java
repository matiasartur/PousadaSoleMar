package funcoes;

import java.time.format.ResolverStyle;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Leitura {

//                          RESERVA

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

//                  CONFERIR NUMERO DIGITADO PELO USUARIO

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


    //                 CONFERIR VALOR DO QUARTO
    public static double lerDouble(String mensagem, double minimo) {

        while (true) {
            System.out.print(mensagem);

            try {
                double numero = Double.parseDouble(
                    scanner.nextLine().replace(",", ".")
                );

                if (numero >= minimo) {
                    return numero;
                }

                System.out.println("Valor abaixo do mínimo permitido!");

            } catch (NumberFormatException e) {
                System.out.println("Digite um valor decimal válido!");
            }
        }
    }

    public static void fechar() {
        scanner.close();
    }
}
