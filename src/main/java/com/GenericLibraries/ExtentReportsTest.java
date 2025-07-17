package com.GenericLibraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsTest {
	static ExtentReports extent;

	public static ExtentReports ExtendsReports() {
		String path = System.getProperty("user.dir") + "\\reports\\extentReportOrangeHRM.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Test");
		reporter.config().setDocumentTitle("Test Result");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rupesh Hirve");
		
		return extent;

	}
}
