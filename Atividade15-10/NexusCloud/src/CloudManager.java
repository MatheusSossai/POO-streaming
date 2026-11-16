import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** "Banco de dados" principal: Cliente -> conjunto de Servidores. [RA: 25127039-2] */
public class CloudManager {

    // RN02: HashMap (alta performance); valores são Set (sem duplicatas e sem ordem) [RA: 25127039-2]
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public CloudManager() {
    }

    /** Registra o cliente com um conjunto vazio de servidores (não sobrescreve se já existir). [RA: 25127039-2] */
    public void registrarCliente(Cliente c) {
        this.bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    /** Vincula um servidor ao cliente; se o cliente ainda não existir, ele é registrado antes. [RA: 25127039-2] */
    public void provisionarServidor(Cliente c, Servidor s) {
        this.bancoDeDados.computeIfAbsent(c, chave -> new HashSet<>()).add(s);
    }

    /**
     * RN04: soma o preço/hora dos servidores LIGADOS do cliente, com Streams.
     * getOrDefault evita NullPointerException para cliente inexistente (conjunto vazio = 0.0). [RA: 25127039-2]
     */
    public double calcularFaturaAtiva(Cliente c) {
        return this.bancoDeDados.getOrDefault(c, new HashSet<>()).stream()
                .filter(Servidor::isStatusLigado)         // só servidores ligados [RA: 25127039-2]
                .mapToDouble(Servidor::getPrecoPorHora)   // extrai o preço por hora [RA: 25127039-2]
                .sum();                                   // soma final [RA: 25127039-2]
    }
}
