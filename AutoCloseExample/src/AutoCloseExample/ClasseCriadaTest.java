package AutoCloseExample;

import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.IOException;

class ClasseCriadaTest {
    @org.junit.jupiter.api.Test
    void close() throws IOException {
        try {
            ClasseCriada classeCriada = new ClasseCriada(File.createTempFile("teste", "Testado"));
            Assertions.assertThrows(RuntimeException.class, () -> {
                classeCriada.close();
            });
        }catch (Exception e){
            throw e;
        }
    }
}