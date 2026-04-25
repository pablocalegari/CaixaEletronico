package models;

public class User {
    private String name = "Usuario Teste";
    private String senha = "123456";
    private int saldo = 10000;

    // 08/04 rafael: talvez eu tire essa agencia depois
    private String agencia = "0001";

    public User(){
    }

    public int getSaldo(){
        return this.saldo;
    }

    public void setSaldo(int saldo){
        this.saldo = saldo;
    }
}