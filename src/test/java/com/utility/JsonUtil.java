package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import constants.Env;

public class JsonUtil {

	public static Environment readUrlFromJson(Env env) {

		Gson gson = new Gson();
		File file = new File(System.getProperty("user.dir") + "//config//config.json");
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Config config = gson.fromJson(fr, Config.class);
		
		Environment environment = config.getEnvironments().get(env.toString()); // Map<String, Environment> - so key should be in String
		return environment;
	}

}
