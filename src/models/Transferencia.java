package models;

public class Transferencia {
    private double valor;
    private String destinatarioAgencia;
    private String destinatarioNome;
    private int destinatarioCodBanco;

    public Transferencia(double valor, String destinatarioAgencia, String destinatarioNome, int destinatarioCodBanco){
        this.valor = valor;
        this.destinatarioAgencia = destinatarioAgencia;
        this.destinatarioNome = destinatarioNome;
        this.destinatarioCodBanco = destinatarioCodBanco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDestinatarioAgencia() {
        return destinatarioAgencia;
    }
    public String setDestinatarioAgencia(String destinatarioAgencia){
        return this.destinatarioAgencia = destinatarioAgencia;
    }

    public String getDestinatarioNome() {
        return destinatarioNome;
    }

    public void setDestinatarioNome(String destinatarioNome) {
        this.destinatarioNome = destinatarioNome;
    }

    public int getDestinatarioCodBanco() {
        return destinatarioCodBanco;
    }

    public void setDestinatarioCodBanco(int destinatarioCodBanco) {
        this.destinatarioCodBanco = destinatarioCodBanco;
    }
}
