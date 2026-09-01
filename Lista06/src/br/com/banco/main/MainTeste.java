package br.com.banco.main;

import br.com.banco.Agencia;
import br.com.banco.Cliente;
import br.com.banco.ContaBancaria;

public class MainTeste {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("111.222.333-44", "Joao Silva", "joao@email.com");
        Cliente cliente2 = new Cliente("111.222.333-44", "J. Silva", "jsilva@email.com");
        if (cliente1.equals(cliente2)) {
            System.out.println("Os clientes sao IGUAIS (mesmo CPF).");
        } else {
            System.out.println("Os clientes sao DIFERENTES.");
        }

        ContaBancaria conta = new ContaBancaria("00001-1", cliente1, 50.0);

        boolean sucessoSaque = conta.sacar(50.0);
        System.out.println("Saque de 50.0 realizado com sucesso? " + sucessoSaque);
        System.out.println("Saldo atual da conta: " + conta.getSaldo());

        System.out.println("Banco: " + Agencia.NOME_BANCO);
        System.out.println("Total de contas abertas: " + Agencia.getTotalContasAbertas());
    }
}
