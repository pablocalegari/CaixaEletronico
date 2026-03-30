import java.io.IOException;
import java.util.Scanner;

public class CaixaEletronico implements ICaixaEletronico {
    @Override
    public String pegaValorTotalDisponivel(){}
    
    @Override
    public String sacar(int valor){}
    
    @Override
    public String pegaRelatorioCedulas(){
        int cedulaRepositorio = new int[6][2];
    }
    
    @Override
    public String reposicaoCedulas(int cedula, int quantidade){}
    
    @Override
    public String armazenaContaMinima(){}
    
    
    public static void main(String[] args) {
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
