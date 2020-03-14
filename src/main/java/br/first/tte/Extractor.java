package br.first.tte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.xml.sax.SAXException;

import br.first.tte.exception.Validator;

public class Extractor {
	
	public static void main(String[] args) {
		startButton("http://api.sidra.ibge.gov.br/values/t/3653/n1/all/v/all/p/201701-201912/h/n");// Passa a URL como parametro
	}
	
	/**Metodo Principal para ser chamado e come�ar todo o processo, recebe a URL da api como parametro*/
	public static void startButton(String url) {
		System.out.println("Start");//log de inicio
		try {
			if (Objects.isNull(url) || "".equalsIgnoreCase(url.trim())) {//verifica se a url � nula ou vazia
				throw new Validator("URL N�o pode ser nula");// joga uma exce��o caso seja
			}
			getDataFromJSONArray(getResponseFromURL(url));// chama os dois metodos respons�veis pela extra��o dos dados
		} catch (Exception e) {
			System.out.println("\n[ERROR]\nError in extractor\nMessage:"+e.getMessage()+"\n[ERROR]\n");// lan�a um log de erro caso ocorra alguma exce��o
		}
	}
	
	/**GET response da URL e transforma em JSON Array
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException */
	public static JSONArray getResponseFromURL(String url) throws IOException, SAXException, ParserConfigurationException {
		
		 URL obj = new URL(url);//insere a String url em um objeto do tipo URL
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();//inicia a conex�o
		 int responseCode = con.getResponseCode();// Recebe o c�digo HTTP da conex�o, ex: 200 = OK, 404 = NOT FOUND
		 System.out.println("Response Code : " + responseCode);// log mostrando a resposta da cone�o
		  BufferedReader in = new BufferedReader(//abre um processo de leitura
			 new InputStreamReader(con.getInputStream(),"UTF-8")); //peg a resposta da conex�o como um input para a leitura, UTF-8 � para n�o ter problemas de acentua��o/ caracteres especiais
			 //Leitura de linhas da resposta
		  	 String inputLine;
			 StringBuffer response = new StringBuffer();
			 while ((inputLine = in.readLine()) != null) {
			   response.append(inputLine);
			 }
			in.close();//fecha a leitura
			JSONArray myResponse = new JSONArray(response.toString());// pega a resposta da conex�o, que � um StringBuffer (linha 52), transforma em um JSONArray (lista de objetos JSON) e o retorna
						
		return myResponse;
	}
	/**Le o dado do JSONArray, pega os dados, o mapea em um objeto e o insere no banco de dados*/
	public static void getDataFromJSONArray(JSONArray jArray) {
		ValoresDescritosDAO valDao = new ValoresDescritosDAO();// Cria um novo Objeto para que seja usado como ponte para o banco
		
		List<ValoresDescritosPorSuasDimensoes> listaObj = new ArrayList<>();// Cria uma lista de Objetos para receber os valores
		for (int i = 0; i < jArray.length(); i++) {// realiza o loop de 0 at� o tamanho do JSONArray (tamanho da resposta da API)
			ValoresDescritosPorSuasDimensoes valor = new ValoresDescritosPorSuasDimensoes();
			//pega o ojeto da posi��o do array e procura o valor da String entre parenteses
			valor.setD2C(jArray.getJSONObject(i).getString("D2C"));
			valor.setD2N(jArray.getJSONObject(i).getString("D2N"));
			valor.setD3C(jArray.getJSONObject(i).getString("D3C"));
			valor.setD3N(jArray.getJSONObject(i).getString("D3N"));
			valor.setD4C(jArray.getJSONObject(i).getString("D4C"));
			valor.setD4N(jArray.getJSONObject(i).getString("D4N"));
			valor.setMC(jArray.getJSONObject(i).getString("MC"));
			valor.setMN(jArray.getJSONObject(i).getString("MN"));
			valor.setV(jArray.getJSONObject(i).getString("V"));
			//System.out.println(valor.toString()); //pequeno log dos dados caso precise
			listaObj.add(valor);// adiciona os Valores pegos � uma nova lista, que ser� usada para inserir no banco
		}
		
		
		for (ValoresDescritosPorSuasDimensoes valoresDescritosPorSuasDimensoes : listaObj) {// realiza outra itera��o (loop), por�m da nova lista criada e insere no banco de dados
				valDao.inserir(valoresDescritosPorSuasDimensoes);	
		}
		System.out.println("Finish");//log de fim
	}
	
	
}
