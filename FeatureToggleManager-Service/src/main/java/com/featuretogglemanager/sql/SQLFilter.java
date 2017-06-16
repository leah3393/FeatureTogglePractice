package com.featuretogglemanager.sql;

public class SQLFilter {
	String fieldName;
	String namedParam;
	String paramValue;
	
	public SQLFilter(String fieldName, String namedParam, String paramValue){
		this.fieldName = fieldName;
		this.namedParam = namedParam;
		this.paramValue = paramValue;
	}
	
	public SQLFilter(){}
	
	@Override
	public String toString(){
		return "\"" + fieldName + "\" = :" + namedParam;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getNamedParam() {
		return namedParam;
	}

	public void setNamedParam(String namedParam) {
		this.namedParam = namedParam;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
}
