package funcoes;

import java.time.format.DateTimeFormatter;
import arquivo.Arquivo;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Hotel {

    private String nome;
    private List<Quarto> quartos;
    private List<Reserva> reservas;

    public Hotel(String nome) {
        this.nome = nome;
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // Cadastro de Quarto
    // ===================================================================

    public void cadastrarQuarto() {

        System.out.println("\n--- Cadastrar Quarto ---");
        int numero = Leitura.lerInteiro("Número do quarto: ", 1);
        int capacidade = Leitura.lerInteiro("Capacidade do quarto: ", 1);
        double valorDiaria = Leitura.lerDouble("Valor da diária: R$ ", 0.01);

        Quarto novoQuarto = new Quarto(numero, capacidade, valorDiaria);

        if (quartos.contains(novoQuarto)) {
            System.out.println("\nQuarto já cadastrado!");
            return;
        }

        quartos.add(novoQuarto);

        System.out.println("\nQuarto cadastrado com sucesso!");
        System.out.println(novoQuarto);
    }

    // Listar Quartos
    // ====================================================================

    public void listarQuartos() {

        System.out.println("\n--- Lista de Quartos ---");

        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado.");
            return;
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < quartos.size(); i++) {

            Quarto quarto = quartos.get(i);

            System.out.println("\n" + quarto);

            boolean encontrouReserva = false;

            for (int j = 0; j < reservas.size(); j++) {

                Reserva reserva = reservas.get(j);

                if (reserva.getQuarto().equals(quarto)) {

                    encontrouReserva = true;

                    System.out.println("  Reservado de " + reserva.getDataEntrada().format(formato) + " até "
                            + reserva.getDataSaida().format(formato));
                }
            }

            if (!encontrouReserva) {
                System.out.println("  Sem reservas cadastradas.");
            }
        }
    }

    // Fazer Reserva
    // ====================================================================

    public void fazerReserva() {

        System.out.println("\n--- Fazer Reserva ---");

        String cpfCliente = Leitura.lerCPF("CPF do cliente: ");
        int numero = Leitura.lerInteiro("Número do quarto: ", 1);

        Quarto quartoEncontrado = null;

        for (int i = 0; i < quartos.size(); i++) {

            if (quartos.get(i).getNumero() == numero) {
                quartoEncontrado = quartos.get(i);
                break;
            }
        }

        if (quartoEncontrado == null) {
            System.out.println("Quarto não encontrado!");
            return;
        }

        LocalDate entrada = Leitura.lerData("Data de entrada (dd/MM/yyyy): ");
        LocalDate saida = Leitura.lerData("Data de saída (dd/MM/yyyy): ");

        if (entrada.isBefore(LocalDate.now())) {
            System.out.println("Não é possível fazer reservas no passado!");
            return;
        }

        if (!entrada.isBefore(saida)) {
            System.out.println("A data de saída deve ser posterior à entrada!");
            return;
        }

        for (int i = 0; i < reservas.size(); i++) {

            Reserva reservaExistente = reservas.get(i);

            if (reservaExistente.getQuarto().equals(quartoEncontrado)) {
                boolean conflito = entrada.isBefore(reservaExistente.getDataSaida())
                        && saida.isAfter(reservaExistente.getDataEntrada());

                if (conflito) {
                    System.out.println("Quarto já reservado nesse período!");
                    return;
                }
            }
        }
        Reserva novaReserva = new Reserva(quartoEncontrado, entrada, saida, cpfCliente);

        reservas.add(novaReserva);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String dados = "========================================\n" +
                "       POUSADA DO SOL E MAR\n" +
                "          REGISTRO DE RESERVA\n" +
                "========================================\n\n" +
                "CPF do cliente: " + cpfCliente + "\n" +
                "Código da reserva: " + novaReserva.getCodigoReserva() + "\n" +
                "Quarto: " + quartoEncontrado.getNumero() + "\n" +
                "Data de entrada: " + entrada.format(formato) + "\n" +
                "Data de saída:   " + saida.format(formato) + "\n" +
                "Reserva realizada em: " +
                LocalDateTime.now().format(formatoDataHora) + "\n" +
                "----------------------------------------";

        Arquivo.salvar(dados);

        System.out.println("\nReserva realizada com sucesso!");
        System.out.println("==================================");
        System.out.println("CÓDIGO DA RESERVA: " + novaReserva.getCodigoReserva());
        System.out.println("==================================");
    }

    // =========================================================================

    public void consultarQuarto() {
    }

    public void cancelarReserva() {
    }

    // uso obrigatório do equals e toString
    // comparar hotéis pelo nome -- desnecessário

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Hotel outro = (Hotel) obj;
        return Objects.equals(nome, outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

      @Override
    public String toString() {
        return "Hotel " + nome + " | Quartos: " + quartos.size()
                + " | Reservas: " + reservas.size();
    }


}