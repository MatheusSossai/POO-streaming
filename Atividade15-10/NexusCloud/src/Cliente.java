import java.util.Objects;

/** Cliente da NexusCloud. Identidade lógica: CPF. [RA: 25127039-2] */
public class Cliente {

    // Atributos privados (encapsulamento) [RA: 25127039-2]
    private String cpf;
    private String nome;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf()  { return cpf; }
    public String getNome() { return nome; }

    // Dois clientes são iguais se tiverem o mesmo CPF [RA: 25127039-2]
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                                  // mesma referência [RA: 25127039-2]
        if (obj == null || getClass() != obj.getClass()) return false; // nulo/tipo diferente [RA: 25127039-2]
        Cliente outro = (Cliente) obj;
        return Objects.equals(cpf, outro.cpf);                         // compara só o CPF [RA: 25127039-2]
    }

    // hashCode coerente com equals: usa somente o CPF (essencial para usar Cliente como chave de HashMap) [RA: 25127039-2]
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", nome=" + nome + "]";
    }
}
