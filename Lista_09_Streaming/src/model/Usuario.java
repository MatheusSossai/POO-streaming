package model;

import java.util.Objects;

public class Usuario {

    // Questão 08: constante global da plataforma
    public static final String NOME_PLATAFORMA = "JavaFlix";

    // Questão 09: contador estático de instâncias
    private static int totalUsuarios = 0;

    // Questão 03: atributos privados (encapsulamento)
    private String nome;
    private String email;
    private boolean ativo;

    public Usuario(String nome, String email, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        totalUsuarios++; // Questão 09: incrementa a cada construção
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public static int getTotalUsuarios() {
        return totalUsuarios;
    }

    // Questão 07: dois usuários são iguais se possuem o mesmo e-mail
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    // Questão 06: representação textual padronizada
    @Override
    public String toString() {
        return "Usuário: " + nome + " | Contato: " + email + " | Ativo: " + ativo;
    }
}
