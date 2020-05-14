package com.xhx.weather.query;

import lombok.Data;

/**
 * 城市天气查询实体
 * 
 * @author lihui
 * @date 2020年5月13日
 *
 */
@Data
public class CityWeatherQuery {
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 城市名编码
	 */
	private String code;

}
