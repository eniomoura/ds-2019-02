package AutoCloseExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClasseCriada extends FileInputStream {
    public ClasseCriada(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public void close() throws IOException {
        throw new RuntimeException("método close chamado");
    }
}
