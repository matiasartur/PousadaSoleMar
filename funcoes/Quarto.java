package funcoes;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um quarto comum do hotel
 * Guarda o número, a capacidade de pessoas e o valor da diária
 * A classe QuartoLuxo herda dessa
 */

public class Quarto implements Serializable {

    private int numero;
    private int capacidade;
    private double valorDiaria;

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

        if (!(obj instanceof Quarto)) {
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
