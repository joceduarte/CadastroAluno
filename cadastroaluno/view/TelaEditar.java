package br.edu.unisep.cadastroaluno.view;

import br.edu.unisep.cadastroaluno.model.Aluno;
import br.edu.unisep.cadastroaluno.model.ArquivoUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEditar extends JFrame {
    private JTextField txtNome, txtCurso, txtPeriodo, txtDataNascimento;
    private String matricula;

    public TelaEditar(String matricula) {
        this.matricula = matricula;

        setTitle("Editar Aluno");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        Aluno aluno = carregarAluno();

        add(new JLabel("Nome:"));
        txtNome = new JTextField(aluno.getNome());
        add(txtNome);

        add(new JLabel("Curso:"));
        txtCurso = new JTextField(aluno.getCurso());
        add(txtCurso);

        add(new JLabel("Período:"));
        txtPeriodo = new JTextField(String.valueOf(aluno.getPeriodo()));
        add(txtPeriodo);

        add(new JLabel("Data de Nascimento:"));
        txtDataNascimento = new JTextField(aluno.getDataNascimento());
        add(txtDataNascimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private Aluno carregarAluno() {
        try {
            List<Aluno> alunos = ArquivoUtils.carregar();
            return alunos.stream()
                    .filter(a -> a.getMatricula().equals(matricula))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar aluno: " + e.getMessage());
            dispose();
            throw new RuntimeException(e);
        }
    }

    private void salvarAlteracoes() {
        try {
            List<Aluno> alunos = ArquivoUtils.carregar();
            for (Aluno aluno : alunos) {
                if (aluno.getMatricula().equals(matricula)) {
                    aluno.setNome(txtNome.getText());
                    aluno.setCurso(txtCurso.getText());
                    aluno.setPeriodo(Integer.parseInt(txtPeriodo.getText()));
                    aluno.setDataNascimento(txtDataNascimento.getText());
                }
            }
            ArquivoUtils.salvar(alunos);
            JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar alterações: " + e.getMessage());
        }
    }
}
