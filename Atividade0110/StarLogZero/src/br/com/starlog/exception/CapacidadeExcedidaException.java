package br.com.starlog.exception;

/**
 * RN05 - Checked Exception: herda DIRETAMENTE de java.lang.Exception.
 * Por ser "checked", o compilador obriga quem chama o método a tratar (try-catch)
 * ou a declarar (throws). É proibido estender RuntimeException aqui.
 */
public class CapacidadeExcedidaException extends Exception {

    // Recebe a mensagem descritiva e a encaminha para a superclasse Exception
    public CapacidadeExcedidaException(String mensagem) {
        super(mensagem);
    }
}
