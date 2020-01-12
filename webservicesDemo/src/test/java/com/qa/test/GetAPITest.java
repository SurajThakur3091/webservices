package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.clientMethods.RestClients;
import com.qa.restApiBase.com.TestBase;
import com.qa.testUtil.JsonReader;

public class GetAPITest extends TestBase {

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

	@Test(priority=1)
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClients();
		closableHttpResponse = restClient.get(URI);
		
		// get Status Code
				int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code returned is---->: " + statusCode);
				Assert.assertEquals(statusCode, 200,"Status Code is not 200");
				//Assert.assertEquals(statusCode, "200","Status Code is not 200");
				// Get Response payload
				String reposeString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
				
				/* JSON response will be converted to simple string format*/
				
				JSONObject responseJson = new JSONObject(reposeString);
				System.out.println("Response JSON---->" + responseJson);
				
				String id = JsonReader.getValueByJpath(responseJson, "/data[0]/id");
				String employeeName = JsonReader.getValueByJpath(responseJson, "/data[0]/employee_name");
				String employeSalary = JsonReader.getValueByJpath(responseJson, "/data[0]/employee_salary");
				String employeeAge = JsonReader.getValueByJpath(responseJson, "/data[0]/employee_age");
				System.out.println("Employee ID-->"+ id);
				System.out.println("Employee Name-->"+ employeeName);
				System.out.println("Employee Salary-->"+ employeSalary);
				System.out.println("Employee Age-->"+ employeeAge);

				// get Header Values

				Header[] headerArray = closableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeader = new HashMap<String, String>();
				for (Header header : headerArray) {
					allHeader.put(header.getName(), header.getValue());
				}

				System.out.println("Header Available --->" + allHeader);
	}

		
	@Test(priority = 2)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException {
		restClient = new RestClients();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("UserName", "abc_213");
//		headerMap.put("Password", "12354");
//		headerMap.put("Tokenkey", "98745");
		
		closableHttpResponse = restClient.get(URI,headerMap);
		
		// get Status Code
				int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code returned is---->: " + statusCode);
				Assert.assertEquals(statusCode, 200,"Status Code is not 200");
				//Assert.assertEquals(statusCode, "200","Status Code is not 200");
				// Get Response payload
				String reposeString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
				
				/* JSON response will be converted to simple string format*/
				
				JSONObject responseJson = new JSONObject(reposeString);
				System.out.println("Response JSON---->" + responseJson);
				
				String id = JsonReader.getValueByJpath(responseJson, "/data[0]/id");
				String employeeName = JsonReader.getValueByJpath(responseJson, "/data[0]/employee_name");
				String employeSalary = JsonReader.getValueByJpath(responseJson, "/data[0]/employee_salary");
				String employeeAge = JsonReader.getValueByJpath(responseJson, "/data[0]/employee_age");
				System.out.println("Employee ID-->"+ id);
				System.out.println("Employee Name-->"+ employeeName);
				System.out.println("Employee Salary-->"+ employeSalary);
				System.out.println("Employee Age-->"+ employeeAge);

				// get Header Values

				Header[] headerArray = closableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeader = new HashMap<String, String>();
				for (Header header : headerArray) {
					allHeader.put(header.getName(), header.getValue());
				}

				System.out.println("Header Available --->" + allHeader);
	}

}
