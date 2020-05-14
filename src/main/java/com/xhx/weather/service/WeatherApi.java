package com.xhx.weather.service;

import com.xhx.weather.query.CityWeatherQuery;
import com.xhx.weather.vo.CityWeatherVo;

/**
 * 天气接口
 * 
 * @author lihui
 * @date 2020年5月13日
 *
 */
public interface WeatherApi {

	/**
	 * 通过编码获取城市天气信息
	 * 
	 * @param query
	 * @return
	 */
	CityWeatherVo getCityWeahter(CityWeatherQuery query);

	/**
	 * 通过编码获取城市恶劣天气信息
	 * 
	 * @param query
	 * @return
	 */
	CityWeatherVo getCityBadWeahter(CityWeatherQuery query);
}
