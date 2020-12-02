package com.xhx.restclient.service;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 
 * @date 2019年5月23日
 * @author xhx
 */
@Service
public class MenuSendService extends BaseService {

	private MenuSendService() {
		super();
		init("/menu/");
	}

	public String getAllMenus() {
		String interfaceName = "getAllMenus";
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("currentUserId", "123");
		params.add("rootid", "654");
		return this.sendHttp(interfaceName, params);
	}

}
