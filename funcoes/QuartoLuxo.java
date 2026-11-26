package funcoes;

/**
 * Representa um quarto de luxo (herança): é um Quarto que também
 * tem uma jacuzzi. Herda número, capacidade e diária da classe Quarto.
 */
public class QuartoLuxo extends Quarto {

    // versão fixa: permite mudar a classe sem perder o hotel.dat já salvo
    private static final long serialVersionUID = 7898645845265156009L;

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

    /**
     * compara pelo número do quarto, igual à classe Quarto. Não compara a jacuzzi
     * de propósito: um quarto comum e um de luxo com o mesmo número são o mesmo
     * quarto, e é isso que impede cadastrar dois quartos com o mesmo número
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
