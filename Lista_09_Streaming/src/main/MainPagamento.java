package main;

import model.Pagamento;
import model.PagamentoCartao;
import model.PagamentoPix;
import model.ProcessadorPagamento;

public class MainPagamento {

    public static void main(String[] args) {
        ProcessadorPagamento processador = new ProcessadorPagamento();

        Pagamento pix = new PagamentoPix();
        Pagamento cartao = new PagamentoCartao();

        processador.finalizarCompra(49.90, pix);
        processador.finalizarCompra(129.90, cartao);

        // O polimorfismo permite adicionar novas formas de pagamento
        // sem nunca precisar alterar o ProcessadorPagamento.
    }
}
