package org.example.modelo;

public class Moto extends Vehiculo {
    private String Marca;
    private String Modelo;
    private int Cilindrada;

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Moto(Double peso, int capPasajeros) {
        super(peso, capPasajeros);
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public int getCilindrada() {
        return Cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        Cilindrada = cilindrada;
    }

    public Moto(String patente, Double peso, int capPasajeros,String Marca,String Modelo, int Cilindrada) {
        super(patente, peso, capPasajeros);
        this.Cilindrada = Cilindrada;
        this.Modelo = Modelo;
        this.Marca = Marca;

    }

}
