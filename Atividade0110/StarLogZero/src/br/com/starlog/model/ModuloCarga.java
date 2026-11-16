package br.com.starlog.model;

import br.com.starlog.exception.CapacidadeExcedidaException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contêiner físico que armazena cargas até um limite de capacidade.
 */
public class ModuloCarga {

    // RN06: atributos privados
    private String idModulo;
    private int capacidadeMaxima;
    private List<Carga> cargas;

    public ModuloCarga(String idModulo, int capacidadeMaxima) {
        this.idModulo = idModulo;
        this.capacidadeMaxima = capacidadeMaxima;
        // RN06: lista inicializada no construtor para evitar NullPointerException
        this.cargas = new ArrayList<>();
    }

    /**
     * RN07: trava física de capacidade (Fail-Fast).
     * Lança uma checked exception se o módulo já estiver cheio.
     */
    public void carregarCarga(Carga carga) throws CapacidadeExcedidaException {
        if (this.cargas.size() >= this.capacidadeMaxima) {
            throw new CapacidadeExcedidaException(
                "Capacidade maxima de " + this.capacidadeMaxima + " atingida no modulo " + this.idModulo);
        }
        // Havendo vaga, a carga entra na lista interna
        this.cargas.add(carga);
    }

    // RN08 (Streams, sem for/if): soma o seguro de todas as cargas
    public double calcularSeguroTotal() {
        return this.cargas.stream()
                .mapToDouble(Carga::getValorSeguro) // extrai o seguro de cada carga
                .sum();                             // soma tudo
    }

    // RN08: conta quantas cargas pertencem à categoria informada
    public long contarPorCategoria(String categoria) {
        return this.cargas.stream()
                .filter(c -> c.getCategoria().equals(categoria)) // mantém só a categoria desejada
                .count();
    }

    // RN08: soma o seguro apenas das cargas com peso acima do corte
    public double calcularSeguroPesadas(double pesoCorte) {
        return this.cargas.stream()
                .filter(c -> c.getPesoKg() > pesoCorte) // só as pesadas
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }

    public String getIdModulo() { return idModulo; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
}
