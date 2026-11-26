package funcoes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;

/**
 * Representa uma reserva: liga um quarto a um período (entrada e saída)
 * e ao CPF do cliente. O código de reserva de 6 dígitos é gerado
 * automaticamente. Também calcula o valor a pagar (diárias x valor da diária).
 */

public class Reserva implements Serializable {

    // versão fixa: permite mudar a classe sem perder o hotel.dat já salvo
    private static final long serialVersionUID = 4372755768620249516L;

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

    // quantidade de diárias = número de noites entre a entrada e a saída
    public long getQuantidadeDiarias() {
        return ChronoUnit.DAYS.between(dataEntrada, dataSaida);
    }

    // valor a pagar = quantidade de diárias x valor da diária do quarto
    public double calcularValorTotal() {
        return getQuantidadeDiarias() * quarto.getValorDiaria();
    }

    // texto com o cálculo, ex.: "3 diária(s) x R$ 150,00 = R$ 450,00"
    public String descreverValor() {
        return getQuantidadeDiarias() + " diária(s) x R$ "
                + String.format("%.2f", quarto.getValorDiaria())
                + " = R$ " + String.format("%.2f", calcularValorTotal());
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
                + "\nData de saída: " + dataSaida.format(formato)
                + "\nValor a pagar: " + descreverValor();
    }
}
