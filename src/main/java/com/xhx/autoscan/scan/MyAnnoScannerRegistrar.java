package com.xhx.autoscan.scan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import com.xhx.autoscan.annotaion.MyAnn;
import com.xhx.autoscan.annotaion.MyAnnScan;

/**
 * 
 * @date 2019年9月2日
 * @author lihui
 */
public class MyAnnoScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

	private ResourceLoader resoureLoader;
	private Class<? extends Annotation> annoClass = MyAnn.class;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resoureLoader = resourceLoader;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(MyAnnScan.class.getName()));
		ClassPathMyAnnScanner scanner = new ClassPathMyAnnScanner(registry);

		if (this.resoureLoader != null) {
			scanner.setResourceLoader(resoureLoader);
		}

		List<String> basePackages = new ArrayList<>();
		if (StringUtils.isEmpty(annoAttrs.getStringArray("basePackages"))) {
			throw new RuntimeException("basePackages参数为空");
		}
		for (String pkg : annoAttrs.getStringArray("basePackages")) {
			if (StringUtils.hasText(pkg)) {
				basePackages.add(pkg);
			}
		}

		// 指定要扫描的注解
		scanner.setAnnotationClass(annoClass);
		scanner.registerFilters();
		scanner.doScan(StringUtils.toStringArray(basePackages));
	}

}
