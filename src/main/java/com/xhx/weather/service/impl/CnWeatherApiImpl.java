package com.xhx.weather.service.impl;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.xhx.weather.query.CityWeatherQuery;
import com.xhx.weather.service.WeatherApi;
import com.xhx.weather.vo.CityWeatherVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用气象局提供的接口
 * 
 * @author lihui
 * @date 2020年5月13日
 *
 */
@Slf4j
public class CnWeatherApiImpl implements WeatherApi {

	private static final String WEATHER_URL = "http://www.weather.com.cn/data/sk/{1}.html";
	private static final String WEATHER_URL2 = "http://www.weather.com.cn/data/cityinfo/{1}.html";

	@Override
	public CityWeatherVo getCityWeahter(CityWeatherQuery query) {
		String cityCode = query.getCode();
		try {
			Thread.sleep(1100);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(WEATHER_URL, String.class, cityCode);
			if (StringUtils.isEmpty(result)) {
				log.info("{} 天气获取异常", cityCode);
				return null;
			}
			result = new String(result.getBytes("ISO-8859-1"), "utf-8");
			String result2 = restTemplate.getForObject(WEATHER_URL2, String.class, cityCode);
			if (StringUtils.isEmpty(result2)) {
				log.info("{} 天气获取异常", cityCode);
				return null;
			}
			result2 = new String(result2.getBytes("ISO-8859-1"), "utf-8");
			log.info("{}", result2);
			CityWeatherVo vo = getVo(result, result2, false);
			if (null != vo) {
				vo.setLatitude(query.getLatitude());
				vo.setLongitude(query.getLongitude());
				vo.setCityName(query.getCityName());
				vo.setCode(query.getCode());
				vo.setWcode(query.getCode());
				return vo;
			}
		} catch (Exception e) {
			log.error("[" + cityCode + "]获取天气数据异常:" + e.getMessage(), e);
		}
		return null;
	}

	private CityWeatherVo getVo(String str, String str2, boolean isCheckBad) {
		// {"weatherinfo":{"city":"哈尔滨","cityid":"101050101","temp":"26.3","WD":"东北风"
		// ,"WS":"小于3级","SD":"55%","AP":"985.6hPa"
		// ,"njd":"暂无实况","WSE":"<3","time":"17:00","sm":"2.4","isRadar":"1","Radar":"JC_RADAR_AZ9451_JB"}}
		CityWeatherVo vo = new CityWeatherVo();

		// JSONObject obj = JSONObject.parseObject(str);
		// JSONObject weatherinfo = obj.getJSONObject("weatherinfo");

		// {"weatherinfo":{"city":"哈尔滨","cityid":"101050101","temp1":"14℃","temp2":"28℃"
		// ,"weather":"雷阵雨转晴","img1":"n4.gif","img2":"d0.gif","ptime":"18:00"}}

		JSONObject obj2 = JSONObject.parseObject(str2);
		JSONObject weatherinfo2 = obj2.getJSONObject("weatherinfo");
		String weather = weatherinfo2.getString("weather");
		vo.setMeteorologicalDescn(weather);
		if (!isCheckBad) {
			vo.setMeteorologicalLabel("1");
			vo.setMeteorologicalLevel("1");
			return vo;
		}
		if (isDRain(weather)) {
			vo.setMeteorologicalLabel("1");
			vo.setMeteorologicalLevel("1");
		} else if (isRain(weather)) {
			vo.setMeteorologicalLabel("1");
			vo.setMeteorologicalLevel("2");
		} else {
			return null;
		}
		return vo;
	}

	private static final String[] D_RAIN_ARR = { "雷阵雨", "暴雨" };

	private static final String[] RAIN_ARR = { "阵雨" };

	/**
	 * 是否需要红色阵雨预警
	 * 
	 * @param str
	 * @return
	 */
	private boolean isDRain(String weather) {
		for (String str : D_RAIN_ARR) {
			if (weather.contains(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否需要橙色阵雨预警
	 * 
	 * @param str
	 * @return
	 */
	private boolean isRain(String weather) {
		for (String str : RAIN_ARR) {
			if (weather.contains(str) && !weather.contains("雷阵雨")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public CityWeatherVo getCityBadWeahter(CityWeatherQuery query) {
		String cityCode = query.getCode();
		try {
			Thread.sleep(1100);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(WEATHER_URL, String.class, cityCode);
			if (StringUtils.isEmpty(result)) {
				log.info("{} 天气获取异常", cityCode);
				return null;
			}
			result = new String(result.getBytes("ISO-8859-1"), "utf-8");
			String result2 = restTemplate.getForObject(WEATHER_URL2, String.class, cityCode);
			if (StringUtils.isEmpty(result2)) {
				log.info("{} 天气获取异常", cityCode);
				return null;
			}
			result2 = new String(result2.getBytes("ISO-8859-1"), "utf-8");
			log.info("{}", result2);
			CityWeatherVo vo = getVo(result, result2, true);
			if (null != vo) {
				vo.setLatitude(query.getLatitude());
				vo.setLongitude(query.getLongitude());
				vo.setCityName(query.getCityName());
				vo.setCode(query.getCode());
				vo.setWcode(query.getCode());
				return vo;
			}
		} catch (Exception e) {
			log.error("[" + cityCode + "]获取天气数据异常:" + e.getMessage(), e);
		}
		return null;
	}

}
