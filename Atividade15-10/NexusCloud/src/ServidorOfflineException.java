/**
 * Exceção UNCHECKED (herda de RuntimeException): não obriga try-catch.
 * Lançada quando se tenta alocar container em servidor desligado. [RA: 25127039-2]
 */
public class ServidorOfflineException extends RuntimeException {
    public ServidorOfflineException(String msg) {
        super(msg); // repassa a mensagem para a superclasse [RA: 25127039-2]
    }
}
