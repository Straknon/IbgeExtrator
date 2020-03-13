package br.first.tte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValoresDescritosDAO  extends DAO{//Filha da classe DAO, logo herda as configurações
	
	/** Metodo para inserir dados da Classe ValoresDescritos... no banco de dados*/
	public void inserir(ValoresDescritosPorSuasDimensoes valor){
		Connection con = getConexao();//inicia a conexão
		String sql ="insert into dbo.TB001_PIM_ATV_tabular (D2C,D2N,D3C,D3N,D4C,D4N,MC,MN,V)" + "values(?,?,?,?,?,?,?,?,?)";//sql a ser executado, nome da tabela de acordo com o texto, "dbo" é o schema padrão do sql server
		try {
			PreparedStatement ps = con.prepareStatement(sql);// Prepara o sql para a substituição das "?" pelos valores
			ps.setString(1, valor.getD2C());//ps.setString insere o valor como String -> parametros: 1 posição, 2 valor
			ps.setString(2, valor.getD2N());
            ps.setString(3, valor.getD3C());
            ps.setString(4, valor.getD3N());
            ps.setString(5, valor.getD4C());
            ps.setString(6, valor.getD4N());
            ps.setString(7, valor.getMC());
            ps.setString(8, valor.getMN());
            ps.setString(9, valor.getV());
            
			ps.executeUpdate();//após a insersão de valores executa o sql
		} catch (SQLException e) {
			System.out.println("\n[ERROR]\nError in ValoresDescritosDAO\nMessage:"+e.getMessage()+"\n[ERROR]\n");//mostra o log da exception
		}
		
	}
}
