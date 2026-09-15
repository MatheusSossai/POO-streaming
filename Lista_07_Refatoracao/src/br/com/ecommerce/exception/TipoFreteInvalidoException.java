package br.com.ecommerce.exception;

/**
 * Exceção customizada (unchecked) lançada quando uma estratégia de frete
 * inválida (nula) é enviada para processamento.
 */
public class TipoFreteInvalidoException extends RuntimeException {

    public TipoFreteInvalidoException(String mensagem) {
        super(mensagem);
    }
}
