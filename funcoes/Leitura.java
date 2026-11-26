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

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma linha do teclado. Todos os outros métodos usam este.
     * Se a entrada for encerrada (Ctrl+Z no Windows, Ctrl+D no Linux/Mac),
     * não há mais o que ler: em vez de o programa quebrar com
     * NoSuchElementException, ele avisa e encerra normalmente.
     * Os dados já estão salvos, porque o sistema grava após cada alteração.
     */
    private static String lerLinha() {

        if (!scanner.hasNextLine()) {
            System.out.println("\n\nEntrada encerrada. Saindo do programa...");
            System.exit(0);
        }

        return scanner.nextLine();
    }

    // LÊ UM TEXTO QUALQUER (pode ficar vazio)
    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return lerLinha();
    }

    // LÊ UM TEXTO QUE NÃO PODE FICAR VAZIO (login, senha)
    public static String lerTextoObrigatorio(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String texto = lerLinha().trim();

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
                return LocalDate.parse(lerLinha().trim(), formato);

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/aaaa (ex: 25/12/2030).");
            }
        }
    }

    // FORMATAÇÃO CPF
    public static String lerCPF(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String cpf = lerLinha().replaceAll("[^0-9]", "");

            if (cpf.matches("\\d{11}")) {
                return cpf;
            }

            System.out.println("CPF inválido! Digite os 11 números do CPF.");
        }
    }

    // CONFERIR NUMERO DIGITADO PELO USUARIO
    public static int lerInteiro(String mensagem, int minimo) {

        while (true) {
            System.out.print(mensagem);

            try {
                int numero = Integer.parseInt(lerLinha().trim());

                if (numero >= minimo) {
                    return numero;
                }

                System.out.println("Número abaixo do mínimo permitido!");

            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }

    /**
     * CONFERIR VALOR DO QUARTO. Aceita só valores em dinheiro como 150, 150,5
     * ou 150,50: até 7 dígitos antes da vírgula (máximo R$ 9.999.999,99) e até
     * 2 casas decimais. O Double.parseDouble sozinho aceitaria textos como
     * "Infinity", "NaN", "1e5", "10d" ou números com centenas de dígitos,
     * por isso o formato é conferido antes da conversão.
     */
    public static double lerDouble(String mensagem, double minimo) {

        while (true) {
            System.out.print(mensagem);

            String texto = lerLinha().trim();

            if (!texto.matches("\\d{1,7}([.,]\\d{1,2})?")) {
                System.out.println("Digite um valor válido! (ex: 150 ou 150,50 - "
                        + "até 7 dígitos e 2 casas decimais)");
                continue;
            }

            double numero = Double.parseDouble(texto.replace(",", "."));

            if (numero < minimo) {
                System.out.println("Valor abaixo do mínimo permitido!");
            } else {
                return numero;
            }
        }
    }

    // CONFERIR ENTRADA QUARTO DE LUXO (S == TRUE E N == FALSE)
    public static boolean lerBoolean(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String resposta = lerLinha().trim().toUpperCase();

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
