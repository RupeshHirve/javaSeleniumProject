package com.GenericLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public String getExcelData(String sheetname, int rownum, int cellnum) throws Throwable {
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		DataFormatter format = new DataFormatter();
		Row row = sh.getRow(rownum);
		Cell cel = row.getCell(cellnum);
		String data = format.formatCellValue(cel);
		wb.close();
		return data;
	}

	public Object[][] getRowCount(String sheetname) throws Throwable {
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		int lastrow = sh.getLastRowNum();
		System.out.println(lastrow);
		int lastCell = sh.getRow(0).getLastCellNum();
		System.out.println(lastCell);
		DataFormatter format = new DataFormatter();
		Object[][] data = new Object[lastrow][lastCell];
		for (int i = 0; i < lastrow; i++) {
			for (int j = 0; j < lastCell; j++) {
				data[i][j] = format.formatCellValue(sh.getRow(i + 1).getCell(j));
			}
		}
		return data;
	}

	public void setDataForExcel(String sheetname, int rownum, int cellnum, String data) throws Throwable {
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row row = sh.getRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(IPathConstants.ExcelFilePath);
		wb.write(fos);
		wb.close();
	}
}
