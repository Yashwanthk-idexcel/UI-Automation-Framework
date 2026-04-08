package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.utility.JsonUtil;
import com.utility.PropertiesUtil;

import constants.Env;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_ATTEMPTS = Integer.parseInt(PropertiesUtil.getProperty(Env.QA, "MAX_ATTEMPTS"));
//	private static final int MAX_ATTEMPTS = JsonUtil.readUrlFromJson(Env.QA).getMAX_ATTEMPTS();

	private int currentAttempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempt <= MAX_ATTEMPTS) {
			currentAttempt++;
			return true;			
		}

		return false;
	}

}
