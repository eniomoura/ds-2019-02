import java.util.Dictionary;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

class ConstrutorExpressao {
    private final String standardFilePath = "./";


    //PUBLIC METHODS

    public Expressao constroiExpressao(String expressao, String filepath) {
        geraBytecode(filepath);
        return novaExpressao(filepath);
    }

    public Expressao constroiExpressao(String expressao) {
        String filepath = standardFilePath;
        geraBytecode(filepath);
        return novaExpressao(filepath);
    }

    public Expressao recuperaExpressao(String identifier) {
        novaExpressao(identifier);
        return expressaoRecuperada;
    }

    //PRIVATE METHODS

    private Expressao novaExpressao(String filepath) {
		Expressao novaExpressao = new Expressao();
        novaExpressao.identifier = filepath;
        novaExpressao.classFilePath = filepath;
		return novaExpressao;
	}

	private ConstrutorExpressao geraBytecode(String filepath) { //stub
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (   StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);
                StringWriter out = new StringWriter();
                PrintWriter outWriter = new PrintWriter(out)) {
            Iterable<? extends JavaFileObject> input = fm.getJavaFileObjects(System.getProperty(filepath));
            List<String> options = null; // ?
            
            compiler.getTask(outWriter, fm, null, options, null, input).call();
        }
        return this;
	}
}

class Expressao {

    String identifier;
    String classFilePath;

    public Double execute(Dictionary<String, Integer> parametros) {
        return runProcess("java " + classFilePath);
    }
}