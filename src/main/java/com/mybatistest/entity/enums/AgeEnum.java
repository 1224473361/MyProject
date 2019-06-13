package com.mybatistest.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @date 2019年6月12日
 * @author lihui
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AgeEnum implements IEnum<Integer> {
	ONE(1, "一岁"), TWO(2, "二岁");

	private Integer value;
	private String desc;

	AgeEnum(final Integer value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

}
