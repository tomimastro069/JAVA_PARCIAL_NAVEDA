package org.example.modelo;

public class Auto extends Vehiculo {
    private String Marca;
    private String Modelo;
    private int Puertas;

    public Auto(Double peso, int capPasajeros) {
        super(peso, capPasajeros);
    }

    public Auto(Double peso, int capPasajeros, String marca, String modelo, int puertas) {
        super(peso, capPasajeros);
        Marca = marca;
        Modelo = modelo;
        Puertas = puertas;
    }

    public Auto(String patente, Double peso, int capPasajeros, String marca, String modelo, int puertas) {
        super(patente, peso, capPasajeros);
        Marca = marca;
        Modelo = modelo;
        Puertas = puertas;
    }

    public Auto(String patente, Double peso, int capPasajeros) {
        super(patente, peso, capPasajeros);
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getPuertas() {
        return Puertas;
    }

    public void setPuertas(int puertas) {
        Puertas = puertas;
    }
}
