import java.util.Objects;

/** Aplicação (container) que roda dentro de um servidor. Identidade lógica: ID. [RA: 25127039-2] */
public class Container {

    private String id;
    private String nomeApp;
    private double ramNecessaria; // RAM em GB [RA: 25127039-2]

    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId()            { return id; }
    public String getNomeApp()       { return nomeApp; }
    public double getRamNecessaria() { return ramNecessaria; }

    // Dois containers são iguais se tiverem o mesmo ID [RA: 25127039-2]
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // só o ID participa do hash [RA: 25127039-2]
    }

    @Override
    public String toString() {
        return "Container [id=" + id + ", app=" + nomeApp + ", ram=" + ramNecessaria + "GB]";
    }
}
