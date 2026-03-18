import java.io.IOException;
import java.util.Scanner;

// teste commit ignora 

public class Main {
    public static void main(String[] args) {
        // esses 2 comentarios sao codigo para futura feature de login
        //Scanner scanner = new Scanner(System.in);
        //int input = scanner.nextInt();

        while(true){
            try{
                Scanner input = new Scanner(System.in);
                menu();
                int opcao = input.nextInt();

                // preencher if else com as funcoes de cada opcao do menu, usando os services e models criados
                if (opcao == 1) {
                    System.out.println("opcao 1");
                } else if (opcao == 2) {
                    System.out.println("opcao 2");
                } else if (opcao == 3) {
                    System.out.println("opcao 3");
                } else if (opcao == 4) {
                    System.out.println("opcao 4");
                } else if (opcao == 5) {
                    System.out.println(">> Saindo...");
                    break;
                }
            } catch (Exception e){
                System.out.println(">> ERRO | " + e.getMessage());
            }

        }
    }

    public static void menu() {
        System.out.println("========= Caixa Eletrônico =========");

        // interface do caixa eletrônico
        System.out.println("[1] Verificar Saldo");
        System.out.println("[2] Depositar");
        System.out.println("[3] Sacar");
        System.out.println("[4] Transferir");
        System.out.println("[5] Sair");
    }
}
