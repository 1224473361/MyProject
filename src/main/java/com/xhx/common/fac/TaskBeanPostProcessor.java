package com.xhx.common.fac;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * 在bean初始化完后将bean存入工厂内
 * 
 * @date 2019年8月21日
 * @author xhx
 */
//@Component
public class TaskBeanPostProcessor implements BeanPostProcessor {
	

	@Resource
	private TaskDealFactory taskDealFactory;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		/*if (bean instanceof TaskDealService) {
			this.taskDealFactory.addImpl((TaskDealService) bean);
			log.info("{}工厂加载【{}】",this.getClass().getName(), bean.getClass());
		}*/
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
