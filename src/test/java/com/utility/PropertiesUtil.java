package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import constants.Env;

public class PropertiesUtil {

	public static String getProperty(Env env, String propertyName) {

		File file;
		FileReader fr;
		Properties prop = null;

		try {
			file = new File(System.getProperty("user.dir") + "//config//" + env + ".properties");
			fr = new FileReader(file);
			prop = new Properties();
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(propertyName.toUpperCase());

	}

}
