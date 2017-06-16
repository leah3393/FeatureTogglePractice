package com.featuretogglemanager.sql;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class SQLBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private ConfigurableListableBeanFactory configurableBeanFactory;
	
	@Override
	public Object postProcessAfterInitialization(Object obj, String arg1) throws BeansException {
		return obj;
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String arg1) throws BeansException {
		ReflectionUtils.doWithFields(obj.getClass(), new SQLFieldCallback(configurableBeanFactory, obj));
		return obj;
	}

}
