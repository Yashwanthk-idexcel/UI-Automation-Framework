package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CsvReaderUtil {

	public static Iterator<User> readCsvFile(String fileName) {

		File csvFile = new File(System.getProperty("user.dir") + "//testData//" + fileName);
		FileReader fr = null;
		CSVReader reader;
		String[] data;
		User user;
		List<User> userList = null;;

		try {
			fr = new FileReader(csvFile);
			reader = new CSVReader(fr);

			userList = new ArrayList<User>();
			reader.readNext(); // Skip (Row 1) column name, i don't want to store column names

			while ((data = reader.readNext()) != null) {
				user = new User(data[0], data[1]);
				userList.add(user);
			}
			
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();
	}

}
