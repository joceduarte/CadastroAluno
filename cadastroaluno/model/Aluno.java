package br.edu.unisep.cadastroaluno.model;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private int periodo;
    private String dataNascimento;

    public Aluno(String nome, String matricula, String curso, int periodo, String dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.periodo = periodo;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    @Override
    public String toString() {
        return nome + "," + matricula + "," + curso + "," + periodo + "," + dataNascimento;
    }

    public static Aluno fromString(String linha) {
        String[] partes = linha.split(",");
        return new Aluno(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]), partes[4]);
    }
}
