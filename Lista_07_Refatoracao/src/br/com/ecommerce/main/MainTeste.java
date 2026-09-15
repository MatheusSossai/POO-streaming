package br.com.ecommerce.main;

import br.com.ecommerce.exception.TipoFreteInvalidoException;
import br.com.ecommerce.model.CalculadoraFrete;
import br.com.ecommerce.model.FreteMotoboy;
import br.com.ecommerce.model.FretePac;
import br.com.ecommerce.model.FreteSedex;

public class MainTeste {

    public static void main(String[] args) {
        CalculadoraFrete calculadora = new CalculadoraFrete();
        double valorPedido = 100.00;

        try {
            double freteSedex = calculadora.processarFrete(valorPedido, new FreteSedex());
            System.out.println("Frete Sedex: R$ " + freteSedex);

            double fretePac = calculadora.processarFrete(valorPedido, new FretePac());
            System.out.println("Frete PAC: R$ " + fretePac);

            double freteMotoboy = calculadora.processarFrete(valorPedido, new FreteMotoboy());
            System.out.println("Frete Motoboy: R$ " + freteMotoboy);

            // Força o erro: nenhuma estratégia informada
            double freteInvalido = calculadora.processarFrete(valorPedido, null);
            System.out.println("Frete inválido: R$ " + freteInvalido); // nunca chega aqui

        } catch (TipoFreteInvalidoException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        System.out.println("Programa finalizado sem crash.");
    }
}
