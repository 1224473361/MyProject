package com.xhx.mybatisplustest.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 演示实体父类
 * 
 * @date 2019年6月12日
 * @author xhx
 */
@SuppressWarnings("rawtypes")
@KeySequence("OA_PUBLIC_SEQ")//类注解
public abstract class SuperEntity<T extends Model> extends Model<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
