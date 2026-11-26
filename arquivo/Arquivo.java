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
import funcoes.Equipe;
import funcoes.Hotel;

/**
 * Cuida dos arquivos: salva e carrega o hotel inteiro em hotel.dat
 * e os funcionários em funcionarios.dat (para recuperar os dados ao
 * reabrir o programa). Também grava arquivos de texto para leitura:
 * reservas.txt (registro de reservas e cancelamentos), quartos.txt
 * (quartos cadastrados) e funcionarios.txt (logins e senhas).
 */

public class Arquivo {

    // salva os dados da reserva no arquivo txt
    public static void salvar(String texto) {

        try {
            FileWriter arquivo = new FileWriter("reservas.txt", true);
            BufferedWriter escritor = new BufferedWriter(arquivo);

            escritor.write(texto);
            escritor.newLine();
            escritor.newLine();

            escritor.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // REESCREVE O ARQUIVO TXT INTEIRO COM O TEXTO RECEBIDO (lista sempre atual)
    public static void sobrescrever(String nomeArquivo, String texto) {

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {

            escritor.write(texto);

        } catch (IOException e) {
            System.out.println("Erro ao salvar " + nomeArquivo + ": " + e.getMessage());
        }
    }

    // MÉTODO PARA LER
    public static void ler() {

        try {
            FileReader arquivo = new FileReader("reservas.txt");
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

        // também grava a lista de quartos em texto, para leitura
        sobrescrever("quartos.txt", hotel.gerarRelatorioQuartos());
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

    // SALVA OS FUNCIONÁRIOS NO ARQUIVO funcionarios.dat
    public static void salvarEquipe(Equipe equipe) {

        try (ObjectOutputStream saida = new ObjectOutputStream(
                new FileOutputStream("funcionarios.dat"))) {

            saida.writeObject(equipe);

        } catch (IOException e) {
            System.out.println("Erro ao salvar os funcionários: " + e.getMessage());
        }

        // também grava os logins e senhas em texto, para leitura
        sobrescrever("funcionarios.txt", equipe.gerarRelatorioFuncionarios());
    }

    // CARREGA OS FUNCIONÁRIOS DO ARQUIVO. Se o arquivo não existir, cria uma
    // equipe só com o ADMIN
    public static Equipe carregarEquipe() {

        File arquivo = new File("funcionarios.dat");

        if (!arquivo.exists()) {
            return new Equipe();
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream(arquivo))) {

            return (Equipe) entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os funcionários: " + e.getMessage());
            return new Equipe();
        }
    }

    /*
     * equals e toString exigidos pelo trabalho (requisito 5). Esta classe só tem
     * métodos estáticos e não guarda dados próprios, então dois objetos Arquivo
     * são sempre iguais entre si.
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return Arquivo.class.hashCode();
    }

    @Override
    public String toString() {
        return "Arquivo: salva e carrega hotel.dat e funcionarios.dat, "
                + "e grava reservas.txt, quartos.txt e funcionarios.txt";
    }
}
