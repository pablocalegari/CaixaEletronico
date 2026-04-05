import services.ICaixaEletronico;
import services.SaqueService;
import java.util.Scanner;
import java.io.IOException;


public class CaixaEletronico implements ICaixaEletronico {
    // isso aqui vai guardar as quantidades das cedulas
    // primeira coluna é o valor a segunda é a quantidade
    // criado como um atributo da classe para todos os metodos poderem usa-lo sem problema
    private int[][] cedulaRepositorio = {{100, 100},
                                        {50, 200},
                                        {20, 300},
                                        {10, 350},
                                        {5, 450},
                                        {2, 500}};
    // atributo para o metodo armazenaContaMinima | numero de teste, vai mudar dps
    private int minimoSaque = 200;

    @Override
    public String pegaValorTotalDisponivel(){
        int valorSomado = 0;
        for (int i = 0; i < cedulaRepositorio.length; i++){
            valorSomado += cedulaRepositorio[i][1];
        }
        return "Valor total disponível: " + valorSomado;
    }

    @Override
    public String sacar(int valor){
        try{

        } catch (Exception e){
            System.out.println("Um erro ocorreu: " + e);
        }

        return "";
    }

    @Override
    public String pegaRelatorioCedulas() {
        // 01/04 - ainda nao testei issai
        // percorre a matriz | 'i' é a linha, 'j' é a coluna
        StringBuilder relatorio = new StringBuilder();
        for (int i = 0; i < cedulaRepositorio.length; i++) {
            // vai printar o valor baseado na linha
            // " linha i, valor na coluna 0, valor na coluna 1"
            String linha = "Valor Cedula: " + cedulaRepositorio[i][0] + " | Quantidade: " + cedulaRepositorio[i][1] + "\n";
            relatorio.append(linha);
        }
        return relatorio.toString();
    }

    @Override
    public String reposicaoCedulas(int cedula, int quantidade){
        return "";
    }

    @Override
    public String armazenaContaMinima(int minimo){
        // verifica se o valor passado é menor que zero
        if (minimo < 0){
            return "Valor minimo de saque não pode ser negativo.";
        }

        // se nao for, atualiza o valor do minimoSaque e retorna uma mensagem de sucesso
        this.minimoSaque = minimo;
        return "Valor mínimo de saque atualizado para: " + minimo;
    }
    
    
    public void main(String[] args) {

    }
    
    
    /*public static void menu() {
        System.out.println("========= Caixa Eletrônico =========");

        // interface do caixa eletrônico
        System.out.println("[1] Verificar Saldo");
        System.out.println("[2] Depositar");
        System.out.println("[3] Sacar");
        System.out.println("[4] Transferir");
        System.out.println("[5] Sair");
        }
        */
        
}
