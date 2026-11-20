package funcoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva {

    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Reserva(Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
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

    // compara data das reservas para ver se o quarto está disponível

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


    // Printar informações da reserva em String
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Reserva do quarto " + quarto.getNumero() + " de "
                + dataEntrada.format(formato) + " até "
                + dataSaida.format(formato);
    }
}