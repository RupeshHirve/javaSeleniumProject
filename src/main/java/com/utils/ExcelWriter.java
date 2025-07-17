package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.GenericLibraries.BaseClass;
//import com.GenericLibraries.Log;

public class ExcelWriter extends BaseClass {
	/*
	 * Goal: Code to write a data in to excel Author: Viraj Dange Excel File: Used
	 * only one file name "NMTWriteData" for data writing. Having separate sheet for
	 * sales,service and parts.
	 */
	
	public void writeToExcel(String sheetName, String key, String dataToWrite) throws IOException {
		// File Location
		String filePath = System.getProperty("user.dir") + "/src/main/resources/excelTestData";
		// File Name
		String fileName = "NMTWriteData.xlsx";
		// String sheetName="dasd";
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook book = null;
		// Checking a file extension.
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			book = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			book = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = book.getSheet(sheetName);
		// Taking row count
		int rowCount = sheet.getLastRowNum();
		// for loop for row
		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			// Taking key value from excel
			String keyActual = row.getCell(0).getStringCellValue();
			// Checking for the required Key in excel
			if (keyActual.equals(key)) {
				// New cell created to inserted data.
				Cell cell = sheet.getRow(i).createCell(1);
				// To write in cell
				cell.setCellValue(dataToWrite);
				log.info("Data Inserted successfully....");
				break;
			}
		}

		// Closing the excel file...
		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(file);
		book.write(outputStream);
		outputStream.close();

	}

	public static String readToExcel(String sheetName, String key) throws IOException {

		String cell = null;
		// File Location
		String filePath = System.getProperty("user.dir") + "/src/main/resources/excelTestData";

		// File Name
		String fileName = "NMTWriteData.xlsx";
		// String sheetName="dasd";

		File file = new File(filePath + "\\" + fileName);

		FileInputStream inputStream = new FileInputStream(file);
		Workbook book = null;

		// Checking a file extension.
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			book = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			book = new HSSFWorkbook(inputStream);
		}

		Sheet sheet = book.getSheet(sheetName);

		// Taking row count
		int rowCount = sheet.getLastRowNum();

		// for loop for row
		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);

			// Taking key value from excel
			String keyActual = row.getCell(0).getStringCellValue();

			// Checking for the required Key in excel
			if (keyActual.equals(key)) {

				// New cell created to inserted data.
				// Cell cell = sheet.getRow(i).createCell(1);
				cell = sheet.getRow(i).getCell(1).getStringCellValue();

				// To write in cell
				// cell.setCellValue(dataToWrite);

				log.info("Data Inserted successfully....");
				break;
			}
		}

		// Closing the excel file...
		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(file);
		book.write(outputStream);
		outputStream.close();

		return cell;

	}

}
