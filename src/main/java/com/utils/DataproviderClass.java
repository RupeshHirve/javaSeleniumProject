package com.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.GenericLibraries.BaseClass;
//import com.nisan.nmt.testbase.Base;

public class DataproviderClass extends BaseClass{
//	@DataProvider(name="partdp")
//	public static Object[][] getDataFromDataproviderPart(Method m){
//		String sheetName=m.getName();
//		int rows=partExcel.getRowCount(sheetName);
//		int cols=partExcel.getColumnCount(sheetName);
//		Object[][] data=new Object[rows-1][cols];
//
//		for (int rownum = 2; rownum <= rows; rownum++) {
//			for (int colnum = 0; colnum < cols; colnum++) {
//				data[rownum-2][colnum]=partExcel.getCellData(sheetName, colnum, rownum);
//			}
//		}
//		return data;
//	}
	
	@DataProvider(name="dp")
	public static Object[][] getDataFromDataprovider(Method m){
		String sheetName=m.getName();
		int rows=excelTestData.getRowCount(sheetName);
		int cols=excelTestData.getColumnCount(sheetName);
		Object[][] data=new Object[rows-1][cols];

		for (int rownum = 2; rownum <= rows; rownum++) {
			for (int colnum = 0; colnum < cols; colnum++) {
				data[rownum-2][colnum]=excelTestData.getCellData(sheetName, colnum, rownum);
			}
		}
		return data;
	}
}