package com.orangeHRM_Dashboard;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.GenericLibraries.BaseClass;
import com.GenericLibraries.Log;
import com.pom.homePage.Dashboard;
import com.pom.homePage.LoginPage;
import com.utils.DataproviderClass;

public class VerifyUserManagementAdminModule extends BaseClass{
	
	@Test
	public void TC001_viewUserList() throws InterruptedException, IOException, ParseException {
		log.info("TC001_viewUserList from VerifyUserManagementAdminModule started");
		LoginPage lp = new LoginPage(getDriver());
		lp.loginWithAdmin(getDriver());
		
		Dashboard db= new Dashboard(getDriver());
		web.clickAfterElementToBeVisible(getDriver(), db.getMainMenuAdmin());
		boolean allVisible = web.returnFlagAfterVisibilityOfAllElements(getDriver(), db.getEmployeeList());
		Assert.assertTrue(allVisible, "Not all employee list elements are visible.");
		log.info("TC001_viewUserList from VerifyUserManagementAdminModule passed successfully");
	}
	
	@Test
	public void TC002_testUsersExist() throws InterruptedException {
		log.info("TC002_testUsersExist from VerifyUserManagementAdminModule started");
		WebDriverWait wait= new WebDriverWait(getDriver(), 10);
		Dashboard db= new Dashboard(getDriver());
		web.clickAfterElementToBeVisible(getDriver(), db.getUserRoleDD());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Admin']"))).click();
		web.clickAfterElementToBeClickable(getDriver(), db.getSearchBtn());
		Thread.sleep(500);
		boolean allUsersVisible = web.returnFlagAfterVisibilityOfAllElements(getDriver(), db.getEmployeeList());
		Assert.assertTrue(allUsersVisible, "Not all employee list elements are visible.");
		log.info("TC002_testUsersExist from VerifyUserManagementAdminModule passed successfully");
	}
	
	@Test(dataProviderClass= DataproviderClass.class, dataProvider = "dp", enabled = true )
	public void TC003_testAddNewEmployee(String firstName, String middleName, String lastName) throws InterruptedException, IOException, ParseException {
		log.info("TC001_testAddNewEmployee from VerifyEmployeeManagementFromPIM_Module started");
		getDriver().switchTo().defaultContent();
		int backSpaceCount = 7;
		int empId = java.getRandomNum();
		Thread.sleep(2000);
		Dashboard db = new Dashboard(getDriver());
		web.clickAfterElementToBeClickable(getDriver(), db.getMainMainuPIM());
		db.addNewUser(getDriver(), firstName, middleName, lastName, empId, backSpaceCount);
		Thread.sleep(1000);		
		boolean statusFlag = web.returnFlagAfterVisibilityOfAllElements(getDriver(), db.getFirstNameAddEmployeeTF());
		Assert.assertTrue(statusFlag, "Creation of new employee failed");
		log.info("TC001_testAddNewEmployee from VerifyEmployeeManagementFromPIM_Module passed successfully");
	}
	
	@Test(dataProviderClass = DataproviderClass.class, dataProvider = "dp", enabled = true)
	public void TC004_addNewUser(
			String role, String status, String name, String username, String password, String confirm_password) 
					throws InterruptedException, IOException, ParseException {
		log.info("TC004_addNewUser from VerifyEmployeeManagementFromPIM_Module started");
		WebDriverWait wait= new WebDriverWait(getDriver(), 10);
		Dashboard db= new Dashboard(getDriver());
		db.addUserRoleToUser(getDriver(), role, status, name, username, password, confirm_password);
		Thread.sleep(4000);
//		web.AcceptAlertPopup(getDriver());
//		Thread.sleep(2000);
		web.clickAfterElementToBeVisible(getDriver(), db.getUserRoleDD());
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + role + "']"))).click();
		web.clickAfterElementToBeVisible(getDriver(), db.getSearchBtn());
		WebElement search_result = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='" + username + "']")));
		boolean statusFlag = web.returnFlagAfterVisibilityOfAllElements(getDriver(), search_result);
		Assert.assertTrue(statusFlag, "Adding new user failed");
		Logout(getDriver());
		log.info("TC004_addNewUser from VerifyEmployeeManagementFromPIM_Module successful");
	}
}
