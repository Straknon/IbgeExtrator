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
		startButton("http://api.sidra.ibge.gov.br/values/t/3653/n1/all/v/all/p/all/h/n");// Passa a URL como parametro
	}
	
	/**Metodo Principal para ser chamado e começar todo o processo, recebe a URL da api como parametro*/
	public static void startButton(String url) {
		System.out.println("Start");//log de inicio
		try {
			if (Objects.isNull(url) || "".equalsIgnoreCase(url.trim())) {//verifica se a url é nula ou vazia
				throw new Validator("URL Não pode ser nula");// joga uma exceção caso seja
			}
			getDataFromJSONArray(getResponseFromURL(url));// chama os dois metodos responsáveis pela extração dos dados
		} catch (Exception e) {
			System.out.println("\n[ERROR]\nError in extractor\nMessage:"+e.getMessage()+"\n[ERROR]\n");// lança um log de erro caso ocorra alguma exceção
		}
	}
	
	/**GET response da URL e transforma em JSON Array
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException */
	public static JSONArray getResponseFromURL(String url) throws IOException, SAXException, ParserConfigurationException {
		
		 URL obj = new URL(url);//insere a String url em um objeto do tipo URL
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();//inicia a conexão
		 int responseCode = con.getResponseCode();// Recebe o código HTTP da conexão, ex: 200 = OK, 404 = NOT FOUND
		 System.out.println("Response Code : " + responseCode);// log mostrando a resposta da coneão
		  BufferedReader in = new BufferedReader(//abre um processo de leitura
			 new InputStreamReader(con.getInputStream(),"UTF-8")); //peg a resposta da conexão como um input para a leitura, UTF-8 é para não ter problemas de acentuação/ caracteres especiais
			 //Leitura de linhas da resposta
		  	 String inputLine;
			 StringBuffer response = new StringBuffer();
			 while ((inputLine = in.readLine()) != null) {
			   response.append(inputLine);
			 }
			in.close();//fecha a leitura
			JSONArray myResponse = new JSONArray(response.toString());// pega a resposta da conexão, que é um StringBuffer (linha 52), transforma em um JSONArray (lista de objetos JSON) e o retorna
						
		return myResponse;
	}
	/**Le o dado do JSONArray, pega os dados, o mapea em um objeto e o insere no banco de dados*/
	public static void getDataFromJSONArray(JSONArray jArray) {
		ValoresDescritosDAO valDao = new ValoresDescritosDAO();// Cria um novo Objeto para que seja usado como ponte para o banco
		
		List<ValoresDescritosPorSuasDimensoes> listaObj = new ArrayList<>();// Cria uma lista de Objetos para receber os valores
		for (int i = 0; i < jArray.length(); i++) {// realiza o loop de 0 até o tamanho do JSONArray (tamanho da resposta da API)
			ValoresDescritosPorSuasDimensoes valor = new ValoresDescritosPorSuasDimensoes();
			//pega o ojeto da posição do array e procura o valor da String entre parenteses
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
			listaObj.add(valor);// adiciona os Valores pegos à uma nova lista, que será usada para inserir no banco
		}
		
		
		for (ValoresDescritosPorSuasDimensoes valoresDescritosPorSuasDimensoes : listaObj) {// realiza outra iteração (loop), porém da nova lista criada e insere no banco de dados
			if(valoresDescritosPorSuasDimensoes.getD3N().contains("2017")||valoresDescritosPorSuasDimensoes.getD3N().contains("2018")||valoresDescritosPorSuasDimensoes.getD3N().contains("2019")) {
				valDao.inserir(valoresDescritosPorSuasDimensoes);	
			}
			
		}
		System.out.println("Finish");//log de fim
	}
	
	
	
	/*
	public static void StringToArray(String source) {
	    List<ArrayList<String>> colunas = new ArrayList<ArrayList<String>>();
	    Document doc = Jsoup.parse(source);
	    System.out.println(doc.toString());
	    for (Element table : doc.select("ArrayOfValorDescritoPorSuasDimensoes")) {
	        for (Element row : table.select("ValorDescritoPorSuasDimensoes")) {
	        	if(!row.select("ValorDescritoPorSuasDimensoes").hasText()) {
		    		continue;
		    	}
	        	ArrayList<String> linhas = new ArrayList<String>();	        	
	        	linhas.add(row.select("D2C").get(0).text());
	        	linhas.add(row.select("D2N").get(0).text());
	        	linhas.add(row.select("D3C").get(0).text());
	        	linhas.add(row.select("D3N").get(0).text());
	        	linhas.add(row.select("D4C").get(0).text());
	        	linhas.add(row.select("D4N").get(0).text());	        	
	        	linhas.add(row.select("D5C").attr("i:nil"));
	        	linhas.add(row.select("D5N").attr("i:nil"));
	        	linhas.add(row.select("D6C").attr("i:nil"));
	        	linhas.add(row.select("D6N").attr("i:nil"));
	        	linhas.add(row.select("D7C").attr("i:nil"));
	        	linhas.add(row.select("D7N").attr("i:nil"));
	        	linhas.add(row.select("D8C").attr("i:nil"));
	        	linhas.add(row.select("D8N").attr("i:nil"));
	        	linhas.add(row.select("D9C").attr("i:nil"));
	        	linhas.add(row.select("D9N").attr("i:nil"));
	        	linhas.add(row.select("MC").get(0).text());
	        	linhas.add(row.select("MN").get(0).text());
	        	linhas.add(row.select("V").get(0).text());
	        	
	        	for (String str : linhas) {
					System.out.println("LOG\t"+str);
				}
	        	
	        	colunas.add(linhas);
	         }
	    }
	}*/

}
