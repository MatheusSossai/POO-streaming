package model;

public class Video {

    private String titulo;
    private int duracaoMinutos;

    // Questão 04: construtor obrigatório com título e duração
    public Video(String titulo, int duracaoMinutos) {
        this.titulo = titulo;
        setDuracaoMinutos(duracaoMinutos); // reaproveita a validação da Questão 03
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    // Questão 03: duração <= 0 é inválida; sistema ignora e define 1 minuto
    public void setDuracaoMinutos(int duracaoMinutos) {
        if (duracaoMinutos <= 0) {
            this.duracaoMinutos = 1;
        } else {
            this.duracaoMinutos = duracaoMinutos;
        }
    }

    @Override
    public String toString() {
        return "Video{titulo='" + titulo + "', duracaoMinutos=" + duracaoMinutos + "}";
    }
}
