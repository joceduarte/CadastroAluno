package br.edu.unisep.cadastroaluno.view;

import br.edu.unisep.cadastroaluno.model.Aluno;
import br.edu.unisep.cadastroaluno.model.ArquivoUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListar extends JFrame {
    private JTable tabela;

    public TelaListar() {
        setTitle("Listar Alunos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] colunas = {"Nome", "Matrícula", "Curso", "Período", "Data de Nascimento"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        carregarAlunos(modeloTabela);

        JButton btnEditar = new JButton("Editar Aluno");
        btnEditar.addActionListener(e -> editarAluno());

        JButton btnExcluir = new JButton("Excluir Aluno");
        btnExcluir.addActionListener(e -> excluirAluno(modeloTabela));

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void carregarAlunos(DefaultTableModel modeloTabela) {
        try {
            List<Aluno> alunos = ArquivoUtils.carregar();
            for (Aluno aluno : alunos) {
                modeloTabela.addRow(new Object[]{
                        aluno.getNome(),
                        aluno.getMatricula(),
                        aluno.getCurso(),
                        aluno.getPeriodo(),
                        aluno.getDataNascimento()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar alunos: " + e.getMessage());
        }
    }

    private void editarAluno() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para editar.");
            return;
        }

        String matricula = tabela.getValueAt(linhaSelecionada, 1).toString();
        new TelaEditar(matricula).setVisible(true);
        dispose();
    }

    private void excluirAluno(DefaultTableModel modeloTabela) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para excluir.");
            return;
        }

        String matricula = tabela.getValueAt(linhaSelecionada, 1).toString();
        try {
            List<Aluno> alunos = ArquivoUtils.carregar();
            alunos.removeIf(aluno -> aluno.getMatricula().equals(matricula));
            ArquivoUtils.salvar(alunos);
            modeloTabela.removeRow(linhaSelecionada);
            JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir aluno: " + e.getMessage());
        }
    }
}
