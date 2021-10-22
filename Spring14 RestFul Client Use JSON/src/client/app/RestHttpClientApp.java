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
		////�ּ� �ϳ��� ó���ϸ� ����
		
		System.out.println("\n==================================\n");
		//1.1 Http Get ��� Request: JsonSimple lib ���
		RestHttpClientApp.RequestHttpGet_UseJsonSimple();
		
		System.out.println("\n==================================\n");
		//1.2 Http Get ��� Request: CodeHaus lib ���
		RestHttpClientApp.RequestHttpGet_UseCodeHaus();
		
		System.out.println("\n==================================\n");
		//2.1 Http Protocol POST ��� Request
		// : Form Data ����(JSON �̿�)/JsonSimple lib ���
		RestHttpClientApp.RequestHttpPostData_UseJsonSimple();
		
		System.out.println("\n==================================\n");
		//2.2 Http Protocol POST ��� Request
		// : Form Data ����(JSON �̿�)/CodeHaus lib ���
		RestHttpClientApp.RequestHttpPostData_UseCodeHaus();
		
		
	}
	
	public static void RequestHttpGet_UseJsonSimple() throws Exception {
		//�������� �����.
		HttpClient httpClient = new DefaultHttpClient();
		
		//request �� �����ش�.!!
		String url = "http://127.0.0.1:8080/Spring14/user/json/user01?name=user02&age=10";
		//get���
		HttpGet httpGet = new HttpGet(url);
		//��� �����
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("content-Type","application/json");
		//response�� get������� �޴´�
		HttpResponse httpResponse = httpClient.execute(httpGet);
		//��������� ���!
		System.out.println(httpResponse);
		System.out.println();
		//�ٵ�����
		HttpEntity responsHttpEntity=httpResponse.getEntity();
		//�ٵ������� �о�´�
		InputStream is = responsHttpEntity.getContent();
		//�ٵ������� buffered�� ���پ� �����鼭 �ѱ� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ�� ]");
		//���پ� ���������� serverData�� ��´�
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
		
		System.out.println("[Server ���� ���� DATA Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		//User user = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		//jsonobj�� map���� �޾Ƽ� ����ϰ� �Ǹ� innerjson�� �ִ� get("user")�� �޾Ƽ� ���
		//user�� �������� jsonobj�� user�� �Ǵϱ� get("user")�� �Ƚ��൵ �ȴ�.
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
		
		//String data = "{\"userId\":\"test\",\"userName\":\"ȫ�浿\"}";
		
		JSONObject json = new JSONObject();
		json.put("userId", "test");
		json.put("userName","ȫ�浿");
		json.put("age", 123);
		
		HttpEntity requestHttpEntity = new StringEntity(json.toString(),"utf-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� ������ Ȯ��]");
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
		
		//String data = "{\"userId\":\"test\",\"userName\":\"ȫ�浿\"}";
		
//		JSONObject json = new JSONObject();
//		json.put("userId", "test");
//		json.put("userName","ȫ�浿");
		
		User user = new User("test","ȫ�浿", "1111", null, 10);
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
		
		System.out.println("[Server���� ���� ������ Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		
		ObjectMapper objectMapper = new ObjectMapper();
		//User returnUser = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		User returnUser = objectMapper.readValue(jsonobj.toString(), User.class);
		
		System.out.println(returnUser);
		
		
		
			
	}

}
