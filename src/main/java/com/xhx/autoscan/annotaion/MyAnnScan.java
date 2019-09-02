package com.xhx.autoscan.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.xhx.autoscan.scan.MyAnnoScannerRegistrar;

/**
 * 自动扫描MyAnno接口
 * 
 * @date 2019年9月2日
 * @author lihui
 */
@Import(MyAnnoScannerRegistrar.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnScan {

	/**
	 * 扫描路径
	 * 
	 * @return
	 */
	String[] basePackages() default {};
}
