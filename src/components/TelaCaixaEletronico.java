package components;

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
        painelBotoes.setLayout(new GridLayout(2, 2, 5, 5)); //espaçamento

        JButton saque = new JButton("Efetuar Saque");
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

                //faz com que não de erro ao clicar no cancelar
                if (valorSaque == null) return;

                //Aparece mensagem de erro se digitar algo que não seja numero
                try {
                    int valor = Integer.parseInt(valorSaque.trim());
                    String resultadoSaque = caixaEletronico.sacar(valor);
                    display.setText(resultadoSaque);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O saque deve ser numérico!");
                }
            }
        });

        painelBotoes.add(saque);
        painelBotoes.add(sair);

        add(painelBotoes, BorderLayout.SOUTH);


    }
}
