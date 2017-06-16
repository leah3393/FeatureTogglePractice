package com.featuretogglemanager.sql;

import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class SQLFieldCallback implements FieldCallback {
	private Object bean;
	
	public SQLFieldCallback(ConfigurableListableBeanFactory configurableBeanFactory, Object obj) {
		this.bean = obj;
	}

	@Override
	public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
		if(!field.isAnnotationPresent(SQL.class)){
			return;
		}
		
		ReflectionUtils.makeAccessible(field);
        String sqlFileName = field.getDeclaredAnnotation(SQL.class).sqlFileName();
        
        String sql = "";
		try {
			sql = parseSQL(sqlFileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        
        field.set(bean, sql);        
    }
	
	private String parseSQL(String sqlFileName) throws IOException{
		Resource resource = new ClassPathResource(sqlFileName);
		return IOUtils.toString(resource.getInputStream(), "UTF-8"); 
	}
}
