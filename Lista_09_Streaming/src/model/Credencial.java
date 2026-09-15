package model;

import java.util.Objects;

public class Credencial {

    private String codigoHex;

    public Credencial(String codigoHex) {
        this.codigoHex = codigoHex;
    }

    public String getCodigoHex() {
        return codigoHex;
    }

    public void setCodigoHex(String codigoHex) {
        this.codigoHex = codigoHex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credencial that = (Credencial) o;
        return Objects.equals(codigoHex, that.codigoHex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoHex);
    }

    @Override
    public String toString() {
        return "Credencial{codigoHex='" + codigoHex + "'}";
    }
}
