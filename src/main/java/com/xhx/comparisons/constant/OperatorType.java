package com.xhx.comparisons.constant;

/**
 * 运算符
 * 
 * @date 2019年5月23日
 * @author xhx
 */
public enum OperatorType {

	/**
	 * == 等于
	 */
	EQUAL("==", "等于", "equal"),
	/**
	 * != 不等于
	 */
	NOT_EQUAL("!=", "不等于", "notEqual"),
	/**
	 * > 大于
	 */
	GEARTER(">", "大于", "greater"),
	/**
	 * >= 等于
	 */
	GEARTER_EQUAL(">=", "等于", "greaterEqual"),
	/**
	 * < 小于
	 */
	LESS("<", "小于", "less"),
	/**
	 * <= 小于
	 */
	LESS_EQUAL("<=", "小于", "lessEqual");

	private String value;
	private String descn;
	private String code;

	OperatorType(String value, String descn, String code) {
		this.value = value;
		this.descn = descn;
		this.code = code;
	}

	public String value() {
		return this.value;
	}

	public String descn() {
		return this.descn;
	}

	public String code() {
		return this.code;
	}

	@Override
	public String toString() {
		return this.value + "-" + this.descn + "-" + this.code;
	}

	/**
	 * 根据类型的名称，返回类型的枚举实例。
	 * 
	 * @param typeName 类型名称
	 */
	public static OperatorType fromTypeName(String value) {
		for (OperatorType type : OperatorType.values()) {
			if (type.value().equals(value)) {
				return type;
			}
		}
		return null;
	}

}
