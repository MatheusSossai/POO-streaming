package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SistemaSeguranca {

    // RN03: Array de tamanho fixo - garagem de cimento
    private Veiculo[] vagasGaragem;

    // RN04: List que aceita repetição - histórico cronológico
    private List<Funcionario> catracaPrincipal = new ArrayList<>();

    // RN05: Set que garante unicidade - Zero Trust do cofre físico
    private Set<Credencial> cofreFisico = new HashSet<>();

    public SistemaSeguranca(int totalVagas) {
        this.vagasGaragem = new Veiculo[totalVagas];
    }

    /**
     * RN03: aloca o veículo na posição exata do array.
     * Se a vaga não existir, o array "capota" com ArrayIndexOutOfBoundsException.
     */
    public void estacionarVeiculo(Veiculo v, int vaga) {
        vagasGaragem[vaga] = v;
        System.out.println("Garagem: Veículo " + v.getPlaca() + " estacionado na vaga " + vaga);
    }

    /**
     * RN04: registra a passagem na catraca. Permite repetição no mesmo dia.
     */
    public void registrarCatraca(Funcionario f) {
        catracaPrincipal.add(f);
        System.out.println("Catraca: Acesso liberado para " + f.getNome());
    }

    /**
     * RN05: tenta liberar o acesso ao cofre. Bloqueia clones de credencial (mesmo codigoHex).
     */
    public void acessarCofre(Credencial cred) {
        boolean inserido = cofreFisico.add(cred);
        if (inserido) {
            System.out.println("Cofre: Acesso CONCEDIDO. Bem-vindo(a) " + cred.getTitular().getNome());
        } else {
            System.out.println("ALERTA MÁXIMO: Credencial " + cred.getCodigoHex()
                    + " bloqueada! Tentativa de clonagem detectada.");
        }
    }

    public Veiculo[] getVagasGaragem() {
        return vagasGaragem;
    }

    public List<Funcionario> getCatracaPrincipal() {
        return catracaPrincipal;
    }

    public Set<Credencial> getCofreFisico() {
        return cofreFisico;
    }
}
