package com.featuretogglemanager.domain;

public class IdGenerator {
	public static final String PREFIX = "F-";
	public static long nextId = 1000L;
	public static int length = 4;
	
	public static synchronized String generateId(){
		if(Long.toString(nextId).length() > length){
			length++;
		}
		
		String id = PREFIX + String.format("%0"+length+"d", nextId);
		nextId++;
		
		return id;
	}
}
