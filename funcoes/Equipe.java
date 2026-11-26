package funcoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Guarda a lista de funcionários do hotel. Faz o login na área de
 * funcionários e o cadastro de novos funcionários.
 * Sempre começa com o funcionário padrão ADMIN (senha 123).
 */

public class Equipe implements Serializable {

    // versão fixa: permite mudar a classe sem perder o funcionarios.dat já salvo
    private static final long serialVersionUID = 6825544468080125290L;

    private List<Funcionario> funcionarios;

    public Equipe() {
        this.funcionarios = new ArrayList<>();
        this.funcionarios.add(new Funcionario("ADMIN", "123"));
    }

    /**
     * pede login e senha e retorna o funcionário que entrou,
     * ou null se o login ou a senha estiverem errados
     */
    public Funcionario fazerLogin() {

        System.out.println("\n--- Login do Funcionário ---");

        String login = Leitura.lerTextoObrigatorio("Login: ");
        String senha = Leitura.lerTextoObrigatorio("Senha: ");

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.autenticar(login, senha)) {
                System.out.println("\nBem-vindo(a), " + funcionario.getLogin() + "!");
                return funcionario;
            }
        }

        System.out.println("\nLogin ou senha incorretos!");
        return null;
    }

    /**
     * mostra os funcionários e exclui o escolhido pelo login.
     * O ADMIN e o próprio funcionário que está logado não podem ser excluídos,
     * para que sempre exista alguém capaz de entrar no sistema.
     */
    public void excluirFuncionario(Funcionario logado) {

        System.out.println("\n--- Excluir Funcionário ---");

        for (Funcionario funcionario : funcionarios) {
            System.out.println("  " + funcionario);
        }

        String login = Leitura.lerTextoObrigatorio("\nLogin do funcionário a excluir: ");

        Funcionario encontrado = null;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getLogin().equalsIgnoreCase(login)) {
                encontrado = funcionario;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("\nFuncionário não encontrado!");
            return;
        }

        if (encontrado.getLogin().equalsIgnoreCase("ADMIN")) {
            System.out.println("\nO funcionário ADMIN não pode ser excluído!");
            return;
        }

        if (encontrado.equals(logado)) {
            System.out.println("\nVocê não pode excluir o próprio usuário!");
            return;
        }

        boolean confirmar = Leitura.lerBoolean(
                "Confirma a exclusão de " + encontrado.getLogin() + "? (S/N): ");

        if (!confirmar) {
            System.out.println("\nExclusão cancelada.");
            return;
        }

        funcionarios.remove(encontrado);
        System.out.println("\nFuncionário excluído com sucesso!");
    }

    // cadastra um novo funcionário, recusando logins que já existem
    public void cadastrarFuncionario() {

        System.out.println("\n--- Cadastrar Funcionário ---");

        String login = Leitura.lerTextoObrigatorio("Login do novo funcionário: ");
        String senha = Leitura.lerTextoObrigatorio("Senha do novo funcionário: ");

        Funcionario novoFuncionario = new Funcionario(login, senha);

        if (funcionarios.contains(novoFuncionario)) {
            System.out.println("\nJá existe um funcionário com esse login!");
            return;
        }

        funcionarios.add(novoFuncionario);

        System.out.println("\nFuncionário cadastrado com sucesso!");
        System.out.println(novoFuncionario);
    }

    // monta o texto do arquivo funcionarios.txt com o login e a senha de cada um
    public String gerarRelatorioFuncionarios() {

        StringBuilder texto = new StringBuilder();

        texto.append("========================================\n")
                .append("       POUSADA DO SOL E MAR\n")
                .append("            FUNCIONÁRIOS\n")
                .append("========================================\n")
                .append("Total de funcionários: ").append(funcionarios.size()).append("\n\n");

        for (Funcionario funcionario : funcionarios) {
            texto.append("Login: ").append(funcionario.getLogin()).append("\n")
                    .append("Senha: ").append(funcionario.getSenha()).append("\n")
                    .append("----------------------------------------\n");
        }

        return texto.toString();
    }

    // duas equipes são iguais se tiverem os mesmos funcionários
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Equipe outra = (Equipe) obj;

        return funcionarios.equals(outra.funcionarios);
    }

    @Override
    public int hashCode() {
        return funcionarios.hashCode();
    }

    @Override
    public String toString() {
        return "Equipe com " + funcionarios.size() + " funcionário(s)";
    }
}
