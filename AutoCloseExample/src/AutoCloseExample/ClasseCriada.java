// FIXME nome fora do padrão deveria ser algo como com.github.eniomoura.autoclose, por exemplo
package AutoCloseExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClasseCriada extends FileInputStream {
    public ClasseCriada(File file) throws FileNotFoundException {
        super(file);
    }

    // FIXME este método não gera IOException
    @Override
    public void close() throws IOException {
        throw new RuntimeException("método close chamado");
    }
}
