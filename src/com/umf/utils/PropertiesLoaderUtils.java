package com.umf.utils;

import java.util.ResourceBundle;

public class PropertiesLoaderUtils {

	private static ResourceBundle resource = ResourceBundle.getBundle("config");
	
	public static String get(String key){
		return resource.getString(key);
	}
}
