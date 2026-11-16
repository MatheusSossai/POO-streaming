/** Roteiro de testes do NexusCloud Manager. [RA: 25127039-2] */
public class MainCloud {

    // "throws" é necessário porque alocarContainer declara uma checked exception [RA: 25127039-2]
    // e o Passo 6 (quando descomentado) a chama fora de um try-catch. [RA: 25127039-2]
    public static void main(String[] args) throws CapacidadeExcedidaException {

        // 1. Instancia o CloudManager [RA: 25127039-2]
        CloudManager manager = new CloudManager();

        // 2. Instancia 1 cliente [RA: 25127039-2]
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");

        // 3. Instancia 1 servidor (nasce desligado) [RA: 25127039-2]
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // 4. Registra o cliente e vincula o servidor a ele [RA: 25127039-2]
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        // 5. Instancia 2 containers diferentes [RA: 25127039-2]
        Container c1 = new Container("C-01", "App Alpha", 10.0);
        Container c2 = new Container("C-02", "App Beta", 8.0);

        // 6. Falha Unchecked: servidor desligado. Descomente para ver o erro vermelho (ServidorOfflineException) [RA: 25127039-2]
        //    e comente de novo para o programa seguir. [RA: 25127039-2]
        // servidor.alocarContainer(c1); [RA: 25127039-2]

        // 7. Liga o servidor [RA: 25127039-2]
        servidor.ligar();

        // 8. Alocação com sucesso do Container 1 (10.0 de 16.0 GB) [RA: 25127039-2]
        servidor.alocarContainer(c1);
        System.out.println("✅ " + c1 + " alocado. RAM em uso: " + servidor.calcularRamEmUso() + "GB");

        // 9. Falha Checked: 10.0 + 8.0 = 18.0 > 16.0 -> tratada com try-catch [RA: 25127039-2]
        try {
            servidor.alocarContainer(c2);
            System.out.println("❌ FALHA: deveria ter estourado a capacidade!");
        } catch (CapacidadeExcedidaException e) {
            System.out.println("⚠️ Alocação recusada: " + e.getMessage());
        }

        // 10. Fatura ativa do cliente (esperado: 5.50) [RA: 25127039-2]
        System.out.println("💰 Fatura ativa de " + cliente.getNome() + ": R$ " + manager.calcularFaturaAtiva(cliente));
    }
}
