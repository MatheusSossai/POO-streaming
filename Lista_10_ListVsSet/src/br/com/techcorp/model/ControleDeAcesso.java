package br.com.techcorp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControleDeAcesso {

    // RN02: mantém ordem e permite repetição -> List (ArrayList)
    private List<Funcionario> historicoCatraca = new ArrayList<>();

    // RN03: não garante ordenação, não permite duplicados, verificação otimizada -> Set (HashSet)
    private Set<Funcionario> autorizadosSalaSegura = new HashSet<>();

    public void registrarPassagem(Funcionario f) {
        historicoCatraca.add(f);
        System.out.println("Catraca: passagem registrada para " + f.getNome() + " (matrícula " + f.getMatricula() + ")");
    }

    public void concederAcessoSala(Funcionario f) {
        boolean inserido = autorizadosSalaSegura.add(f);
        if (inserido) {
            System.out.println("Acesso liberado para a Sala Segura");
        } else {
            System.out.println("Aviso: Matrícula já registrada na sala. Acesso não permitido.");
        }
    }

    public List<Funcionario> getHistoricoCatraca() {
        return historicoCatraca;
    }

    public Set<Funcionario> getAutorizadosSalaSegura() {
        return autorizadosSalaSegura;
    }
}
