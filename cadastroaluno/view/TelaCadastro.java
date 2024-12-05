package br.edu.unisep.cadastroaluno.view;

import br.edu.unisep.cadastroaluno.model.Aluno;
import br.edu.unisep.cadastroaluno.model.ArquivoUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCadastro extends JFrame {
    private JTextField txtNome, txtMatricula, txtCurso, txtPeriodo, txtDataNascimento;

    public TelaCadastro() {
        setTitle("Cadastro do Taura");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Curso:"));
        txtCurso = new JTextField();
        add(txtCurso);

        add(new JLabel("Período:"));
        txtPeriodo = new JTextField();
        add(txtPeriodo);

        add(new JLabel("Data de Nascimento:"));
        txtDataNascimento = new JTextField();
        add(txtDataNascimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarAluno());
        add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());
        add(btnVoltar);
    }

    private void salvarAluno() {
        try {
            Aluno aluno = new Aluno(
                    txtNome.getText(),
                    txtMatricula.getText(),
                    txtCurso.getText(),
                    Integer.parseInt(txtPeriodo.getText()),
                    txtDataNascimento.getText()
            );

            List<Aluno> alunos = ArquivoUtils.carregar();
            alunos.add(aluno);
            ArquivoUtils.salvar(alunos);

            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar aluno: " + e.getMessage());
        }
    }
}
