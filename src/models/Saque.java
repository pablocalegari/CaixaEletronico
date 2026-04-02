package models;

public class Saque {
    private double valor;

    public Saque(){}
    public Saque (double valor){
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
