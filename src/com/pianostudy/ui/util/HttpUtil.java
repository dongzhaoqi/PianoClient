package com.pianostudy.ui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class HttpUtil {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	static OkHttpClient client = new OkHttpClient();

	public static String post(String api, JSONObject json) throws IOException {
		
		System.out.println("====jsonObject==:" + json);
		
		RequestBody body = RequestBody.create(JSON, json.toString());
		
		System.out.println("1111111111111");
		
		Request request = new Request.Builder().url(api).post(body).build();

		System.out.println("222222");
		
		Response response = client.newCall(request).execute();
		
		System.out.println("333333");
		
		if (response.isSuccessful()) {
			System.out.println("body success:"+response.body().string());
			return response.body().string();
		} else {
			System.out.println("body fail:"+response.body().string());
			throw new IOException("Unexpected code " + response);
		}
	}
	
	
	/*public static String run(String url) throws IOException {
		
		System.out.println("url:"+url);
		
	    Request request = new Request.Builder().url(url).build();
	    Response response = client.newCall(request).execute();    
	    if (response.isSuccessful()) {        
	    	System.out.println("body success:"+response.body().string());
	    	return response.body().string();
	    } else {        
	    	throw new IOException("Unexpected code " + response);
	    }
	}*/

	/*public static String post(String api, JSONObject jsonObject)
			throws Exception, IOException {

		String result = null;
		HttpClient mHttpClient = new DefaultHttpClient();

		HttpPost mHttpPost = new HttpPost(ContantValues.urlRoot + api);
		System.out.println("======:" + ContantValues.urlRoot + api);
		System.out.println("====jsonObject==:" + jsonObject);

		StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		mHttpPost.setEntity(entity);

		System.out.println("mHttpResponse before:");
		HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
		System.out.println("mHttpResponse:" + mHttpResponse);

		HttpEntity entityResponse = mHttpResponse.getEntity();
		System.out.print("888:" + entityResponse.getContentEncoding());
		// entityResponse.getContentType();
		if (entityResponse != null) {
			InputStream instreams = entityResponse.getContent();
			result = convertStreamToString(instreams);
			// System.out.println("Do something");
			System.out.println("999:"
					+ (new String(result.getBytes(), "utf-8")));
			// Do not need the rest
			// Tranlate(str);
			mHttpPost.abort();
		}
		return new String(result.getBytes(), "utf-8");

	}*/

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}