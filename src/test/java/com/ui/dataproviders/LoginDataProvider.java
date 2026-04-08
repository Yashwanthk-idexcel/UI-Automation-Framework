package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CsvReaderUtil;
import com.utility.ExcelReaderUtil;

public class LoginDataProvider {

	@DataProvider(name = "LoginTestJsonDataProvider")
	public Iterator<Object[]> loginJsonDataProvider() {
		
		Gson gson = new Gson();
		FileReader fr = null;
		File testDataFile;
		try {
			testDataFile = new File(System.getProperty("user.dir")+"\\testData\\loginData.json");
			fr = new FileReader(testDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		TestData data = gson.fromJson(fr, TestData.class);
		
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		
		for ( User user : data.getData()) {
			dataToReturn.add(new Object[] { user });
		}
		
		return dataToReturn.iterator();
		
		/**
		 * What is new Object[] {} ?
		 * Say: It’s an inline way of creating and initializing an Object array, commonly used in varargs, TestNG DataProviders, and generic method handling.”
		 */
	}
	
	@DataProvider(name = "LoginTestCsvDataProvider")
	public Iterator<User> loginCsvDataProvider() {
		return CsvReaderUtil.readCsvFile("loginData.csv");
	}
	
	@DataProvider(name = "LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtil.readExcelFile("loginData.xlsx");
	}
}
