package br.com.starlog.model;

/** Entidade de domínio do Almoxarifado StarLog (mapeia a tabela "produtos"). */
public class Produto {

    private Long id;
    private String nome;
    private double preco;
    private int estoque;

    /** Construtor de persistência: usado quando o registro já veio do banco (com id). */
    public Produto(Long id, String nome, double preco, int estoque) {
        validarNome(nome);
        validarPreco(preco);
        validarEstoque(estoque);
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    /** Construtor de negócio: produto novo, ainda sem id (o SQLite gera no INSERT). */
    public Produto(String nome, double preco, int estoque) {
        this(null, nome, preco, estoque);
    }

    // ---- Invariantes (Fail-Fast): o objeto nunca existe em estado inválido ----
    private static void validarNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome deve ter no minimo 3 caracteres.");
        }
    }

    private static void validarPreco(double preco) {
        // !(preco > 0.0) barra zero, negativos e também NaN
        if (!(preco > 0.0)) {
            throw new IllegalArgumentException("Preco deve ser maior que 0.0.");
        }
    }

    private static void validarEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque nao pode ser negativo.");
        }
    }

    public Long getId()      { return id; }
    public String getNome()  { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque()  { return estoque; }

    // Setter do id: usado pelo DAO para devolver o id gerado no INSERT
    public void setId(Long id) { this.id = id; }

    // Setters de preco e estoque reaproveitam as mesmas validações do construtor
    public void setPreco(double preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        validarEstoque(estoque);
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3s | %-25s | R$ %9.2f | Estoque: %4d un",
                id != null ? id : "-", nome, preco, estoque);
    }
}
