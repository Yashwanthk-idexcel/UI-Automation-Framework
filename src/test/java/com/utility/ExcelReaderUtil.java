package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtil {

	public static Iterator<User> readExcelFile(String fileName){

		File excelFile = new File(System.getProperty("user.dir") + "//testData//" + fileName);
		XSSFWorkbook workbook = null;
		XSSFSheet sheet;
		Iterator<Row> rowiterator;
		List<User> userList = null;
		Row row;
		User user;
		
		try {
			workbook = new XSSFWorkbook(excelFile);
			sheet = workbook.getSheet("LoginTestData");
			rowiterator = sheet.iterator();
			userList = new ArrayList<User>();

			rowiterator.next(); // Skip the row 1 data
			while (rowiterator.hasNext()) {
				row = rowiterator.next();
				user = new User(row.getCell(0).toString(), row.getCell(1).toString());
				userList.add(user);
			}

			workbook.close();
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();
	}

}
