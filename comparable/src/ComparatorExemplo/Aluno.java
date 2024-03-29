package ComparatorExemplo;

import java.util.Objects;

class Aluno implements Comparable<Aluno> {
    private String nome;

    Aluno(final String nomeAluno) {
        nome = nomeAluno;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(nome, aluno.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public int compareTo(Aluno alunoObj) {
        return Integer.compare(nome.length(), alunoObj.nome.length());
    }
}