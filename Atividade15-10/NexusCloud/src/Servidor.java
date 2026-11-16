import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Servidor virtual. Identidade lógica: IP. [RA: 25127039-2] */
public class Servidor {

    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado; // por padrão (false) o servidor "nasce" desligado [RA: 25127039-2]

    // RN02: lista cronológica inicializada na declaração -> evita NullPointerException [RA: 25127039-2]
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
    }

    public void ligar()    { this.statusLigado = true; }
    public void desligar() { this.statusLigado = false; }

    /**
     * RN03: aloca o container aplicando a política Fail-Fast.
     * Valida ANTES de adicionar na lista; se algo falhar, nada é alterado. [RA: 25127039-2]
     */
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        // Regra 1 (Unchecked): servidor desligado não aceita containers [RA: 25127039-2]
        if (!this.statusLigado) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }
        // Regra 2 (Checked): RAM já em uso + RAM do novo container não pode passar do total [RA: 25127039-2]
        if (calcularRamEmUso() + c.getRamNecessaria() > this.ramTotal) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + this.ip + ".");
        }
        // Passou nas duas barreiras: pode adicionar [RA: 25127039-2]
        this.containers.add(c);
    }

    /** RN04: soma da RAM de todos os containers alocados, usando Streams (sem for). [RA: 25127039-2] */
    public double calcularRamEmUso() {
        return this.containers.stream()
                .mapToDouble(Container::getRamNecessaria) // extrai a RAM de cada container [RA: 25127039-2]
                .sum();                                   // soma total [RA: 25127039-2]
    }

    public String getIp()            { return ip; }
    public double getPrecoPorHora()  { return precoPorHora; }
    public double getRamTotal()      { return ramTotal; }
    public boolean isStatusLigado()  { return statusLigado; }
    public List<Container> getContainers() { return containers; }

    // Dois servidores são iguais se tiverem o mesmo IP [RA: 25127039-2]
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public String toString() {
        return "Servidor [ip=" + ip + ", ramTotal=" + ramTotal + "GB, preco/h=R$ " + precoPorHora
                + ", ligado=" + statusLigado + "]";
    }
}
