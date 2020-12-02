package com.xhx.restclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.xhx.restclient.client.MHttpClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年5月23日
 * @author xhx
 */
@Service
@Slf4j
public abstract class BaseService {

	@Autowired
	private MHttpClient client;
	private String baseUrl = "http://127.0.0.1:9995/xx";
	protected String typeUrl = "/process/";

	public void init(String typeUrl) {
		this.typeUrl = typeUrl;
	}

	/**
	 * 发送请求
	 * 
	 * @param interfaceName
	 * @param params
	 */
	protected String sendHttp(String interfaceName, MultiValueMap<String, String> params) {
		String url = this.baseUrl + this.typeUrl + interfaceName;
		HttpMethod method = HttpMethod.POST;
		log.info("请求接口：{}", url);
		log.info("请求参数：{}", params);
		String rs = this.client.client(url, method, params);
		log.info("结果：{}", rs);
		return rs;
	}

}
