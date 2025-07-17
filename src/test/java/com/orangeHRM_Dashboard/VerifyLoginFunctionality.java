package com.orangeHRM_Dashboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericLibraries.BaseClass;
import com.pom.homePage.LoginPage;

public class VerifyLoginFunctionality extends BaseClass {

	@Test
	public void TC001_vlaidLoginTest() throws Throwable {
		log.info("TC001_vlaidLoginTest started");
		LoginPage lp = new LoginPage(getDriver());
		lp.loginWithAdmin(getDriver());
		String act = getDriver().getTitle();
		System.out.println("Actual Page Title is " + act);
		String exp = json.ReadDataFromJson("pageTitle");
		System.out.println("Expected Page Title is " + exp);

		Assert.assertEquals(act, exp, "TC001_vlaidLoginTest Failed");
		System.out.println("Title verification successful");
		Logout(getDriver());
		log.info("Valid login test successfull");
		// Read data from excel test
//		String data_0_0 = excel.getExcelData("testData", 0, 0);
//		System.out.println("excel data " + data_0_0);
	}

	@Test
	public void TC002_InvalidLogin() throws Throwable {
		log.info("TC002_InvalidLogin Started");
		LoginPage lp = new LoginPage(getDriver());
		lp.invalidLogin(getDriver());
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		boolean flag = wait.until(ExpectedConditions.visibilityOf(lp.getInvalidCredentials())).isDisplayed();
		Assert.assertEquals(flag, true, "TC002_InvalidLogin Failed");
		log.info("Login with invalis credentials successfull");
	}

	@Test
	public void TC003_blank_username_password_fields() throws Throwable {
		log.info("TC003_blank_username_password_fields Started");
		LoginPage lp = new LoginPage(getDriver());
		lp.login_with_blank_username_password(getDriver());
		boolean requiredUsernameFlag = web.isPresent(lp.getRequired_username());
		boolean requiredPasswordFlag = web.isPresent(lp.getRequired_password());
		boolean finalFLag = requiredUsernameFlag && requiredPasswordFlag;
		Assert.assertEquals(finalFLag, true, "TC003_blank_username_password_fields failed");
		log.info("Login with blank username and password verified successfully");
	}

	@Test
	public void TC004_TestForgotPasswordLink() throws Throwable {
		log.info("TC004_TestForgotPasswordLink Started");
		LoginPage lp = new LoginPage(getDriver());
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.visibilityOf(lp.getForgotPasswordLink())).click();
		Thread.sleep(2000);
		getDriver().switchTo().defaultContent();
		boolean pageElementFlag = web.isPresent(lp.getResetPasswordPage());
		Assert.assertEquals(pageElementFlag, true, "TC004_TestForgotPasswordLink failed");
		log.info("Reset password link verified successfully");
	}
}
