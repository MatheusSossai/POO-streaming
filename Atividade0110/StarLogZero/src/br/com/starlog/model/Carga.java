package br.com.starlog.model;

import java.util.Objects;

/**
 * Entidade de domínio com identidade única: o código de rastreio.
 */
public class Carga {

    // RN01: código de rastreio é FINAL (imutável) e não possui setter
    private final String codigoRastreio;
    private String categoria;
    private double pesoKg;
    private double valorSeguro;

    public Carga(String codigoRastreio, String categoria, double pesoKg, double valorSeguro) {
        // RN02: barreira Fail-Fast ANTES de qualquer atribuição.
        // O "if" aqui é permitido: a proibição vale só para os métodos analíticos do ModuloCarga.
        if (codigoRastreio == null || codigoRastreio.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo de rastreio da carga nao pode ser nulo ou vazio.");
        }
        // RN02: o peso precisa ser estritamente positivo (a forma !(x > 0) também barra NaN)
        if (!(pesoKg > 0)) {
            throw new IllegalArgumentException("Peso da carga deve ser maior que zero.");
        }
        // Só depois de validar é que os atributos são atribuídos
        this.codigoRastreio = codigoRastreio;
        this.categoria = categoria;
        this.pesoKg = pesoKg;
        this.valorSeguro = valorSeguro;
    }

    // Getters (sem setter para codigoRastreio, conforme RN01)
    public String getCodigoRastreio() { return codigoRastreio; }
    public String getCategoria()      { return categoria; }
    public double getPesoKg()         { return pesoKg; }
    public double getValorSeguro()    { return valorSeguro; }

    /**
     * RN03: igualdade baseada ESTRITAMENTE no codigoRastreio.
     * Peso, categoria e seguro ficam de fora para o HashSet reconhecer clones.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                          // mesmo objeto na memória
        if (obj == null || getClass() != obj.getClass()) return false; // nulo ou tipo diferente
        Carga outra = (Carga) obj;
        return Objects.equals(codigoRastreio, outra.codigoRastreio);   // só compara o código
    }

    // Contrato: objetos iguais DEVEM ter o mesmo hash -> usa apenas o codigoRastreio
    @Override
    public int hashCode() {
        return Objects.hash(codigoRastreio);
    }

    // RN04: representação textual no padrão exigido
    @Override
    public String toString() {
        return "Carga [rastreio=" + codigoRastreio + ", categoria=" + categoria
                + ", peso=" + pesoKg + "kg, seguro=R$ " + valorSeguro + "]";
    }
}
