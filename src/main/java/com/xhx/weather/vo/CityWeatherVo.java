package com.xhx.weather.vo;

import lombok.Data;

/**
 * 气象vo
 * 
 * @author lihui
 * @date 2020年5月13日
 *
 */
@Data
public class CityWeatherVo {

	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 气象描述
	 */
	private String meteorologicalDescn;
	/**
	 * 气象预警级别
	 */
	private String meteorologicalLevel;
	/**
	 * 气象标签(1雨，2雪，3晴,4多云,5阴,6沙尘暴,7雾,8霾)
	 */
	private String meteorologicalLabel;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 城市名编码
	 */
	private String code;
	/**
	 * 城市名编码-供天气接口使用
	 */
	private String wcode;

}
