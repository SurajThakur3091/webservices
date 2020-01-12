package com.qa.clientMethods;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClients {

	// 1.Automate Get Call without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient closableHttpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);// creating http get request

		CloseableHttpResponse closableHttpResponse = closableHttpClient.execute(httpGet);/* hit the get URL */

		return closableHttpResponse;
	}

	// 2.Automate Get Call with header
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)throws ClientProtocolException, IOException {

		CloseableHttpClient closableHttpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);// creating http get request

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closableHttpResponse = closableHttpClient	.execute(httpGet);/* hit the get URL */

		return closableHttpResponse;
	}
	
	//3.Post Method with header
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String>headerMap) throws Exception{
		
		//opening http client connection
		CloseableHttpClient closableHttpClient = HttpClients.createDefault();
		
		//Adding URL to the connection
		HttpPost httpPost = new HttpPost(url);
		
		//Adding Payload
		httpPost.setEntity(new StringEntity(entityString));
		
		//Adding Header
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closableHttpResponse = closableHttpClient	.execute(httpPost);/* hit the post URL */

		return closableHttpResponse;
		
	}

}
