package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import spring.domain.User;


public class RestHttpClientApp {

	public static void main(String[] args) throws Exception {
		////주석 하나씩 처리하며 연습
		
		System.out.println("\n==================================\n");
		//1.1 Http Get 방식 Request: JsonSimple lib 사용
		RestHttpClientApp.RequestHttpGet_UseJsonSimple();
		
		System.out.println("\n==================================\n");
		//1.2 Http Get 방식 Request: CodeHaus lib 사용
		RestHttpClientApp.RequestHttpGet_UseCodeHaus();
		
		System.out.println("\n==================================\n");
		//2.1 Http Protocol POST 방식 Request
		// : Form Data 전달(JSON 이용)/JsonSimple lib 사용
		RestHttpClientApp.RequestHttpPostData_UseJsonSimple();
		
		System.out.println("\n==================================\n");
		//2.2 Http Protocol POST 방식 Request
		// : Form Data 전달(JSON 이용)/CodeHaus lib 사용
		RestHttpClientApp.RequestHttpPostData_UseCodeHaus();
		
		
	}
	
	public static void RequestHttpGet_UseJsonSimple() throws Exception {
		//브라우저를 만든다.
		HttpClient httpClient = new DefaultHttpClient();
		
		//request 로 보내준다.!!
		String url = "http://127.0.0.1:8080/Spring14/user/json/user01?name=user02&age=10";
		//get방식
		HttpGet httpGet = new HttpGet(url);
		//헤더 만들기
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("content-Type","application/json");
		//response로 get방식으로 받는다
		HttpResponse httpResponse = httpClient.execute(httpGet);
		//받은결과값 출력!
		System.out.println(httpResponse);
		System.out.println();
		//바디정보
		HttpEntity responsHttpEntity=httpResponse.getEntity();
		//바디정보를 읽어온다
		InputStream is = responsHttpEntity.getContent();
		//바디정보를 buffered에 한줄씩 담으면서 한글 변역
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server에서 받은 Data 확인 ]");
		//한줄씩 받은정보를 serverData에 담는다
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		
		System.out.println(jsonobj);
		
		
	}
	
	public static void RequestHttpGet_UseCodeHaus() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/Spring14/user/json/user01?name=user02&age=10";
		
		HttpGet httpGet=new HttpGet(url);
		httpGet.setHeader("Accept","application/json");
		httpGet.setHeader("Content-Type","applicatoin/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server 에서 받은 DATA 확인]");
		String serverData = br.readLine();
		System.out.println(serverData);
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		//User user = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		//jsonobj가 map으로 받아서 사용하게 되면 innerjson에 있는 get("user")로 받아서 사용
		//user로 보내지면 jsonobj가 user가 되니까 get("user")를 안써줘도 된다.
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
		
		//======================================
		
		//=======================================
		
		
	}
	
	public static void RequestHttpPostData_UseJsonSimple() throws Exception{
			
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/Spring14/user/json/getUser/user01";
		
		
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		//String data = "{\"userId\":\"test\",\"userName\":\"홍길동\"}";
		
		JSONObject json = new JSONObject();
		json.put("userId", "test");
		json.put("userName","홍길동");
		json.put("age", 123);
		
		HttpEntity requestHttpEntity = new StringEntity(json.toString(),"utf-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server에서 받은 데이터 확인]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		
		System.out.println(jsonobj);
		
		
		
			
	}
	
	public static void RequestHttpPostData_UseCodeHaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/Spring14/user/json/getUser/user01";
		
		
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		//String data = "{\"userId\":\"test\",\"userName\":\"홍길동\"}";
		
//		JSONObject json = new JSONObject();
//		json.put("userId", "test");
//		json.put("userName","홍길동");
		
		User user = new User("test","홍길동", "1111", null, 10);
		ObjectMapper objectMapper01 = new ObjectMapper();
		
		String jsonValue = objectMapper01.writeValueAsString(user);
		
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue,"utf-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server에서 받은 데이터 확인]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		
		ObjectMapper objectMapper = new ObjectMapper();
		//User returnUser = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		User returnUser = objectMapper.readValue(jsonobj.toString(), User.class);
		
		System.out.println(returnUser);
		
		
		
			
	}

}
