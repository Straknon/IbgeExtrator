# FD Browser - TTE Project

### Informações gerais

Language: Java - 1.8 jdk </br>
Platform: JavaFX - 0.0.3 openfx

### Informações de uso

Ao abrir o programa, localizado na pasta *"exeSample"*, aparecerá 3 botões e 2 campos.
- O botão *"GO"* é utilizado para a pesquisa do campo da *url*.
-  O botão *"ONE"* é utilizado para a transformação da tabela da página atual em excel.
-  O botão *"ALL"* é utilizado para a transformação de todas as tabelas do "sigct" em uma tabela excel, funciona somente na página de demandas e pega somente os dados das "ufs" mapeadas no código, pela falta de uma *"API"* do sigct, o programa foi feito simulando os passos de uma pessoa, funciona como um *"bot"*, demorará alguns minutos para terminar de executar.
- O campo maior é destinado para busca de *url*.
- O campo menor, é o tempo em segundos de intervalo entre cada iteração do botão *"ALL"*, ao apertar o botão, ele somente pegará a próxima operação após esse tempo, aconselhado deixar entre 4 e 6 segundos.


**Importante: O  arquivo será gerado na mesma pasta do ".exe" e substituira caso tenha um arquivo com o mesmo nome e extensão, aconselhável apertar o botão *"ALL"* somente uma vez, ele fará o trabalho todo sozinho, é aconselhável olhar o log e a página para ver se está ocorrendo tudo corretamente, espere a mensagem de log *"FINISH"* para fechar o programa.
Observações: Se o botão *"ALL"* for pressionado mais de uma vez, ele fará o processo quantas vezes for pressionado, o excel só começa a ser gerado após ter pego todas as tabelas, aparecerá uma mensagem no log.**

