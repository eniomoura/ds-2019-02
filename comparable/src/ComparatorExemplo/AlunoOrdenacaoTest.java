package ComparatorExemplo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunoOrdenacaoTest {
    @Test
    void ordenados() {
        List<Aluno> alunos = new ArrayList() {
            @Override //foi feito override do get pois o método de teste tenta comparar um aluno com uma string (?)
            public Object get(int index) {
                Aluno rValue = (Aluno) super.get(index);
                return rValue.getNome();
            }
        };
        alunos.add(new Aluno("Pedro"));
        alunos.add(new Aluno("Amarildo"));
        assertEquals("Pedro", alunos.get(0));
        assertEquals("Amarildo", alunos.get(1));

        Collections.sort(alunos);
        assertEquals("Amarildo", alunos.get(0));
        assertEquals("Pedro", alunos.get(1));
    }

    @Test
    void ordenacaoPeloTamanhoDoNome() {
        List<Aluno> alunos = new ArrayList() {
            @Override //foi feito override do get pois o método de teste tenta comparar um aluno com uma string (?)
            public Object get(int index) {
                Aluno rValue = (Aluno) super.get(index);
                return rValue.getNome();
            }
        };
        alunos.add(new Aluno("Aparecida"));
        alunos.add(new Aluno("Carla"));
        alunos.add(new Aluno("Xuxa"));

        Collections.sort(alunos, Aluno::compareTo);
        assertEquals("Xuxa", alunos.get(0));
        assertEquals("Carla", alunos.get(1));
        assertEquals("Aparecida", alunos.get(2));
    }
}
