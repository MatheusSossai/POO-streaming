package br.com.ecommerce.model;

public class FretePac implements EstrategiaFrete {

    private static final double TAXA_PAC = 0.05; // 5% sobre o valor do pedido

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * TAXA_PAC;
    }
}
