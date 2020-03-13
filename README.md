# FD Browser - TTE Project

### Informações gerais

Language: Java - 1.8 jdk </br>
Repository: Maven

### Guia

Configurar o banco de dados com as variaveis: D2C,D2N,D3C,D3N,D4C,D4N,MC,MN,V em formato varchar, caso queira mudar o tipo de dado, terá que mudar no java também.
Importe o projeto no Eclipse.
Mudar as configurações da classe DAO, de acordo com o seu banco, caso não seja Sql Server, lembre-se de importar no maven o driver do seu banco de dados.

Executar no eclipse
-

Em Extrator clique com o botão direito na página e `Run as/ Java Application`, lembre-se de ter instalado e configurado o jdk1.8

Build
-

No projeto no menu lateral clique com o botão direito e vá em `Run as/Maven Build...` aparecerá para mudar as configurações de execução, em "GOALS" coloque "package" e pode rodar, caso de algum problema na build, tente "clean package", lembre-se de que necessita estar configurado para usar o jdk1.8 no eclipse.

A build fica na pasta target, para executar, abra o cmd na pasta da build e execute o comando com java -jar nomeArquivo.jar (use o com dependencias), lembre-se de ter o java configurado nas variaveis de ambiente do windows (colocar o diretorio do bin do jdk 1.8 em "Path").

Configurando jdk1.8 eclipse
-

Após ter baixado e instalado o jdk, vá no eclipse botão direito no projeto `Build path/Configure build path ` clique em "JRE System Library" e clique em "Edit", selecione `Alternate JRE/Installed JREs/Add` coloque o diretorio da pasta jdk. Ainda nas propriedades do projeto, "Properties", verifique o "Java Compiler" se está para jdk 1.8.
