package br.com.bookstore.dao;

import br.com.bookstore.model.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDAOSqlite implements LivroDAO {

    private static final String URL_PADRAO = "jdbc:sqlite:bookstore.db";

    private static final String SQL_CREATE =
            "CREATE TABLE IF NOT EXISTS livros ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
          + "titulo TEXT NOT NULL, "
          + "autor TEXT NOT NULL, "
          + "preco REAL NOT NULL);";

    private static final String SQL_INSERT =
            "INSERT INTO livros (titulo, autor, preco) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ID =
            "SELECT id, titulo, autor, preco FROM livros WHERE id = ?";
    private static final String SQL_SELECT_ALL =
            "SELECT id, titulo, autor, preco FROM livros ORDER BY id";

    private final String url;

    public LivroDAOSqlite() {
        this(URL_PADRAO);
    }

    public LivroDAOSqlite(String url) {
        this.url = url;
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(SQL_CREATE);
        } catch (SQLException e) {
            throw new PersistenciaException("Falha ao inicializar o schema do banco.", e);
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url);
    }

    @Override
    public void salvar(Livro livro) {
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setDouble(3, livro.getPreco());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    livro.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Falha ao salvar o livro: " + livro.getTitulo(), e);
        }
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ID)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapearLinha(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Falha ao buscar o livro de id " + id, e);
        }
    }

    @Override
    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                livros.add(mapearLinha(rs));
            }
            return livros;
        } catch (SQLException e) {
            throw new PersistenciaException("Falha ao listar os livros.", e);
        }
    }

    /** Ponto único de extração de colunas, evitando duplicação. */
    private Livro mapearLinha(ResultSet rs) throws SQLException {
        return new Livro(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getDouble("preco"));
    }
}
