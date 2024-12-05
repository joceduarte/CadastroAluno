package br.edu.unisep.cadastroaluno.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {
    private static final String CAMINHO_ARQUIVO = "alunos.txt";

    public static void salvar(List<Aluno> alunos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Aluno aluno : alunos) {
                writer.write(aluno.toString());
                writer.newLine();
            }
        }
    }

    public static List<Aluno> carregar() throws IOException {
        List<Aluno> alunos = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return alunos;

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                alunos.add(Aluno.fromString(linha));
            }
        }
        return alunos;
    }
}
