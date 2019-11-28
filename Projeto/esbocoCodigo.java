import java.io.StringWriter;
import java.util.Dictionary;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/** Classe responsável por implementar métodos que retornam instâncias de {@see Expressao}
 * de acordo com expressões informadas por meio de strings com expressões matemáticas,
 * ou caminhos de arquivos referentes a Bytecodes previamente compilados.
 * 
 * O código abaixo serve apenas para exemplificar o design e não oferece garantia
 * de qualidade, sendo detalhes de construção de software uma etapa posterior do desenvolvimento.
 */
class ConstrutorExpressao {
    /** Método a ser chamado para retornar uma instância de {@see Expressao}, gerando
     * o bytecode cujo caminho será armazenado nessa instância, e gerando o identificador
     * que será posteriormente usado para recuperar essa instância com o método
     * {@link #recuperaExpressao(String)}. Os valores da instância serão inicializados
     * pelo método {@link #novaExpressao(String)}.
     * O caminho do bytecode gerado é definido em {@see #STD_FILE_PATH}.
     * @param expressao A expressão a ser compilada.
     * @return Instância de {@see Expressao} com a expressao compilada no
     * identifier gerado por {@link #geraIdentifier()}
     */

    public Expressao[] cache;

    public Expressao constroiExpressao(String expressao) {
        //Gera um identifier para a expressão existente
        String identifier = geraIdentifier(expressao);
        //Se a expressão existir no cache, retornar a expressão.
        for (Expressao ex : cache) {
            if(ex.identifier == geraIdentifier(expressao)){
                return ex;
            }
        }
        geraBytecode(expressao);
        return novaExpressao(identifier);
    }
    
    /** Método a ser chamado para retornar uma instância de {@see Expressao}, a partir do
     * identificador gerado durante a compilação inicial da expressão.
     * @param identifier O identifier gerado durante a compilação inicial da expressão.
     * @return Instância de {@see Expressao} reconstruída do bytecode recuperado pelo identifier.
     */
    private Expressao recuperaExpressao(String identifier) {
        novaExpressao(identifier);
        return expressaoRecuperada;
    }

    /** Método a ser chamado internamente por essa classe, para fornecer instâncias de
     * {@see Expressao}. Esse método define também o comportamento do identifier gerado para a
     * recuperação.
     * @return Instância de {@see Expressao} reconstruída a partir do identifier.
     */
    private Expressao novaExpressao(String identifier) {
		Expressao novaExpressao = new Expressao();
        novaExpressao.identifier = identifier;
		return novaExpressao;
	}

	private byte[] geraBytecode(String expressao) {
        //É obtida a referência do arquivo gerado contendo a expressão
        File arquivoExpressao = geraArquivoExpressao(expressao);
        /* ...Restante das operações de IO... */

        //O javaCompiler é instanciado
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (
            //São inicializados os recursos, um para obter a classe de expressão a ser compilada, outro para obter
            //a saída da CompilationTask do JavaCompiler.
            StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);
            StringWriter out = new StringWriter();
        ) {
            //É criado um input uma array do tipo JavaFileObject para ser recebida pelo getTask 
            Iterable<? extends JavaFileObject> input =
                    fm.getJavaFileObjects(/*Arquivo contendo a expressão transformada em classe java*/);

            /*
                SOBRE O ARQUIVO JAVA CONTENDO A EXPRESSÃO:
                -----
                O arquivo deverá ter apenas uma classe com o nome do identifier gerado,
                e um método main contendo a expressão.

                A concatenação das strings para a geração desse arquivo será implementada
                no método geraArquivoExpressao() dessa classe.
            
            */
                    

            //É chamada a CompilationTask e convertido o output dela em bytes para serem executados na expressão
            compiler.getTask(out, fm, null, null, null, input).call();
            return out.toString().getBytes();
        }
	}

    /* Gera identifier para cada classe */
    private String geraIdentifier(String expressao){
        return expressao.hashCode();
    }

    /* Gera o boilerplate com a classe com o nome do identifier,
    seguido da declaração da main, concatenada com a expressão executável. */
    private File geraArquivoExpressao(String expressao){}
}

//Essa classe guardará os dados da expressão
class Expressao {
    String identifier;
    byte[] expressaoCompilada;

    //Essa classe executa o método main da expressão convertida em arquivo
    public Double execute(Dictionary<String, Integer> parametros, byte[] b, String classname) {
        DynamicClassLoader loader = new ClassLoader();
        Class<?> clazz = loader.defineClass(classname, b);
        Method method = clazz.getMethod("main", String[].class);
        method.invoke(null, (Object) new String[0]);
    }
}

//Essa classe é um wrapper da ClassLoader exigido para o overwrite de ClassLoader.defineClass
class DynamicClassLoader extends ClassLoader {
    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}