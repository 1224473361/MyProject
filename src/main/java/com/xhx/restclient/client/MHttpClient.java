package com.xhx.restclient.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟http请求
 * 
 * @date 2019年5月23日
 * @author xhx
 */
@Service
@Slf4j
public class MHttpClient {

	@Autowired
	private RestTemplate template;
	private static final String KEY_AUTHORIZATION = "Authorization";
	private static final String KEY_BEARER = "Bearer ";
	private static final String LOG_STR = "{}:{}";

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

	/**
	 * 连接url
	 * 
	 * @param url
	 * @param obj
	 * @return
	 */
	public JSONObject restToPost(String url, JSONObject obj) {
		// header参数
		HttpHeaders headers = new HttpHeaders();
		headers.add(KEY_AUTHORIZATION, KEY_BEARER + obj.getString("token"));
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			// 放入body中的json参数 假如有其他参数可以放置进去
			HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
			log.info(LOG_STR, url, "开始请求。。。");
			ResponseEntity<JSONObject> responseEntity = template.exchange(url, HttpMethod.POST, request,
					JSONObject.class);
			log.info(LOG_STR, url, responseEntity.getBody());
			return responseEntity.getBody();
		} catch (ResourceAccessException e) {
			log.info("[{}]", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @param paramMap
	 * @param token
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject restToFromDataPost(String url, MultiValueMap<String, String> paramMap, String token) {
		// header参数
		HttpHeaders headers = new HttpHeaders();
		headers.add(KEY_AUTHORIZATION, KEY_BEARER + token);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		try {
			// 2、使用postForEntity请求接口
			org.springframework.http.HttpEntity<MultiValueMap<String, Object>> request = new org.springframework.http.HttpEntity(
					paramMap, headers);
			ResponseEntity<JSONObject> responseEntity = template.postForEntity(url, request, JSONObject.class);
			log.info(LOG_STR, url, responseEntity.getBody());
			return responseEntity.getBody();
		} catch (ResourceAccessException e) {
			log.info("[{}]", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 使用template调用外部rest接口<br>
	 * 需要往headers里面存入Authorization:Bearer token
	 *
	 * @param url   地址
	 * @param obj   参数
	 * @param token token
	 * @return
	 */
	public JSONObject restToPost(String url, JSONObject obj, String token) {
		// header参数
		HttpHeaders headers = new HttpHeaders();
		headers.add(KEY_AUTHORIZATION, KEY_BEARER + token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			// 放入body中的json参数 假如有其他参数可以放置进去
			HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
			log.info(LOG_STR, url, "开始请求。。。");
			ResponseEntity<JSONObject> responseEntity = template.exchange(url, HttpMethod.POST, request,
					JSONObject.class);
			log.info(LOG_STR, url, responseEntity.getBody());
			return responseEntity.getBody();
		} catch (ResourceAccessException e) {
			log.info("[{}]", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}
