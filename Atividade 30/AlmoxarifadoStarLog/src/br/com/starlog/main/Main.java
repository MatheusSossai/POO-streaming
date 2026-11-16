package br.com.starlog.main;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;
import java.util.List;
import java.util.Optional;

/** Simulação de negócio: ciclo completo CRUD no Almoxarifado StarLog. */
public class Main {

    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAOSqlite();

        // Reset do ambiente: permite rodar o programa várias vezes com o mesmo resultado final
        dao.listarTodos().forEach(p -> dao.excluir(p.getId()));

        System.out.println("=== [CREATE] Cadastrando produtos ===");
        Produto teclado = new Produto("Teclado Mecânico RGB", 349.90, 10);
        Produto mousepad = new Produto("Mousepad Speed XL", 79.90, 25);
        dao.salvar(teclado);
        dao.salvar(mousepad);
        System.out.println("Teclado salvo com ID gerado:  " + teclado.getId());
        System.out.println("Mousepad salvo com ID gerado: " + mousepad.getId());

        System.out.println("\n=== [READ] Listando todos os produtos ===");
        imprimirTabela(dao.listarTodos());

        System.out.println("\n=== [UPDATE] Teclado: estoque 15 e preço R$ 289,90 ===");
        teclado.setEstoque(15);
        teclado.setPreco(289.90);
        boolean atualizou = dao.atualizar(teclado);
        System.out.println("atualizar() retornou: " + atualizou);
        Optional<Produto> recarregado = dao.buscarPorId(teclado.getId());
        System.out.println("Registro atualizado: " + recarregado.map(Produto::toString).orElse("não encontrado"));

        System.out.println("\n=== [DELETE] Removendo o Mousepad por ID ===");
        System.out.println("excluir(" + mousepad.getId() + ") retornou: " + dao.excluir(mousepad.getId()));
        boolean inexistente = dao.excluir(888L);
        System.out.println("excluir(888) retornou: " + inexistente);
        if (!inexistente) {
            System.out.println("ℹ️ ID 888 não existe: nada foi removido (tratado sem travar).");
        }

        System.out.println("\n=== [FINAL STATE] Estado final do banco ===");
        List<Produto> finais = dao.listarTodos();
        imprimirTabela(finais);
        System.out.println("Total de produtos restantes: " + finais.size());
    }

    // Exibe os produtos em colunas limpas no terminal
    private static void imprimirTabela(List<Produto> produtos) {
        System.out.println("-".repeat(70));
        produtos.forEach(System.out::println);
        System.out.println("-".repeat(70));
    }
}
