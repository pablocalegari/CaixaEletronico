package Interface;

public interface ICaixaEletronico {
    public String pegaValorTotalDisponivel();
    
    public String sacar(int valor);
    
    public String pegaRelatorioCedulas();

    public String reposicaoCedulas(int cedula, int quantidade);
    
    public String armazenaCotaMinima(int minimo);
}
