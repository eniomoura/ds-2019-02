package AutoCloseExample;

import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.IOException;

class ClasseCriadaTest {
    // FIXME este método não gera IOException
    @org.junit.jupiter.api.Test
    void close() throws IOException {
        // FIXME remova o "try" sem utilidade aqui (observe que não provoca nenhum efeito)
        try {
            Assertions.assertThrows(RuntimeException.class, () -> {
                try(
                    ClasseCriada obj = new ClasseCriada(File.createTempFile("Teste", "Testado"))
                ){}
            });
        }catch (Exception e){
            throw e;
        }
    }
}
