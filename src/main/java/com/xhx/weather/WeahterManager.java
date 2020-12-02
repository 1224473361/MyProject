package com.xhx.weather;

import java.util.ArrayList;
import java.util.List;

import com.xhx.weather.query.CityWeatherQuery;
import com.xhx.weather.service.WeatherApi;
import com.xhx.weather.service.impl.CnWeatherApiImpl;
import com.xhx.weather.vo.CityWeatherVo;

/**
 * 天气获取
 * 
 * @author xhx
 * @date 2020年5月12日
 *
 */
public class WeahterManager {

	private WeatherApi api = new CnWeatherApiImpl();

	/**
	 * 城市恶劣气象数据
	 * 
	 * @param provinces 省份名称列表
	 * @return
	 *         <ul>
	 *         <li>longitude:经度</li>
	 *         <li>latitude:纬度</li>
	 *         <li>meteorologicalDescn:气象描述</li>
	 *         <li>meteorologicalLevel:气象预警级别</li>
	 *         <li>meteorologicalLabel:气象标签(1雨，2雪，3晴,4多云,5阴,6沙尘暴,7雾,8霾)</li>
	 *         <li>cityName:城市名称</li>
	 *         </ul>
	 * @throws Exception
	 */
	public List<CityWeatherVo> weatherByProvince(String[] cityCodes) {
		List<CityWeatherVo> resultList = new ArrayList<>();
		for (String string : cityCodes) {
			CityWeatherQuery cquery = new CityWeatherQuery();
			cquery.setCode(string);
			CityWeatherVo vo = api.getCityWeahter(cquery);
			if (null != vo) {
				resultList.add(vo);
			}
		}
		return resultList;
	}

	public static void main(String[] args) {
		WeahterManager m = new WeahterManager();
		String[] arr = { "101040100" };
		System.out.println(m.weatherByProvince(arr));
	}
}
