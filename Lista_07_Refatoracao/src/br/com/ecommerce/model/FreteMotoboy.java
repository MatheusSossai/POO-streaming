package br.com.ecommerce.model;

public class FreteMotoboy implements EstrategiaFrete {

    private static final double TAXA_FIXA_MOTOBOY = 15.00;

    @Override
    public double calcular(double valorPedido) {
        return TAXA_FIXA_MOTOBOY;
    }
}
