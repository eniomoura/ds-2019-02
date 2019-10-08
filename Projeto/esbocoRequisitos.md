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
