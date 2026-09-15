package br.com.ecommerce.model;

/**
 * Contrato do padrão Strategy para o cálculo de frete.
 * Cada transportadora implementa sua própria regra de cálculo,
 * eliminando a necessidade de if/else na calculadora.
 */
public interface EstrategiaFrete {

    double calcular(double valorPedido);
}
