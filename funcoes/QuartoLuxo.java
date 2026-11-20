package funcoes;

/**
 * Representa um quarto de luxo (herança): é um Quarto que também
 * tem uma jacuzzi. Herda número, capacidade e diária da classe Quarto.
 */
public class QuartoLuxo extends Quarto {
    private int capacidadeJacuzzi;

    public QuartoLuxo(int numero, int capacidade, double valorDiaria, int capacidadeJacuzzi) {
        super(numero, capacidade, valorDiaria);

        this.capacidadeJacuzzi = capacidadeJacuzzi;
    }

    public int getCapacidadeJacuzzi() {
        return capacidadeJacuzzi;
    }

    @Override
    public String toString() {
        return "--------------------------------------\n" +
                "Número do quarto de LUXO: " + super.getNumero() +
                " | Capacidade da Jacuzzi: " + this.capacidadeJacuzzi +
                " | Capacidade: " + super.getCapacidade() + " pessoas" +
                " | Diária: R$ " + String.format("%.2f", super.getValorDiaria());
    }
}
