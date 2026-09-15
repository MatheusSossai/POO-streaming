package br.com.ecommerce.model;

public class FreteSedex implements EstrategiaFrete {

    private static final double TAXA_SEDEX = 0.10; // 10% sobre o valor do pedido

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * TAXA_SEDEX;
    }
}
