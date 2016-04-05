package com.pianostudy.ui.util;

import org.apache.http.entity.StringEntity;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GeiliRestClient {
	
    private static final String BASE_URL = ContantValues.urlRoot;
	//private static final String BASE_URL = "http://192.168.0.104:8080/HelpAppServer/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context,String url, StringEntity stringEntity, AsyncHttpResponseHandler responseHandler){
        System.out.println("post url:" + getAbsoluteUrl(url));
    	client.post(context,getAbsoluteUrl(url),stringEntity,"application/json",responseHandler);
    
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}