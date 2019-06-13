package com.xhx.restclient.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 模拟http请求
 * 
 * @date 2019年5月23日
 * @author lihui
 */
@Service
public class MHttpClient {

	@Autowired
	RestTemplate template;

	/**
	 * 发送http请求
	 * 
	 * @param url    地址
	 * @param method 方法
	 * @param params 参数
	 * @return
	 */
	public String client(String url, HttpMethod method, MultiValueMap<String, String> params) {
		// header参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
		// 组装
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers); 
		ResponseEntity<String> response = template.exchange(url, method, request, String.class, params);
		return response.getBody();
	}

}
