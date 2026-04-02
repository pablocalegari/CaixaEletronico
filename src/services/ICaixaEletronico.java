package services;

public interface ICaixaEletronico {
    public String pegaValorTotalDisponivel();
    
    public String sacar(int valor);
    
    public String pegaRelatorioCedulas(int[][] cedulas);

    public String reposicaoCedulas(int cedula, int quantidade);
    
    public String armazenaContaMinima();
}
