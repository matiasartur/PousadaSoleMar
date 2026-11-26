package principal;

import arquivo.Arquivo;
import funcoes.Equipe;
import funcoes.Funcionario;
import funcoes.Leitura;
import funcoes.Hotel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe principal: pergunta se o usuário é funcionário ou cliente,
 * mostra o menu correspondente e chama a função escolhida.
 */

public class Main {

    public static void main(String[] args) {

        LocalDateTime hoje = LocalDateTime.now();

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println(hoje.format(formatoData));

        Hotel hotel = Arquivo.carregarHotel("Pousada do Sol e Mar");
        Equipe equipe = Arquivo.carregarEquipe();

        // grava os arquivos logo ao abrir, para quartos.txt e funcionarios.txt
        // sempre existirem e estarem em dia com os dados carregados
        Arquivo.salvarHotel(hotel);
        Arquivo.salvarEquipe(equipe);

        boolean executando = true;

        while (executando) {

            System.out.println();
            System.out.println("=========================");
            System.out.println("|  POUSADA DO SOL E MAR  |");
            System.out.println("=========================");
            System.out.println("\nVocê é:");
            System.out.println("1 - Funcionário");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");

            int opcao = Leitura.lerInteiro("\nEscolha uma opção: ", 0);

            switch (opcao) {
                case 1:
                    Funcionario logado = equipe.fazerLogin();
                    if (logado != null) {
                        executando = menuFuncionario(hotel, equipe, logado);
                    }
                    break;
                case 2:
                    executando = menuCliente(hotel);
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        }

        System.out.println("\nSaindo do programa...\n");
        Leitura.fechar();
    }

    /**
     * Menu da área de funcionários. Retorna false se o usuário escolheu sair
     * do programa e true se escolheu voltar para a escolha de perfil.
     */
    private static boolean menuFuncionario(Hotel hotel, Equipe equipe, Funcionario logado) {

        while (true) {

            System.out.println();
            System.out.println("==========================");
            System.out.println("|  MENU DO FUNCIONÁRIO  |");
            System.out.println("==========================");
            System.out.println("\n1 - Cadastrar quarto");
            System.out.println("2 - Listar quartos");
            System.out.println("3 - Consultar quarto");
            System.out.println("4 - Cadastrar funcionário");
            System.out.println("5 - Excluir funcionário");
            System.out.println("9 - Voltar");
            System.out.println("0 - Sair");

            int opcao = Leitura.lerInteiro("\nEscolha uma opção: ", 0);

            switch (opcao) {
                case 1:
                    boolean luxo = Leitura.lerBoolean("Quarto de luxo? (S/N): ");
                    if (luxo) {
                        hotel.cadastrarQuartoLuxo();
                    } else {
                        hotel.cadastrarQuarto();
                    }
                    Arquivo.salvarHotel(hotel);
                    break;
                case 2:
                    hotel.listarQuartos(true);
                    break;
                case 3:
                    hotel.consultarQuartos();
                    break;
                case 4:
                    equipe.cadastrarFuncionario();
                    Arquivo.salvarEquipe(equipe);
                    break;
                case 5:
                    equipe.excluirFuncionario(logado);
                    Arquivo.salvarEquipe(equipe);
                    break;
                case 9:
                    return true;
                case 0:
                    return false;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        }
    }

    /**
     * Menu da área de clientes. Retorna false se o usuário escolheu sair
     * do programa e true se escolheu voltar para a escolha de perfil.
     */
    private static boolean menuCliente(Hotel hotel) {

        while (true) {

            System.out.println();
            System.out.println("======================");
            System.out.println("|  MENU DO CLIENTE  |");
            System.out.println("======================");
            System.out.println("\n1 - Fazer reserva");
            System.out.println("2 - Cancelar reserva");
            System.out.println("3 - Ver quartos disponíveis");
            System.out.println("9 - Voltar");
            System.out.println("0 - Sair");

            int opcao = Leitura.lerInteiro("\nEscolha uma opção: ", 0);

            switch (opcao) {
                case 1:
                    hotel.fazerReserva();
                    Arquivo.salvarHotel(hotel);
                    break;
                case 2:
                    hotel.cancelarReserva();
                    Arquivo.salvarHotel(hotel);
                    break;
                case 3:
                    hotel.listarQuartos(false);
                    break;
                case 9:
                    return true;
                case 0:
                    return false;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        }
    }

    /*
     * equals e toString exigidos pelo trabalho (requisito 5). Esta classe só tem
     * métodos estáticos e não guarda dados próprios, então dois objetos Main
     * são sempre iguais entre si.
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return Main.class.hashCode();
    }

    @Override
    public String toString() {
        return "Main: menu principal do sistema da Pousada do Sol e Mar";
    }
}
