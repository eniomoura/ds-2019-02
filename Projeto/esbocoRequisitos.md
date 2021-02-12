# Dúvidas que podem te orientar acerca do que fazer...
- O código ilustra alguma coisa que faz uso de JavaCompiler, conforme os requisitos. Ok, você deu uma olhada em JavaCompiler. Contudo, 
  - como é que uma expressão é compilada, por exemplo, "a + b"? A String fornecida como argumento no método **constroiExpressao** nem é empregada pelo método. Naturalmente, "a + b" não é uma sentença válida em Java, simples assim, talvez seja válida em 
  ```java
  public class UmaClasseIdUnicoAqui {
     public double avalie(double a, double b) { return a + b; }
  }
  ```
  Neste último caso, contudo, faria parte do seu _design_ indicar que esta "moldura" será acrescentada automaticamente para cada expressão. Aí você também teria que acrescentar um gerador único para a classe (id único referenciado no nome da classe). Há muitas opções. Observe que para gerar esta "moldura", você teria que analisar a expressão, afinal, como você faria para saber que há os argumentos a e b? Também faz parte do seu _design_. 
  - Vamos supor que está definida uma forma de gerar o código a ser compilado. Neste ponto você já sabe que apenas a expressão não é suficiente. A compilação é problema da classe JavaCompiler, contudo, a classe gerada (bytecodes), precisa ser utilizada. Então será necessário ler (carregar) a classe. Este passo é imprescindível para o desempenho. A solução proposta no código em Java, no qual uma nova instância da JVM é chamada é onerosa e impraticável na perspectiva de desempenho. Mas é uma proposta. 
  - Na proposta de desempenho "baixo", comentada acima, ainda há uma questão não respondida: como passar valor das variáveis, neste exemplo, a e b, para que possam ser empregados na avaliação da expressão? No código não há nenhuma orientação. 

* Avaliação de expressões matemáticas só conhecidas em tempo de execução.
* Posteriormente reutilizada inúmeras vezes, possivelmente ao longo de meses e anos.
* Neste caso, a expectativa é que a expressão possa ser compilada para bytecodes, em tempo de execução, oferecendo um ganho em desempenho.

    -- Criar um módulo (biblioteca, classe) *que gera bytecodes* de operações matemáticas
        Expressao expr = ExpressaoFactory.criaExpressao(string expressao)
        Expressao expr = ExpressaoFactory.recuperaExpressao(string identifier)
    -- Identifier seria, inicialmente, o nome do próprio arquivo .class gerado
    -- Esse bytecode devem ser "executáveis" ao se chamar uma função dentro do módulo criado que teria os valores a serem usados na
    expressão, como um Dictionary ('X' = 4) ('Y' = 3)
        Double resultado = expr.execute(Dictionary<string, int> parametros)
    -- Para gerar o bytecode, deve ser usado o Java Compiler

* Nesta proposta, a classe JavaCompiler, que faz parte do JDK, deve ser utilizada.
