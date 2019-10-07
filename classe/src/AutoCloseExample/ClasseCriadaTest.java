package AutoCloseExample;

import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.IOException;

class ClasseCriadaTest {
    @org.junit.jupiter.api.Test
    void close() throws IOException {
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