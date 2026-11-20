package principal;

import funcoes.Leitura;
import funcoes.Hotel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        LocalDateTime hoje = LocalDateTime.now();

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println(hoje.format(formatoData));

        Hotel hotel = new Hotel("Pousada do Sol e Mar");
        int opcao;

        do {
            System.out.println();
            System.out.println("====================");
            System.out.println("|  MENU PRINCIPAL  |");
            System.out.println("====================");
            System.out.println("\n1 - Cadastrar quarto");
            System.out.println("2 - Fazer reserva");
            System.out.println("3 - Cancelar reserva");
            System.out.println("4 - Listar quartos");
            System.out.println("5 - Consultar quarto");
            System.out.println("0 - Sair");

            opcao = Leitura.lerInteiro("\nEscolha uma opção: ", 0);

            switch (opcao) {
                case 1:
                    boolean luxo = Leitura.lerBoolean("Quarto de luxo? (S/N): ");
                    if (luxo) {
                        hotel.cadastrarQuartoLuxo();
                    } else {
                        hotel.cadastrarQuarto();
                    }
                    break;
                case 2:
                    hotel.fazerReserva();
                    break;
                case 3:
                    hotel.cancelarReserva();
                    break;
                case 4:
                    hotel.listarQuartos();
                    break;
                case 5:
                    hotel.consultarQuartos();
                    break;
                case 0:
                    System.out.println("\nSaindo do programa...\n");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }

        } while (opcao != 0);

        Leitura.fechar();
    }
}
