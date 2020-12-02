package com.xhx.weather.enums;

/**
 * 天气类型
 * 
 * @author xhx
 * @date 2020年5月12日
 *
 */
public enum WeatherType {

	/**
	 * 
	 */
	W1("小雨", 4, "1"),
	/**
	 * 
	 */
	W2("小到中雨", 4, "1"),
	/**
	 * 
	 */
	W3("中雨", 4, "1"),
	/**
	 * 
	 */
	W4("中到大雨", 4, "1"),
	/**
	 * 
	 */
	W5("大雨", 4, "1"),
	/**
	 * 
	 */
	W6("大到暴雨", 3, "1"),
	/**
	 * 
	 */
	W7("暴雨", 3, "1"),
	/**
	 * 
	 */
	W8("暴雨到大暴雨", 2, "1"),
	/**
	 * 
	 */
	W9("大暴雨", 2, "1"),
	/**
	 * 
	 */
	W10("大暴雨到特大暴雨", 2, "1"),
	/**
	 * 
	 */
	W11("特大暴雨", 1, "1"),
	/**
	 * 
	 */
	W12("冻雨", 3, "1"),
	/**
	 * 
	 */
	W13("阵雨", 3, "1"),
	/**
	 * 
	 */
	W14("雷阵雨", 3, "1"),
	/**
	 * 
	 */
	W15("雨夹雪", 4, "1"),
	/**
	 * 
	 */
	W16("雷阵雨伴有冰雹", 1, "1"),
	/**
	 * 
	 */
	W17("小雪", 4, "2"),
	/**
	 * 
	 */
	W18("小到中雪", 4, "2"),
	/**
	 * 
	 */
	W19("中雪", 4, "2"),
	/**
	 * 
	 */
	W20("中到大雪", 4, "2"),
	/**
	 * 
	 */
	W21("大雪", 4, "2"),
	/**
	 * 
	 */
	W22("大到暴雪", 1, "2"),
	/**
	 * 
	 */
	W23("暴雪", 2, "2"),
	/**
	 * 
	 */
	W24("阵雪", 3, "2"),
	/**
	 * 
	 */
	W25("晴", 4, "3"),
	/**
	 * 
	 */
	W26("多云", 4, "4"),
	/**
	 * 
	 */
	W27("阴", 4, "5"),
	/**
	 * 
	 */
	W28("强沙尘暴", 1, "6"),
	/**
	 * 
	 */
	W29("扬沙", 3, "6"),
	/**
	 * 
	 */
	W30("沙尘暴", 2, "6"),
	/**
	 * 
	 */
	W31("浮尘", 3, "6"),
	/**
	 * 
	 */
	W32("雾", 3, "7"),
	/**
	 * 
	 */
	W33("霾", 3, "8");

	/**
	 * 气候描述
	 */
	private String type;
	/**
	 * 告警级别(4蓝色、3黄色、2橙色和1红色)
	 */
	private int level;
	/**
	 * 气象标签(1雨，2雪，3晴,4多云,5阴,6沙尘暴,7雾,8霾)
	 */
	private String label;

	private WeatherType(String type, int level, String label) {
		this.type = type;
		this.level = level;
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}

	public String getLabel() {
		return this.label;
	}

	/**
	 * 是否告警
	 */
	public boolean isAlerm() {
		return this.level < 3;
	}

	/**
	 * 根据code获取枚举实例
	 * 
	 * @param code
	 * @return
	 */
	public static WeatherType getTypeByCode(String code) {
		WeatherType[] ss = WeatherType.values();
		for (WeatherType type : ss) {
			if (type.getType().equals(code)) {
				return type;
			}
		}
		return null;
	}

}
