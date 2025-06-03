package org.example.modelo;

public class Vehiculo {
    private String Patente;
    private Double Peso;
    private int CapPasajeros;

    public Vehiculo(Double peso, int capPasajeros) {
        Peso = peso;
        CapPasajeros = capPasajeros;
    }

    public Vehiculo(String patente, Double peso, int capPasajeros) {
        Patente = patente;
        Peso = peso;
        CapPasajeros = capPasajeros;
    }

    public String getPatente() {
        return Patente;
    }

    public void setPatente(String patente) {
        Patente = patente;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double peso) {
        Peso = peso;
    }

    public int getCapPasajeros() {
        return CapPasajeros;
    }

    public void setCapPasajeros(int capPasajeros) {
        CapPasajeros = capPasajeros;
    }
}
