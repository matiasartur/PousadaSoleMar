package arquivo;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Arquivo {

    // MÉTODO PARA SALVAR
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
}
