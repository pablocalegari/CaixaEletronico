package models;

public class User {
    private String name;
    private String senha;
    private int saldo;

    // 08/04 rafael: talvez eu tire essa agencia depois
    private String agencia;

    public User(String name, String senha, int saldo, String agencia){
        this.name = name;
        this.senha = senha;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    public int getSaldo(){
        return this.saldo;
    }

    public void setSaldo(int saldo){
        this.saldo = saldo;
    }
}