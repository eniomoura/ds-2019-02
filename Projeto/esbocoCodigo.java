import java.util.Dictionary;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/** Classe responsável por implementar métodos que retornam instâncias de {@see Expressao}
 * de acordo com expressões informadas por meio de strings com expressões matemáticas,
 * ou caminhos de arquivos referentes a Bytecodes previamente compilados.
 */
class ConstrutorExpressao {

    /** Constante de caminho padrão a ser definido no código para a criação dos Bytecodes caso
     * não seja provido um por parâmetro na chamada de {@link #constroiExpressao(String)}.
    */
    private final String STD_FILE_PATH = "./";

    /** Método a ser chamado para retornar uma instância de {@see Expressao}, gerando
     * o bytecode cujo caminho será armazenado nessa instância, e gerando o identificador
     * que será posteriormente usado para recuperar essa instância com o método
     * {@link #recuperaExpressao(String)}. Os valores da instância serão inicializados
     * pelo método {@link #novaExpressao(String)}.
     * O caminho do bytecode gerado é definido em {@see #STD_FILE_PATH}.
     * @param expressao A expressão a ser compilada.
     * @return Instância de {@see Expressao} com a expressao compilada no filepath default.
     */
    public Expressao constroiExpressao(String expressao) {
        String filepath = STD_FILE_PATH;
        geraBytecode(filepath);
        return novaExpressao(filepath);
    }

    /** Método a ser chamado para retornar uma instância de {@see Expressao}, gerando
     * o bytecode cujo caminho será armazenado nessa instância, e gerando o identificador
     * que será posteriormente usado para recuperar essa instância com o método
     * {@link #recuperaExpressao(String)}. Os valores da instância serão inicializados
     * pelo método {@link #novaExpressao(String)}.
     * @param filepath O caminho onde o arquivo bytecode será gerado na compilação.
     * @param expressao A expressão a ser compilada.
     * @return Instância de {@see Expressao} com a expressao compilada no filepath alvo.
     */
    public Expressao constroiExpressao(String expressao, String filepath) {
        geraBytecode(filepath);
        return novaExpressao(filepath);
    }
    
    /** Método a ser chamado para retornar uma instância de {@see Expressao}, a partir do
     * identificador gerado durante a compilação inicial da expressão.
     * @param identifier O identifier gerado durante a compilação inicial da expressão.
     * @return Instância de {@see Expressao} reconstruída do bytecode recuperado pelo identifier.
     */
    public Expressao recuperaExpressao(String identifier) {
        novaExpressao(identifier);
        return expressaoRecuperada;
    }


    /** Método a ser chamado internamente por essa classe, para fornecer instâncias de
     * {@see Expressao} inicializadas com base no filepath do bytecode gerado para os métodos
     * dessa classe. Esse método define também o comportamento do identifier gerado para a
     * recuperação - O identifier pode ser o próprio filepath.
     * @param filepath O filepath do bytecode já gerado para a expressão.
     * @return Instância de {@see Expressao} reconstruída a 
     */
    private Expressao novaExpressao(String filepath) {
		Expressao novaExpressao = new Expressao();
        novaExpressao.identifier = filepath;
        novaExpressao.classFilePath = filepath;
		return novaExpressao;
	}


	private ConstrutorExpressao geraBytecode(String filepath) { //stub - CORRIGIR - input está incorreto
        completaCodigo()
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