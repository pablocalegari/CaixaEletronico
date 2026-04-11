package components;

import main.CaixaEletronico;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Login {
    public static void abrirTelaSetup() {
        JFrame telaSetup = new JFrame("Configuração Inicial da Conta");
        telaSetup.setSize(350, 300);
        telaSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaSetup.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel labelNome = new JLabel(" Nome:");
        JTextField campoNome = new JTextField();

        JLabel labelSenha = new JLabel(" Senha:");
        JPasswordField campoSenha = new JPasswordField();

        JLabel labelSaldo = new JLabel(" Saldo Inicial (R$):");
        JTextField campoSaldo = new JTextField();

        JLabel labelAgencia = new JLabel(" Agência:");
        JTextField campoAgencia = new JTextField();

        JButton btnConfirmar = new JButton("Criar Conta e Entrar");

        telaSetup.add(labelNome);
        telaSetup.add(campoNome);
        telaSetup.add(labelSenha);
        telaSetup.add(campoSenha);
        telaSetup.add(labelSaldo);
        telaSetup.add(campoSaldo);
        telaSetup.add(labelAgencia);
        telaSetup.add(campoAgencia);
        telaSetup.add(new JLabel("")); // Espaço vazio para alinhar
        telaSetup.add(btnConfirmar);

        btnConfirmar.addActionListener(e -> {
            try {
                String nome = campoNome.getText();
                String senha = new String(campoSenha.getPassword());
                String agencia = campoAgencia.getText();
                String saldoTexto = campoSaldo.getText();

                if(nome.isEmpty() || senha.isEmpty() || agencia.isEmpty() || saldoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(telaSetup, "Preencha todos os campos!");
                    return;
                }

                int saldoInicial = Integer.parseInt(saldoTexto);

                // 1. Cria o usuário com os dados da tela
                User usuario = new User(nome, senha, saldoInicial, agencia);

                // 2. Cria o caixa com esse usuário
                CaixaEletronico caixa = new CaixaEletronico(usuario);

                // 3. Abre a sua tela principal, passando o caixa pronto para ela!
                TelaCaixaEletronico telaPrincipal = new TelaCaixaEletronico(caixa);
                telaPrincipal.setLocationRelativeTo(null);
                telaPrincipal.setVisible(true);

                // 4. Fecha a tela de setup
                telaSetup.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(telaSetup, "O saldo deve ser numérico!");
            }
        });

        telaSetup.setLocationRelativeTo(null);
        telaSetup.setVisible(true);
    }
}
