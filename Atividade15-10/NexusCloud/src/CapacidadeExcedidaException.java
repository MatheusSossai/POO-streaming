/**
 * Exceção CHECKED (herda de Exception): o compilador obriga a tratar ou declarar.
 * Lançada quando a RAM do servidor não comporta o novo container. [RA: 25127039-2]
 */
public class CapacidadeExcedidaException extends Exception {
    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}
