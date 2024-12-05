package br.edu.unisep.cadastroaluno.view;

import javax.swing.*;

public class TelaMenu extends JFrame {
    public TelaMenu() {
        setTitle("Menu Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnCadastro = new JButton("Cadastrar Aluno");
        btnCadastro.setBounds(50, 30, 200, 30);
        btnCadastro.addActionListener(e -> new TelaCadastro().setVisible(true));
        add(btnCadastro);

        JButton btnListar = new JButton("Listar Alunos");
        btnListar.setBounds(50, 70, 200, 30);
        btnListar.addActionListener(e -> new TelaListar().setVisible(true));
        add(btnListar);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(50, 110, 200, 30);
        btnSair.addActionListener(e -> System.exit(0));
        add(btnSair);
    }
}
