package main;

import Interface.ICaixaEletronico;
import components.Login;
import models.Admin;
import services.SaqueService;
import models.User;

import java.util.HashMap;


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
    private User user;
    private Admin adm;
    private final HashMap<Integer, String> extratoCliente = new HashMap<Integer, String>();
    private final HashMap<Integer, String> extratoAdm = new HashMap<Integer, String>();

    public CaixaEletronico(User user) {
        this.user = user;
    }
    public CaixaEletronico(Admin adm){
        this.adm = adm;
    }

    public HashMap<Integer, String> getExtratoCliente() {
        return extratoCliente;
    }

    public HashMap<Integer, String> getExtratoAdm() {
        return extratoAdm;
    }

    // fazer isso ser apenas para ADMS
    @Override
    public String pegaValorTotalDisponivel(){
        int valorSomado = 0;
        for (int i = 0; i < cedulaRepositorio.length; i++){
            valorSomado += cedulaRepositorio[i][0] * cedulaRepositorio[i][1];
        }
        String extrato = "Verificou valor total disponível no caixa";
        extratoAdm.put(extratoAdm.size() + 1, extrato);
        return "Valor total disponível: " + valorSomado;
    }

    @Override
    public String sacar(int valor) {
        int saldoAtual = user.getSaldo();
        int novoSaldo = saldoAtual - valor;

        if (valor <= 0) {
            return "Invalido: valor de saque não pode ser negativo";
        }
        if (!SaqueService.isSaqueValido(valor)) {
            return "Invalido: tentou sacar cedulas de valor invalido";
        }
        if (saldoAtual < valor) {
            return "Invalido: Valor de saque maior que saldo da conta";
        }

        // Trabalha em cópia para não alterar o repositório caso o saque falhe
        int[] quantidades = new int[cedulaRepositorio.length];
        for (int i = 0; i < cedulaRepositorio.length; i++) {
            quantidades[i] = cedulaRepositorio[i][1];
        }

        int[] notasUsadasSaque = new int[cedulaRepositorio.length]; // conta quantas notas foram usadas
        int valorRestante = sacarRecursivo(valor, 0, quantidades, notasUsadasSaque);

        if (valorRestante != 0) {
            return "Invalido: não é possível realizar o saque com as cédulas disponíveis";
        }

        // Verifica limite de notas antes de confirmar o saque
        int totalNotas = 0;
        for (int n : notasUsadasSaque) totalNotas += n;
        if (totalNotas > 30) {
            return "Invalido: excedeu numero maximo de cedulas para saque";
        }

        String notasUsadas = "Notas entregues:\n"; //vai adicionando as notas usadas

        //aplica as mudanças no repositório real e monta o resultado
        for (int i = 0; i < cedulaRepositorio.length; i++) {
            cedulaRepositorio[i][1] = quantidades[i]; // atualiza a quantidade de cedulas no repositorio
            if (notasUsadasSaque[i] > 0) { //faz com que só adicione notas que foram usadas no print
                notasUsadas += "Nota de R$" + cedulaRepositorio[i][0] + ": " + notasUsadasSaque[i] + "\n";
            }
        }
        String extrato = "Sacou " + "R$" + valor;
        extratoCliente.put(extratoCliente.size() + 1, extrato);

        user.setSaldo(novoSaldo);
        return "Saque efetuado com sucesso!\n" + notasUsadas + "Saldo da conta >> " + user.getSaldo();
    }
    // Metodo auxiliar: tenta resolver o saque com backtracking
    // Vai tentando cada cedula até chegar a 0 usando todas cedulas possiveis
    private int sacarRecursivo(int valorRestante, int indice, int[] quantidades, int[] notasUsadas) {
        if (valorRestante == 0 || indice >= cedulaRepositorio.length) return valorRestante;

        int valorCedula = cedulaRepositorio[indice][0];
        int maxUsavel = Math.min(valorRestante / valorCedula, quantidades[indice]);

        // tenta chegar até 0 usando o backtracking
        for (int qtd = maxUsavel; qtd >= 0; qtd--) {
            quantidades[indice] -= qtd;
            notasUsadas[indice] = qtd;
            int resultado = sacarRecursivo(valorRestante - qtd * valorCedula, indice + 1, quantidades, notasUsadas);
            if (resultado == 0) return 0; // se conseguir resolver finaliza
            quantidades[indice] += qtd; // desfaz e tenta com menos
        }

        notasUsadas[indice] = 0;
        return valorRestante; // não achou solução por este caminho
    }

    // fazer isso ser apenas para ADMS
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
        String extrato = "Verificou relátorio de cédulas";
        extratoAdm.put(extratoAdm.size() + 1, extrato);
        return relatorio.toString();
    }

    @Override
    public String reposicaoCedulas(int cedula, int quantidade){
        for (int i = 0; i < cedulaRepositorio.length; i++) {
            if (cedulaRepositorio[i][0] == cedula) {
                cedulaRepositorio[i][1] += quantidade;
            }
        }
        String extrato = "Repós " + quantidade + " cedulas de R$" + cedula;
        extratoAdm.put(extratoAdm.size() + 1, extrato);
        return "Reposição realizada: " + quantidade + " cedulas de R$" + cedula + " adicionadas.";
    }

    // fazer isso ser apenas para ADMS
    // 25/04 (rafael) - Reverificar isso no blackboard pra ver se ta fazendo oq o professor quer
    @Override
    public String armazenaCotaMinima(int minimo){
        if(minimo < this.cotaMinimaAtendimento){
            return "Caixa Vazio: Chame o Operador";
        }
        return "Cota minima para atendimento: " + this.cotaMinimaAtendimento + " cedulas armazenadas";
    }

    public static void main(String[] args) {
        //System.out.println(sacar());
        Login telaLogin = new Login();
        telaLogin.abrirTelaSetup();
    }
}