package com.orangeHRM_Dashboard;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericLibraries.BaseClass;
import com.pom.homePage.Dashboard;
import com.pom.homePage.LoginPage;

public class VerifyDeleteEmployee extends BaseClass{
	
	@Test
	public void TC001_deleteUser() throws InterruptedException, IOException, ParseException {
		log.info("TC001_deleteUser started");
		String username = json.ReadDataFromJson("essUsername");
		LoginPage lp = new LoginPage(getDriver());
		lp.loginWithAdmin(getDriver());
		
		Dashboard db = new Dashboard(getDriver());
		db.deleteUser(getDriver(), username);
		Thread.sleep(2000);
		List<WebElement> invisibilityOfEle = getDriver().findElements(By.xpath("//div[contains(@class,'table-row')]//div[text()='" + username + "']"));
		Assert.assertTrue(invisibilityOfEle.size() == 0, "Element should not be present, but it is.");
		log.info("TC001_deleteUser successful");
//		Logout(getDriver());
	}
}
