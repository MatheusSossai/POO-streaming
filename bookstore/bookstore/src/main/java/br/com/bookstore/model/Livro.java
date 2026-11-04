package br.com.bookstore.model;

/**
 * Entidade de domínio. Não conhece JDBC nem qualquer detalhe de persistência.
 * Suas invariantes são garantidas no construtor.
 */
public class Livro {

    private Long id;
    private final String titulo;
    private final String autor;
    private final double preco;

    /** Construtor completo: usado para hidratar tuplas vindas do banco. */
    public Livro(Long id, String titulo, String autor, double preco) {
        if (titulo == null || titulo.trim().length() < 2) {
            throw new IllegalArgumentException("O título é obrigatório e deve ter ao menos 2 caracteres.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor é obrigatório.");
        }
        if (Double.isNaN(preco) || preco <= 0.0) {
            throw new IllegalArgumentException("O preço de venda deve ser estritamente positivo.");
        }
        this.id = id;
        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.preco = preco;
    }

    /** Construtor de negócio: livro novo, ainda sem PK (id = null). */
    public Livro(String titulo, String autor, double preco) {
        this(null, titulo, autor, preco);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPreco() {
        return preco;
    }

    /** Único setter permitido: sincroniza a PK gerada pelo banco. */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Livro{id=%s, titulo='%s', autor='%s', preco=R$ %.2f}",
                id, titulo, autor, preco);
    }
}
