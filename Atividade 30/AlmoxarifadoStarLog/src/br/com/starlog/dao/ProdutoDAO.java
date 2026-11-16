package br.com.starlog.dao;

import br.com.starlog.model.Produto;
import java.util.List;
import java.util.Optional;

/** Contrato de persistência: não conhece SQL, SQLite nem JDBC. */
public interface ProdutoDAO {
    void salvar(Produto produto);              // Create
    Optional<Produto> buscarPorId(Long id);    // Read (um)
    List<Produto> listarTodos();               // Read (todos)
    boolean atualizar(Produto produto);        // Update
    boolean excluir(Long id);                  // Delete
}
