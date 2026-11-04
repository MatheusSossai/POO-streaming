package br.com.bookstore.dao;

/** Exceção não verificada que encapsula falhas de infraestrutura (SQLException). */
public class PersistenciaException extends RuntimeException {

    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
