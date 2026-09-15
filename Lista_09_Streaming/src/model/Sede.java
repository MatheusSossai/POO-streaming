package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sede {

    // Questão 15: array de capacidade fixa (3 vagas)
    private String[] vagasGaragem = new String[3];

    // Questão 17: histórico cronológico, aceita repetição
    private List<Credencial> historicoCatraca = new ArrayList<>();

    // Questão 18: coleção que garante unicidade
    private Set<Credencial> acessoCofre = new HashSet<>();

    /**
     * Estaciona um veículo pela placa na vaga informada.
     * Se a vaga não existir no array, lança ArrayIndexOutOfBoundsException.
     */
    public void estacionarVeiculo(String placa, int vaga) {
        vagasGaragem[vaga] = placa;
        System.out.println("Garagem: veículo " + placa + " estacionado na vaga " + vaga);
    }

    public void registrarPassagemCatraca(Credencial c) {
        historicoCatraca.add(c);
        System.out.println("Catraca: passagem registrada para credencial " + c.getCodigoHex());
    }

    /**
     * Questão 19: tenta autorizar entrada no cofre; se já estiver ativa, bloqueia.
     */
    public void autorizarEntradaCofre(Credencial c) {
        boolean autorizado = acessoCofre.add(c);
        if (!autorizado) {
            System.out.println("Alerta de Segurança: Tentativa de acesso duplicado com credencial já ativa no cofre");
        } else {
            System.out.println("Cofre: acesso autorizado para credencial " + c.getCodigoHex());
        }
    }

    public String[] getVagasGaragem() {
        return vagasGaragem;
    }

    public List<Credencial> getHistoricoCatraca() {
        return historicoCatraca;
    }

    public Set<Credencial> getAcessoCofre() {
        return acessoCofre;
    }
}
