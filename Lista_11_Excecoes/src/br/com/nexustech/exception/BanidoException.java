package br.com.nexustech.exception;

/**
 * Checked exception (herda de Exception, não de RuntimeException):
 * o compilador obriga quem chamar a tratar ou declarar essa exceção.
 */
public class BanidoException extends Exception {

    public BanidoException() {
        super("Jogador Banido!");
    }
}
