package funcoes;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um funcionário do hotel, com o login e a senha
 * usados para entrar na área de funcionários.
 */

public class Funcionario implements Serializable {

    // versão fixa: permite mudar a classe sem perder o funcionarios.dat já salvo
    private static final long serialVersionUID = -4623801008547726324L;

    private String login;
    private String senha;

    public Funcionario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // confere se o login (sem diferenciar maiúsculas) e a senha estão corretos
    public boolean autenticar(String login, String senha) {
        return this.login.equalsIgnoreCase(login) && this.senha.equals(senha);
    }

    // comparar funcionários pelo login, sem diferenciar maiúsculas e minúsculas
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Funcionario outro = (Funcionario) obj;

        return login.equalsIgnoreCase(outro.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login.toUpperCase());
    }

    // a senha não aparece no toString por segurança
    @Override
    public String toString() {
        return "Funcionário: " + login;
    }
}
