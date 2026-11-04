package br.com.bookstore;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Garante execução determinística: banco limpo => IDs 1, 2 e 3.
        Files.deleteIfExists(Path.of("bookstore.db"));

        // 1) DIP: o Main depende da abstração; a implementação é escolhida aqui.
        LivroDAO dao = new LivroDAOSqlite();

        // 2) Persistência de 3 obras
        System.out.println("=== 2) Persistindo livros ===");
        List<Livro> novos = List.of(
                new Livro("Clean Code", "Robert C. Martin", 89.90),
                new Livro("Design Patterns", "Erich Gamma et al.", 129.50),
                new Livro("Refactoring", "Martin Fowler", 109.00));

        for (Livro livro : novos) {
            dao.salvar(livro);
            System.out.println("Salvo com ID " + livro.getId() + " -> " + livro.getTitulo());
        }

        // 3) Busca por ID existente
        System.out.println("\n=== 3) Buscando ID 2 ===");
        dao.buscarPorId(2L).ifPresentOrElse(
                l -> System.out.printf("Encontrado: [%d] %s, de %s, por R$ %.2f%n",
                        l.getId(), l.getTitulo(), l.getAutor(), l.getPreco()),
                () -> System.out.println("Aviso: nenhum livro encontrado com ID 2."));

        // 4) Busca por ID inexistente: sem NullPointerException
        System.out.println("\n=== 4) Buscando ID 999 ===");
        dao.buscarPorId(999L).ifPresentOrElse(
                l -> System.out.println("Encontrado: " + l),
                () -> System.out.println("Aviso: o livro de ID 999 não existe. Nenhuma exceção foi lançada."));

        // 5) Listagem tabular
        System.out.println("\n=== 5) Listando todos ===");
        System.out.printf("%-4s | %-18s | %-20s | %10s%n", "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println("-".repeat(62));
        for (Livro l : dao.listarTodos()) {
            System.out.printf("%-4d | %-18s | %-20s | %10s%n",
                    l.getId(), l.getTitulo(), l.getAutor(), String.format("R$ %.2f", l.getPreco()));
        }
    }
}
