package br.com.techcorp.model;

import java.util.Objects;

public class Funcionario {

    private String matricula;
    private String nome;
    private String cargo;

    public Funcionario(String matricula, String nome, String cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    /**
     * RN01: a identidade do funcionário é definida exclusivamente pela matrícula,
     * nunca pelo nome (pode haver homônimos na empresa).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Funcionario{matricula='" + matricula + "', nome='" + nome + "', cargo='" + cargo + "'}";
    }
}
