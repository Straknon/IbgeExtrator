package br.first.tte;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//Nome do driver de conexão do banco de dados (nome padrão do sql server)
	private final String usuario = "stk_dev";//nome de usuário a ser usado para a conexão (atente-se as permissões do usuário no banco)
	private final String senha = "Lucas@2019";//senha do usuário para a conexão
	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=IBGETEST";// url da conexão, padrão local, construida a partir jdbc:sqlserver:// + serverName\instanceName + ;databaseName=NOMEBANCO
	private Connection con = null;//define conexão inicial como nula, por ainda não ter valor
	
	public Connection getConexao(){
		//estrutura try-catch : permite testarmos codigos e tratarmos as exceções (Exceptions causadas por ele)
		try {
			Class.forName(driver);//localiza o driver do banco de dados -> necessita ser adicionado na biblioteca do projeto -> botão direito no projeto BUILD PATH/CONFIGURE BUILD PATH. Na aba "Libraries" aperte "Add External Jars" se ele já não estiver, eu o coloquei em src/main/resources/sqljdbc, caso não seja sql server, adicionar outro driver e confiurar no pom.xml.
			con = (Connection) DriverManager.getConnection(url,usuario,senha);//inicia a conexão com o banco de acordo com a url,usuario e senha
		} catch (Exception e) {//bloco de captura de exceções
			System.out.println("\n[ERROR]\nError in DAO\nMessage:"+e.getMessage()+"\n[ERROR]\n");//mostra o log da exception
		}
		return con;//retorna a conexão a ser usada
	}
}
