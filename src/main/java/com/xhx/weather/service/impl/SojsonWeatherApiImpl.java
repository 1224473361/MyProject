package com.xhx.weather.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.xhx.weather.dto.WeatherDto;
import com.xhx.weather.dto.WeatherDto.WeatherData.Forecast;
import com.xhx.weather.enums.WeatherType;
import com.xhx.weather.query.CityWeatherQuery;
import com.xhx.weather.service.WeatherApi;
import com.xhx.weather.vo.CityWeatherVo;

/**
 * 气象获取工具类-使用sojson的api接口
 * 
 * @author lihui
 * @date 2020年5月13日
 *
 */
public class SojsonWeatherApiImpl implements WeatherApi {

	/**
	 * 请求连接地址
	 */
	private final static String SOJSON_WEATHER_URL = "http://t.weather.sojson.com/api/weather/city/{1}";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public CityWeatherVo getCityWeahter(CityWeatherQuery query) {
		CityWeatherVo vo = null;
		return vo;
	}

	/**
	 * 判断是否需要告警
	 * 
	 * @param weather
	 * @return
	 */
	private CityWeatherVo checkBadWeather(WeatherDto weather, Map<String, Object> map) {
		if (null == weather) {
			return null;
		}
		CityWeatherVo vo = new CityWeatherVo();
		List<Forecast> forecasts = weather.getData().getForecast();
		int index = 0;
		for (Forecast forecast : forecasts) {
			WeatherType type = WeatherType.getTypeByCode(forecast.getType());
			if (index > 3) {
				return null;
			}
			if (type.isAlerm()) {
				vo.setMeteorologicalDescn(type.getType() + ((index == 0) ? "" : "-未来" + index + "d"));
				vo.setMeteorologicalLevel(Integer.toString(type.getLevel()));
				vo.setMeteorologicalLabel(type.getLabel());
				return vo;
			}
			index++;
		}
		return null;
	}

	/**
	 * 调用三方接口
	 * 
	 * @param cityCode
	 * @return
	 */
	public WeatherDto getWeatherByCityCode(String cityCode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			WeatherDto dto = restTemplate.getForObject(SOJSON_WEATHER_URL, WeatherDto.class, cityCode);
			if (dto != null && dto.isSuccess()) {
				return dto;
			} else {
				logger.error("[{}]获取天气数据返回异常:{}", cityCode, dto);
			}
		} catch (Exception e) {
			logger.error("[" + cityCode + "]获取天气数据异常:" + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public CityWeatherVo getCityBadWeahter(CityWeatherQuery query) {
		CityWeatherVo vo = null;
		WeatherDto weather = getWeatherByCityCode(query.getCode());
		Map<String, Object> map = new HashMap<>();
		vo = checkBadWeather(weather, map);
		if (null != vo) {
			vo.setLatitude(query.getLatitude());
			vo.setLongitude(query.getLongitude());
			vo.setCityName(query.getCityName());
			vo.setCode(query.getCode());
			vo.setWcode(query.getCode());
		}
		return vo;
	}

}
