package br.com.starlog.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Centraliza a conexão JDBC com o arquivo SQLite local. */
public final class ConexaoFactory {

    private static final String URL = "jdbc:sqlite:almoxarifado.db";

    private ConexaoFactory() {
        throw new UnsupportedOperationException("ConexaoFactory e utilitaria!");
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
