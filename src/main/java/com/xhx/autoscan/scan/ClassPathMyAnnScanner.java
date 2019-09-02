package com.xhx.autoscan.scan;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.xhx.autoscan.fac.MyAnnProxyFactory;

/**
 * 
 * @date 2019年9月2日
 * @author lihui
 */
public class ClassPathMyAnnScanner extends ClassPathBeanDefinitionScanner {

	private MyAnnProxyFactory mapperFactoryBean = new MyAnnProxyFactory();

	private Class<? extends Annotation> annotationClass;

	public ClassPathMyAnnScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	/**
	 * 过滤包下面的类
	 */
	public void registerFilters() {
		// 指定扫描的注解
		if (this.annotationClass != null) {
			this.addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
		}

	}

	/**
	 * 入口
	 */
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		if (beanDefinitions.isEmpty()) {
			logger.warn("no impl");
		} else {
			postProcessBeanDefinitions(beanDefinitions);
		}
		return beanDefinitions;
	}

	/**
	 * 主要方法
	 * 
	 * @param beanDefinitions
	 */
	private void postProcessBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			// 向mapperFactoryBean的构造器注入参数
			definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
			// 指定工厂类
			definition.setBeanClass(this.mapperFactoryBean.getClass());
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}

	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}

}
