package com.GenericLibraries;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners implements ITestListener {
	ExtentReports reports;
	ExtentTest test;
//	BaseClass bc = new BaseClass();

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName() + " is failed");
		test.log(Status.FAIL, result.getThrowable());

		try {
			BaseClass base = new BaseClass(); // if needed for utility access
			String path = base.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(path);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, result.getMethod().getMethodName() + "is passed");
//		test.log(Status.PASS, result.getThrowable());
//		try {
//			BaseClass base = new BaseClass(); // if needed for utility access
//			String path = base.getScreenshot(result.getName());
//			test.addScreenCaptureFromPath(path);
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
	}

	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, result.getMethod().getMethodName() + "is skipped");
		try {
			BaseClass base = new BaseClass(); // if needed for utility access
			String path = base.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(path);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "./reports/extentReportOrangeHRM.html");
		reporter.config().setDocumentTitle("OrangeHRM");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("SampleReport");
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Build no", "1");
		reports.setSystemInfo("Env", "UAT");
		reports.setSystemInfo("Platform", "CHROME");
		reports.setSystemInfo("Browser version", "137");
	}

	public void onFinish(ITestContext context) {
		reports.flush();
	}
}