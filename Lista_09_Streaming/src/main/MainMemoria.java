package main;

import model.Usuario;

public class MainMemoria {

    public static void main(String[] args) {
        Usuario u1 = new Usuario("Beatriz Nunes", "beatriz@email.com", true);
        Usuario u2 = new Usuario("Beatriz N. Silva", "beatriz@email.com", false);

        if (u1.equals(u2)) {
            System.out.println("Os usuários são considerados iguais (mesmo e-mail).");
        } else {
            System.out.println("Os usuários são diferentes.");
        }

        System.out.println("Plataforma: " + Usuario.NOME_PLATAFORMA);
        System.out.println("Total de usuários criados: " + Usuario.getTotalUsuarios());
    }
}
