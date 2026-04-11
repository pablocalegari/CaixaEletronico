import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.CaixaEletronico;


public class TelaCaixaEletronico extends JFrame {
    private JTextArea display;
    private CaixaEletronico caixaEletronico;

    public TelaCaixaEletronico(CaixaEletronico caixa) {
        caixaEletronico = caixa;
        setTitle("Caixa Eletrônico");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextArea();
        display.setEditable(false);

        add(new JScrollPane(display), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 2, 5, 5)); //espaçamento

        JButton saque = new JButton("Efetuar Saque");
        JButton relatorio = new JButton("Relatório Cédulas");
        JButton total = new JButton("Valor Total");
        JButton reposicao = new JButton("Repor Cédulas");
        JButton minimo = new JButton("Cota Minima");
        JButton sair = new JButton("Sair");
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // adicionando os metodos da main.CaixaEletronico para os botoes

        saque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valorSaque = JOptionPane.showInputDialog("Digite o valor do saque:");
                String resultadoSaque = caixaEletronico.sacar(Integer.parseInt(valorSaque));
                display.setText(resultadoSaque);
            }
        });

        relatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String relatorioCedulas = caixaEletronico.pegaRelatorioCedulas();
                display.setText(relatorioCedulas);
            }
        });

        total.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valorTotal = caixaEletronico.pegaValorTotalDisponivel();
                display.setText(valorTotal);
            }
        });

        reposicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = JOptionPane.showInputDialog("Digite o valor da cédula a ser reposta:");
                String quantidade = JOptionPane.showInputDialog("Digite a quantidade de cédulas a ser reposta:");
                String resultadoReposicao = caixaEletronico.reposicaoCedulas(Integer.parseInt(cedula), Integer.parseInt(quantidade));
                display.setText(resultadoReposicao);
            }
        });

        minimo.addActionListener(new ActionListener() {
            ;

            public void actionPerformed(ActionEvent e) {
                String minimo = JOptionPane.showInputDialog("Digite a cota mínima para atendimento:");
                String resultadoMinimo = caixaEletronico.armazenaCotaMinima(Integer.parseInt(minimo));
                display.setText(resultadoMinimo);
            }
        });


        painelBotoes.add(saque);
        painelBotoes.add(relatorio);
        painelBotoes.add(total);
        painelBotoes.add(reposicao);
        painelBotoes.add(minimo);
        painelBotoes.add(sair);

        add(painelBotoes, BorderLayout.SOUTH);


    }
}
