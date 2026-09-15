package main;

import model.Usuario;
import model.Video;

public class MainStreaming {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Carlos Lima", "carlos.lima@email.com", true);

        Video videoValido = new Video("Introdução à POO", 120);
        Video videoInvalido = new Video("Vídeo Corrompido", -45);

        System.out.println(usuario);
        System.out.println("Duração final do 1º vídeo: " + videoValido.getDuracaoMinutos() + " min");
        System.out.println("Duração final do 2º vídeo (era -45): " + videoInvalido.getDuracaoMinutos() + " min");
    }
}
