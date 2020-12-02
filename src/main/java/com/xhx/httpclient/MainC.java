package com.xhx.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * @date 2019年6月24日
 * @author xhx
 */
public class MainC {

	private static final String POST_URL = "http://127.0.0.1:9995/oa/menu/getAllMenus";

	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("rootid", "654"));
		params.add(new BasicNameValuePair("currentUserId", "798745"));
		System.err.println(MyHttpClientInstance.sendPost(POST_URL, params));
	}

}
