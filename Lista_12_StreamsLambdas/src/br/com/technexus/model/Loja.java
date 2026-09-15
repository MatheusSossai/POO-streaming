package br.com.technexus.model;

import java.util.List;
import java.util.ArrayList;

public class Loja {

    private List<Produto> catalogo = new ArrayList<>();

    public void cadastrar(Produto p) {
        this.catalogo.add(p);
    }

    /**
     * Exercício 3: filtra o catálogo por categoria, sem usar for/if.
     */
    public List<Produto> buscarPorCategoria(String catDesejada) {
        return this.catalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(catDesejada))
                .toList();
    }

    /**
     * Exercício 4: soma o preço de todos os produtos do catálogo.
     */
    public double calcularPatrimonioTotal() {
        return this.catalogo.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }

    /**
     * Exercício 5: filtra por categoria e já soma o preço dos produtos filtrados,
     * tudo em uma única pipeline de stream.
     */
    public double calcularTotalPorCategoria(String catDesejada) {
        return this.catalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(catDesejada))
                .mapToDouble(Produto::getPreco)
                .sum();
    }
}
