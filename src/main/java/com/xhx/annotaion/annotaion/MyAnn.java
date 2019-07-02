package com.xhx.annotaion.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 
 * @date 2019年7月2日
 * @author lihui
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnn {

	public String value() default "";
}
