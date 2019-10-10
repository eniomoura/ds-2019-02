É necessário criar um componente de um sistema de registros de diários
que seja capaz de armazenar registros e dados sobre esses registros, de maneira a
permitir que o sistema possa recuperar esses registros por data e palavras-chave.
Cada registro pode ter uma ou mais respostas, e cada resposta será relacionada a uma
pergunta. Cada registro também precisará ser associado a um usuário, identificado por
nome, email e senha.

# Observações (esclarecer)

- Não é claro o que é um "diário" nem tampouco o papel de um "diário" nesta descrição. Estou entendendo que você citou por ser relevante, mas a descrição não descreve o porquê. 
- O que é um registro? É um registro em um SGBD? Se for, então esta é uma restrição de projeto ou apenas um equívoco que introduziu esta decisão de _design_ nos requisitos?
- Os dados sobre os registros são meta-dados? Quais são eles?
- "O sistema deve recuperar registros por data e palavra-chave". Estes são meta-dados ou são dados do registro?
- "Cada registro pode ter uma ou mais respostas, e cada resposta será relacionada a uma pergunta." Isto é parte de um modelo de dados (_design_) já estabelecido? Se for, faz parte de uma restrição de projeto? Também sugere que a funcionalidade aqui atribuída a um módulo seria o acréscimo de uma funcionalidade em um sistema existente (atividade de manutenção). Se for o caso, isto deve ser melhor registrado (descrito) e possivelmente, nem seria um módulo, mas parte de um, ou ainda, talvez partes (no plural) de vários módulos existentes.
- O que é uma resposta? Um texto?
- Cada resposta está associada a uma pergunta. Depois da existência de uma resposta associada a uma pergunta é possível alterar a pergunta? Quais os relacionamentos e as restrições de integridade entre estes elementos? Em tempo, o componente "deve ser capaz de armazenar", então estão faltando detalhes das operações relevantes, se for o caso, pois apenas a busca foi indicada. 
- Deve haver busca pelo usuário ou apenas é uma informação a ser recuperada juntamente com os registros retornados por uma consulta?
- A descrição me parece com a divisão de responsabilidades ou atividades atribuídas a um membro de uma equipe a serem realizadas em uma iteração. Nenhum problema, contudo, se for o caso, há interação com vários _designs_ parciais ou já produzidos, o que dificulta a produção de uma descrição. 
