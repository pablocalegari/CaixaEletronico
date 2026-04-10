import services.ICaixaEletronico;
import services.SaqueService;
import models.User;
import java.util.Scanner;
import java.io.IOException;


public class CaixaEletronico implements ICaixaEletronico {
    // primeira coluna é o valor a segunda é a quantidade
    // criado como um atributo da classe para todos os metodos poderem usa-lo sem problema
    private int[][] cedulaRepositorio = {{100, 100},
                                        {50, 200},
                                        {20, 300},
                                        {10, 350},
                                        {5, 450},
                                        {2, 500}};
    // atributo para o metodo armazenaContaMinima | numero de teste, vai mudar dps
    private int cotaMinimaAtendimento = 200;

    // instanciando o usuario para testes, tem que arrumar um jeito de isso poder ser criado dentro do main, e que possa ser usado dentro dos métodos sem passar como argumento
    User user = new User("Rafael", "1234", 1000, "0001");

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
        int saldoAtual = user.getSaldo();
        int novoSaldo =  saldoAtual - valor;

        if (valor < 0){
            return "Invalido: valor de saque não pode ser negativo";
        }
        if (!SaqueService.isSaqueValido(valor)){
            return "Invalido: tentou sacar cedulas de valor invalido";
        }
        if (saldoAtual < valor){
            return "Invalido: Valor de saque maior que saldo da conta";
        }

        try{
            for(int i = 0; i < cedulaRepositorio.length; i++){
                int valorCedula = cedulaRepositorio[i][0];
                int quantidadeCedula = cedulaRepositorio[i][1];

                // enquanto o valor do saque for maior ou igual ao valor da cedula e houver cedulas disponiveis
                while (valor >= valorCedula && quantidadeCedula > 0){
                    valor -= valorCedula; // subtrai o valor da cedula do valor do saque
                    quantidadeCedula--; // diminui a quantidade de cedulas disponiveis
                    cedulaRepositorio[i][1] = quantidadeCedula; // atualiza a quantidade de cedulas no repositorio
                }

                user.setSaldo(novoSaldo);
            }
        } catch (Exception e){
            System.out.println("Um erro ocorreu: " + e);
        }
        return "Saque efetuado com sucesso! Saldo da conta >> " + user.getSaldo();
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
    public String armazenaCotaMinima(int minimo){
        if(minimo < this.cotaMinimaAtendimento){
            return "Caixa Vazio: Chame o Operador";
        }
        return "Cota minima para atendimento: " + this.cotaMinimaAtendimento + " cedulas armazenadas";
    }

    public void main(String[] args) {
        //System.out.println(sacar());

    }
}
