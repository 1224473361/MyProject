package com.xhx.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取省市区json
 * 
 * @author xhx
 * @date 2020年8月20日
 *
 */
@Slf4j
public class GetAreaJsonUtil {

	public static final String ALL_JSON_PATH = "D:/json/all.json";
	public static final String INFO_JSON_PATH = "D:/json/infos.json";
	public static final String URL = "https://geo.datav.aliyun.com/areas_v2/bound/";

	public static final String DIR = "D:/json/jsons2/";

	public static void main(String[] args) {
		String jsonStr = getJsonStr(ALL_JSON_PATH);
		JSONArray arr = getJSON(jsonStr);
		RestTemplate rest = new RestTemplate();
		for (int i = 0; i < arr.size(); i++) {
			JSONObject jobj = arr.getJSONObject(i);
			String level = jobj.getString("level");
			if (level.equals("province")) {
				String code = jobj.getString("adcode");
				String name = jobj.getString("name");
				System.out.println(name);
				// getJSONFile(code, name, rest);
			}
		}
	}

	public static void getJSONFile(String code, String name, RestTemplate rest) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			String url = URL + code + ".json";
			ResponseEntity<String> responseEntity = rest.getForEntity(url, String.class);
			String string = responseEntity.getBody();
			String path = DIR + code + ".json";
			FileUtil.writeString(string, path, "utf-8");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("{}", code);
		}
	}

	public static JSONArray getJSON(String str) {
		try {
			return JSON.parseArray(str);
		} catch (Exception e) {
			throw new RuntimeException("json解析错误");
		}
	}

	public static String getJsonStr(String path) {
		File file = new File(path);
		if (!file.exists()) {
			throw new RuntimeException("文件不存在");
		}
		try {
			return FileUtil.readString(file, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException("文件读取错误");
		}
	}

}
