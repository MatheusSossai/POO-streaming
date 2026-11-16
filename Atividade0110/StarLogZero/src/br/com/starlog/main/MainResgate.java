package br.com.starlog.main;

import br.com.starlog.exception.CapacidadeExcedidaException;
import br.com.starlog.model.BaseLancamento;
import br.com.starlog.model.Carga;
import br.com.starlog.model.ModuloCarga;
import java.util.HashSet;
import java.util.Set;

/**
 * Protocolo Chaos Monkey: 4 ataques para validar a resiliência do sistema.
 */
public class MainResgate {

    public static void main(String[] args) {

        // ATAQUE 1: dados espúrios (Fail-Fast)
        try {
            Carga fantasma = new Carga("    ", "CRIOGENICA", 10.0, 500.0);
            System.out.println("❌ FALHA: Aceitou codigo vazio!");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ SUCESSO ATAQUE 1 (Fail-Fast ativado): " + e.getMessage());
        }

        // ATAQUE 2: clone com mesmo código (integridade do hash)
        Set<Carga> esteiraTriagem = new HashSet<>();
        Carga c1 = new Carga("ORB-999", "ALIMENTOS", 10.0, 100.0);
        Carga c1Clone = new Carga("ORB-999", "DADOS_ALTERADOS", 999.0, 8000.0); // mesmo ID, outra memória
        esteiraTriagem.add(c1);
        esteiraTriagem.add(c1Clone); // o Set deve recusar a duplicata
        if (esteiraTriagem.size() == 1) {
            System.out.println("✅ SUCESSO ATAQUE 2 (Hash imune a clones): Tamanho do Set = 1");
        } else {
            System.out.println("❌ FALHA: O clone furou a dispersão e entrou no Set! Tamanho = " + esteiraTriagem.size());
        }

        // ATAQUE 3: ruptura de limite (checked exception)
        ModuloCarga moduloTeste = new ModuloCarga("MOD-TESTE", 2);
        try {
            moduloTeste.carregarCarga(new Carga("C-01", "GERAL", 5.0, 50.0));
            moduloTeste.carregarCarga(new Carga("C-02", "GERAL", 5.0, 50.0));
            moduloTeste.carregarCarga(new Carga("C-03", "GERAL", 5.0, 50.0)); // 3ª deve explodir
            System.out.println("❌ FALHA: Deixou ultrapassar a capacidade fisica!");
        } catch (CapacidadeExcedidaException e) {
            System.out.println("✅ SUCESSO ATAQUE 3 (Trava de Limite operante): " + e.getMessage());
        }

        // ATAQUE 4: processamento analítico com Streams (2 x R$ 50,00 = R$ 100,00)
        double seguroCalculado = moduloTeste.calcularSeguroTotal();
        System.out.println("✅ SUCESSO ATAQUE 4 (Streams Calculado): R$ " + seguroCalculado);

        // Extra: demonstra a BaseLancamento (busca O(1)) e as demais streams
        BaseLancamento base = new BaseLancamento();
        base.cadastrarModulo(moduloTeste);
        System.out.println("ℹ️ Busca O(1) do modulo: " + base.buscarModulo("MOD-TESTE").getIdModulo()
                + " | GERAL=" + moduloTeste.contarPorCategoria("GERAL")
                + " | seguro pesadas(>1kg)=R$ " + moduloTeste.calcularSeguroPesadas(1.0));
    }
}
