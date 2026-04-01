import java.io.IOException;
import java.util.Scanner;

public class CaixaEletronico implements ICaixaEletronico {
    @Override
    public String pegaValorTotalDisponivel(){}
    
    @Override
    public String sacar(int valor){
        try{
            
        } catch (Exception e){
            System.out.println("Um erro ocorreu: " + e);
        }
    }
    
    @Override
    public String pegaRelatorioCedulas(int[][] cedulas){
        // 01/04 - ainda nao testei issai
        // percorre a matriz | 'i' é a linha, 'j' é a coluna
        String relatorio;
        for(int i = 0; i < 6; i++){
            // vai printar o valor baseado na linha
            // " linha i, valor na coluna 0, valor na coluna 1"
            String linha = System.out.println("Valor Cedula: " + cedulas[i][0] + " | Quantidade: " + cedulas[i][1] + "\n");
            relatorio.append(linha);
        }
        return relatorio.toString();
    }
    
    @Override
    public String reposicaoCedulas(int cedula, int quantidade){}
    
    @Override
    public String armazenaContaMinima(){}
    
    
    public static void main(String[] args) {
        // isso aqui vai guardar as quantidades das cedulas
        // primeira coluna é o valor a segunda é a quantidade
        int[][] cedulaRepositorio = {{100, 100}, 
                                    {50, 200}, 
                                    {20, 300}, 
                                    {10, 350}, 
                                    {5, 450}, 
                                    {2, 500}};
                                    
        
        pegaRelatorioCedulas(cedulaRepositorio);
    }
    
    
    /*public static void menu() {
        System.out.println("========= Caixa Eletrônico =========");

        // interface do caixa eletrônico
        System.out.println("[1] Verificar Saldo");
        System.out.println("[2] Depositar");
        System.out.println("[3] Sacar");
        System.out.println("[4] Transferir");
        System.out.println("[5] Sair");
        }*/
        
}
