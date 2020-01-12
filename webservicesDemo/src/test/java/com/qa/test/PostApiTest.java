package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.clientMethods.RestClients;
import com.qa.data.JasonPayload;
import com.qa.restApiBase.com.TestBase;

public class PostApiTest extends TestBase {
	TestBase testBase;
	String endURL;
	String apiURL;
	String URI;
	RestClients restClient;
	CloseableHttpResponse closableHttpResponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		endURL = prop.getProperty("EndPointURL");
		apiURL = prop.getProperty("ServiceURL");
		URI = endURL + apiURL;

	}
	@Test
	public void postApiTest() throws Exception{
		restClient = new RestClients();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API
		ObjectMapper mapper = new ObjectMapper();
		JasonPayload  payload = new JasonPayload("Singh",70000,29);
		
		//object to JSON fileConversion
		mapper.writeValue(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\data\\UserData.JSON"), payload);
		
		//object to json string
		String 	payloadJsonString = mapper.writeValueAsString(payload);
		closableHttpResponse = restClient.post(URI, payloadJsonString, headerMap);
		
		//1.Status Code
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(200, statusCode,"Status code si not 200");
		
		
		//2.Get Json response string
		String reposeString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
		
		/* JSON response will be converted to simple string format*/
		
		JSONObject responseJson = new JSONObject(reposeString);
		System.out.println("Response JSON---->" + responseJson);
		
		//Json to java object "un-marshelling"
		
		JasonPayload  payLoadConverted = mapper.readValue(reposeString,JasonPayload.class);
		System.out.println(payLoadConverted);
		System.out.println(payload.getName().equals(payLoadConverted.getName()));
		System.out.println(payload.getAge()==(payLoadConverted.getAge()));
		System.out.println(payload.getSalary()==(payLoadConverted.getSalary()));
		
		
		
	}
}
