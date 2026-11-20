package arquivo;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import funcoes.Hotel;

/**
 * Cuida dos arquivos: salva e carrega o hotel inteiro em hotel.dat
 * (para recuperar os dados ao reabrir o programa) e grava o registro
 * de cada reserva em dados.txt.
 */

public class Arquivo {

    // salva os dados da reserva no arquivo txt
    public static void salvar(String texto) {

        try {
            FileWriter arquivo = new FileWriter("dados.txt", true);
            BufferedWriter escritor = new BufferedWriter(arquivo);

            escritor.write(texto);
            escritor.newLine();
            escritor.newLine();

            escritor.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // MÉTODO PARA LER
    public static void ler() {

        try {
            FileReader arquivo = new FileReader("dados.txt");
            BufferedReader leitor = new BufferedReader(arquivo);

            String linha;

            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }

            leitor.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler: " + e.getMessage());
        }
    }

    // SALVA O HOTEL INTEIRO (quartos e reservas) NO ARQUIVO hotel.dat
    public static void salvarHotel(Hotel hotel) {

        try (ObjectOutputStream saida = new ObjectOutputStream(
                new FileOutputStream("hotel.dat"))) {

            saida.writeObject(hotel);

        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // CARREGA O HOTEL DO ARQUIVO. Se o arquivo não existir (primeira vez), cria um
    // hotel novo
    public static Hotel carregarHotel(String nome) {

        File arquivo = new File("hotel.dat");

        if (!arquivo.exists()) {
            return new Hotel(nome);
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream(arquivo))) {

            return (Hotel) entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
            return new Hotel(nome);
        }
    }
}
