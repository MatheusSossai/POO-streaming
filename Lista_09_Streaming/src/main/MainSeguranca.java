package main;

import model.Credencial;
import model.Sede;

public class MainSeguranca {

    public static void main(String[] args) {
        Sede sede = new Sede();
        Credencial credencial = new Credencial("A1B2C3");

        // 2. Registra a mesma credencial 2x na catraca
        sede.registrarPassagemCatraca(credencial);
        sede.registrarPassagemCatraca(credencial);
        System.out.println("Total de registros na catraca: " + sede.getHistoricoCatraca().size());

        // 3. Tenta autorizar a mesma credencial 2x no cofre
        sede.autorizarEntradaCofre(credencial);
        sede.autorizarEntradaCofre(credencial);

        // 4. Preenche as 3 vagas e tenta acessar a 4ª (índice 3) -> deve estourar a exceção
        sede.estacionarVeiculo("AAA-0001", 0);
        sede.estacionarVeiculo("BBB-0002", 1);
        sede.estacionarVeiculo("CCC-0003", 2);
        sede.estacionarVeiculo("DDD-0004", 3); // ArrayIndexOutOfBoundsException esperado aqui
    }
}
