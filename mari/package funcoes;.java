package funcoes;

public class Quarto{
    //declaracao de atributos
    private int numero;
    private double valor;
    private int capacidade;
    private boolean ocupado;

    //construtores ~mariana
    public Quarto(){
        this.numero = 0;
        this.valor = 0.0;
        this.capacidade = 0;
        this.ocupado = false;

    }

    public Quarto(int numero, double valor, int capacidade){
        this.numero = numero;
        this.valor = valor;
        this.capacidade = capacidade;
        this.ocupado = false;  // todo quarto novo começa livre ~mariana
    }
    //GETTERS = ler valor do atributo ~mariana
    public int getnumero(){
            return this.numero;
        }

    public double getvalor(){
            return this.valor;
        }

    public int getcapacidade(){
            return this.capacidade;
        }

    public boolean isOcupado(){
         return this.ocupado;
    }

    // SETTERS = alterar valor do atributo (sempre void!) ~mariana
    public void setnumero(int novo){
        this.numero = novo;
    }

    public void setvalor(double novo){
        this.valor = novo;
    }

    public void setcapacidade(int novo){
        this.capacidade = novo;
    }

    public void setOcupado(boolean novo){
        this.ocupado = novo;
    }
    
    // toString = mostra os conteúdos dos atributos ~mariana
    @Override
    public String toString() {
        String status = this.ocupado ? "Ocupado" : "Livre";
        return "Nº do quarto: " + this.numero + "\n" +
               "Valor do quarto (R$): " + this.valor + "\n" +
               "Capacidade: " + this.capacidade + " pessoa(s)\n" +
               "Status: " + status + "\n";
    }

    // equals = compara se dois quartos têm o mesmo número (exigência do projeto) ~mariana

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Quarto)) return false;
        Quarto outro = (Quarto) obj;
        return this.numero == outro.numero;
    }
}