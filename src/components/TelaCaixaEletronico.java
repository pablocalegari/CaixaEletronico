package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.CaixaEletronico;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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
        JButton sair = new JButton("Fechar");
        JButton logOut = new JButton("Log Out");
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> extrato = caixaEletronico.getExtratoCliente();
                StringBuilder textoExtrato = new StringBuilder();
                textoExtrato.append("Extrato\n");

                // verifica se teve alguma ação
                if (extrato == null || extrato.isEmpty()) {
                    textoExtrato.append("Nenhuma operação registrada.");
                } else {
                    // converte o hashmap para um set para poder fazer o for dentro dele
                    for (Map.Entry<String, String> entry : extrato.entrySet()) {
                        // junta numa string a chave do hashmap (getKey()) e o valor da chave (getValue()) divididos por um ":"
                        textoExtrato.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                }

                // Exibe o painel sem bloquear o resto do código (Modal = false)
                JOptionPane pane = new JOptionPane(textoExtrato.toString(), JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = pane.createDialog("Encerrando Sessão...");
                dialog.setModal(false);
                dialog.setVisible(true);

                // Inicia o contador de 5 segundos (5000ms) para fechar a tela
                Timer timer = new Timer(5000, evt -> System.exit(0));
                timer.setRepeats(false);
                timer.start();
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

        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // fecha a tela atual
                Login.abrirTelaSetup(); // volta para a tela de login
            }
        });

        painelBotoes.add(saque);
        painelBotoes.add(sair);
        painelBotoes.add(logOut);

        add(painelBotoes, BorderLayout.SOUTH);


    }
}
