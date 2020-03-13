package br.first.tte;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//Nome do driver de conex�o do banco de dados (nome padr�o do sql server)
	private final String usuario = "stk_dev";//nome de usu�rio a ser usado para a conex�o (atente-se as permiss�es do usu�rio no banco)
	private final String senha = "Lucas@2019";//senha do usu�rio para a conex�o
	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=IBGETEST";// url da conex�o, padr�o local, construida a partir jdbc:sqlserver:// + serverName\instanceName + ;databaseName=NOMEBANCO
	private Connection con = null;//define conex�o inicial como nula, por ainda n�o ter valor
	
	public Connection getConexao(){
		//estrutura try-catch : permite testarmos codigos e tratarmos as exce��es (Exceptions causadas por ele)
		try {
			Class.forName(driver);//localiza o driver do banco de dados -> necessita ser adicionado na biblioteca do projeto -> bot�o direito no projeto BUILD PATH/CONFIGURE BUILD PATH. Na aba "Libraries" aperte "Add External Jars" se ele j� n�o estiver, eu o coloquei em src/main/resources/sqljdbc, caso n�o seja sql server, adicionar outro driver e confiurar no pom.xml.
			con = (Connection) DriverManager.getConnection(url,usuario,senha);//inicia a conex�o com o banco de acordo com a url,usuario e senha
		} catch (Exception e) {//bloco de captura de exce��es
			System.out.println("\n[ERROR]\nError in DAO\nMessage:"+e.getMessage()+"\n[ERROR]\n");//mostra o log da exception
		}
		return con;//retorna a conex�o a ser usada
	}
}
