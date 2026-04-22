package components;

import main.CaixaEletronico;
import models.User;
import models.Admin;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;


public class Login {
    public static void abrirTelaSetup() {
        JFrame telaSetup = new JFrame("Caixa Eletronico: Log In");
        telaSetup.setSize(350, 300);
        telaSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaSetup.setLayout(new BorderLayout(10, 10));

        // 18/04 - Rafael
        // adicionando paineis para organizar onde ficam os campos de info
        // esses paineis sao apenas sessoes separadas, o Grid era uma sessao só e ficava ruim para editar o layout da tela
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelNome = new JLabel(" Nome:");
        JTextField campoNome = new JTextField();

        JLabel labelSenha = new JLabel(" Senha:");
        JPasswordField campoSenha = new JPasswordField();

        JLabel labelSaldo = new JLabel(" Saldo Inicial (R$):");
        JTextField campoSaldo = new JTextField();

        JLabel labelAgencia = new JLabel(" Agência:");
        JTextField campoAgencia = new JTextField();

        painelCampos.add(labelNome);
        painelCampos.add(campoNome);
        painelCampos.add(labelSenha);
        painelCampos.add(campoSenha);
        painelCampos.add(labelSaldo);
        painelCampos.add(campoSaldo);
        painelCampos.add(labelAgencia);
        painelCampos.add(campoAgencia);

        // painel dos campos
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 0, 5));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton btnConfirmar = new JButton("Entrar como Usuario");
        JButton btnLogInAdm = new JButton("Entrar como Admin");

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnLogInAdm);

        // organiza ambos paineis, deixando o dos campos de info em cima e o de botao em baixo
        telaSetup.add(painelCampos, BorderLayout.CENTER);
        telaSetup.add(painelBotoes, BorderLayout.SOUTH);

        btnLogInAdm.addActionListener(e -> {
            abrirTelaLoginAdmin(telaSetup);
        });

        // logica para log in como usuario comum
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

    // tela de login para adms
    private static void abrirTelaLoginAdmin(JFrame telaLogin) {
        JDialog telaAdmin = new JDialog(telaLogin, "Login do Administrador", true);
        telaAdmin.setSize(300, 150);
        telaAdmin.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel labelNomeAdm = new JLabel(" Nome Admin:");
        JTextField campoNomeAdm = new JTextField();

        JLabel labelSenhaAdm = new JLabel(" Senha:");
        JPasswordField campoSenhaAdm = new JPasswordField();

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCancelar = new JButton("Cancelar");

        telaAdmin.add(labelNomeAdm);
        telaAdmin.add(campoNomeAdm);
        telaAdmin.add(labelSenhaAdm);
        telaAdmin.add(campoSenhaAdm);
        telaAdmin.add(btnEntrar);
        telaAdmin.add(btnCancelar);

        btnCancelar.addActionListener(e -> telaAdmin.dispose());

        btnEntrar.addActionListener(e -> {
            try{
                String nome = campoNomeAdm.getText();
                String senha = new String(campoSenhaAdm.getPassword());

                if(nome.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(telaAdmin, "Preencha nome e senha!");
                    return;
                }

                if (nome.equals(Admin.getName()) && senha.equals(Admin.getPassword())){
                    JOptionPane.showMessageDialog(telaAdmin, "Bem-vindo, Administrador!");

                    Admin adm = new Admin();
                    CaixaEletronico caixa = new CaixaEletronico(adm);

                    TelaAdmin telaPrincipal = new TelaAdmin(caixa);
                    telaPrincipal.setLocationRelativeTo(null);
                    telaPrincipal.setVisible(true);
                    telaLogin.dispose();
                    telaAdmin.dispose();
                } else {
                    JOptionPane.showMessageDialog(telaAdmin, "Nome ou senha incorretos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);

                    //limpa o campo de senha para o usuário tentar de novo
                    campoSenhaAdm.setText("");
                }
            } catch (Exception ex){
                JOptionPane.showMessageDialog(telaAdmin, "Algo deu errado, tente novamente");
                return;
            }
        });

        telaAdmin.setLocationRelativeTo(telaLogin); // Centraliza em relação a tela principal
        telaAdmin.setVisible(true);
    }
}
