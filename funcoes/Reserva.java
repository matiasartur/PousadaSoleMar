package funcoes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

/**
 * Representa uma reserva: liga um quarto a um período (entrada e saída)
 * e ao CPF do cliente. O código de reserva de 6 dígitos é gerado
 * automaticamente.
 */

public class Reserva implements Serializable {

    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String cpfCliente;
    private String codigoReserva;

    public Reserva(Quarto quarto, LocalDate dataEntrada,
            LocalDate dataSaida, String cpfCliente) {

        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.cpfCliente = cpfCliente;

        // Gera um código aleatório de 6 dígitos
        Random random = new Random();
        this.codigoReserva = String.format("%06d",
                random.nextInt(1000000));
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Reserva outra = (Reserva) obj;

        return Objects.equals(quarto, outra.quarto)
                && Objects.equals(dataEntrada, outra.dataEntrada)
                && Objects.equals(dataSaida, outra.dataSaida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quarto, dataEntrada, dataSaida);
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Código da reserva: " + codigoReserva
                + "\nCPF do cliente: " + cpfCliente
                + "\nData de entrada: " + dataEntrada.format(formato)
                + "\nData de saída: " + dataSaida.format(formato);
    }
}
