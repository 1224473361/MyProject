package com.xhx.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年6月24日
 * @author lihui
 */
@Slf4j
public class MyHttpClientInstance {

	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendPost(String url, List<NameValuePair> params) {
		HttpClientBuilder hb = HttpClientBuilder.create();
		CloseableHttpClient instance = hb.disableRedirectHandling().build();
		try {
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
			CloseableHttpResponse rs = instance.execute(post);
			return readEntity(rs.getEntity());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}

	/**
	 * @param hs
	 * @return
	 */
	private static String readEntity(HttpEntity hs) {
		try (InputStream in = hs.getContent();) {
			return readStream(hs.getContent());
		} catch (Exception e) {
			return "error";
		}
	}

	/**
	 * 读取 InputStream 到 String字符串中
	 * 
	 * @param in
	 * @return
	 */
	private static String readStream(InputStream in) {
		// 创建字节数组输出流，用来输出读取到的内容
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			// 创建缓存大小
			byte[] buffer = new byte[1024]; // 1KB
			// 每次读取到内容的长度
			int len = -1;
			// 开始读取输入流中的内容
			while ((len = in.read(buffer)) != -1) { // 当等于-1说明没有数据可以读取了
				baos.write(buffer, 0, len); // 把读取到的内容写到输出流中
			}
			// 返回字符串结果
			return baos.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return e.getMessage();
		}
	}
}
