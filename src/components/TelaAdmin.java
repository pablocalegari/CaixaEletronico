package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.CaixaEletronico;


public class TelaAdmin extends JFrame {
    private JTextArea display;
    private CaixaEletronico caixaEletronico;

    public TelaAdmin(CaixaEletronico caixa) {
        caixaEletronico = caixa;
        setTitle("Caixa Eletrônico: Painel ADM");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextArea();
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);

        JButton relatorio = new JButton("Relatório Cédulas");
        JButton total = new JButton("Valor Total");
        JButton reposicao = new JButton("Repor Cédulas");
        JButton minimo = new JButton("Cota Minima");
        JButton sair = new JButton("Sair");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BorderLayout(0, 5));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel painelAcoes = new JPanel();
        painelAcoes.setLayout(new GridLayout(2, 2, 5, 5));
        painelAcoes.add(relatorio);
        painelAcoes.add(total);
        painelAcoes.add(reposicao);
        painelAcoes.add(minimo);

        // juntando os paineis na parte de baixo da tela
        painelBotoes.add(painelAcoes, BorderLayout.NORTH);
        painelBotoes.add(sair, BorderLayout.SOUTH);
        add(painelBotoes, BorderLayout.SOUTH);

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // adicionando os metodos da main.CaixaEletronico para os botoes
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
                if (cedula == null) return;

                String quantidade = JOptionPane.showInputDialog("Digite a quantidade de cédulas a ser reposta:");
                if (quantidade == null) return;

                try {
                    String resultadoReposicao = caixaEletronico.reposicaoCedulas(Integer.parseInt(cedula), Integer.parseInt(quantidade));
                    display.setText(resultadoReposicao);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valores de reposição precisam ser numericos!");
                }

            }
        });

        minimo.addActionListener(new ActionListener() {
            // chat pra que serve esse ; na proxima linha to com medo de tirar e quebrar o código
            ;

            public void actionPerformed(ActionEvent e) {
                String valorMinimo = JOptionPane.showInputDialog("Digite a cota mínima para atendimento:");
                //faz com que não de erro ao clicar no cancelar
                if (valorMinimo == null) return;

                //Aparece mensagem de erro se digitar algo que não seja numero
                try {
                    String resultadoMinimo = caixaEletronico.armazenaCotaMinima(Integer.parseInt(valorMinimo));
                    display.setText(resultadoMinimo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "A cota minima deve ser numerica!");
                }
            }
        });
    }
}
