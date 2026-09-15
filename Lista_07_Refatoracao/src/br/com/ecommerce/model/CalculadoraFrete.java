package br.com.ecommerce.model;

import br.com.ecommerce.exception.TipoFreteInvalidoException;

/**
 * Versão refatorada da calculadora de frete.
 * Não conhece mais as regras específicas de cada transportadora (Sedex, PAC, Motoboy):
 * apenas recebe uma EstrategiaFrete pronta e delega o cálculo a ela.
 * Isso resolve a violação do OCP (Open/Closed Principle) do código legado.
 */
public class CalculadoraFrete {

    public double processarFrete(double valorPedido, EstrategiaFrete estrategia) {
        if (estrategia == null) {
            throw new TipoFreteInvalidoException("Tipo de frete inválido: nenhuma estratégia foi informada.");
        }
        return estrategia.calcular(valorPedido);
    }
}
