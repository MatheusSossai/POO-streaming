package main;

import model.Credencial;
import model.Departamento;
import model.Funcionario;
import model.SistemaSeguranca;
import model.Veiculo;

public class MainTeste {

    public static void main(String[] args) {
        // 1. Instancia Departamento, Funcionário e Veículo
        Departamento ti = new Departamento("TI", "Tecnologia da Informação", 3);
        Funcionario func = new Funcionario("F001", "Ana Souza", ti);
        Veiculo carro = new Veiculo("ABC-1234", "Onix", func);

        // 2. SistemaSeguranca com apenas 2 vagas de garagem
        SistemaSeguranca sistema = new SistemaSeguranca(2);

        // 3. Credencial original
        Credencial c1 = new Credencial("FFF-999", true, func);

        // 4. Clone: mesmo código hex, outro objeto na memória
        Credencial clone = new Credencial("FFF-999", true, func);

        // 5. Teste da Catraca: mesmo funcionário passa duas vezes
        sistema.registrarCatraca(func);
        sistema.registrarCatraca(func);

        // 6. Teste do Cofre: c1 entra, clone é bloqueado
        sistema.acessarCofre(c1);
        sistema.acessarCofre(clone);

        // 7. Teste da Garagem: estaciona na vaga válida, depois força o erro na vaga 5
        sistema.estacionarVeiculo(carro, 0);
        sistema.estacionarVeiculo(carro, 5); // ArrayIndexOutOfBoundsException esperado aqui
    }
}
