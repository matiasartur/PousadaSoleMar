package funcoes;

import java.util.Objects;

public class Quarto {
    // cadastro de quarto
    // ===================================================================

    private int numero;
    private int capacidade;
    private double valorDiaria;
    private Reserva reserva;

    public Quarto(int numero, int capacidade, double valorDiaria) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.valorDiaria = valorDiaria;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public Reserva getReserva() {
        return reserva;
    }

    // listar quartos
    // ===================================================================

    private boolean disponivel = true;

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "--------------------------------------\n" +
                "Número do quarto: " + numero +
                " | Capacidade: " + capacidade + " pessoas" +
                " | Diária: R$ " + String.format("%.2f", valorDiaria);
    }

    // comparar quartos pelo número
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Quarto outro = (Quarto) obj;

        return numero == outro.numero;
    }

    // gerar hashCode baseado no número do quarto
    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
