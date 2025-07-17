package com.orangeHRM_Dashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericLibraries.BaseClass;
import com.pom.homePage.Dashboard;
import com.pom.homePage.LoginPage;
import com.utils.DataproviderClass;

public class VerifyAccessControl extends BaseClass{
	
	@Test(dataProviderClass = DataproviderClass.class, dataProvider = "dp", enabled = true)
	public void TC001_accessControlValidation(String username, String password) throws InterruptedException{
		log.info("TC001_accessControlValidation from VerifyAccessControl started");
		LoginPage lp = new LoginPage(getDriver());
		lp.ESS_login(getDriver(), username, password);
		Dashboard db = new Dashboard(getDriver());
		web.waitForElementToVisible(getDriver(), db.getMyInfoLink());
		
		List<WebElement> elements = getDriver().findElements(By.xpath("//span[contains(@class,'main-menu-item') and text()='Admin']"));
		Assert.assertTrue(elements.size() == 0, "Element should not be present, but it is.");
		Logout(getDriver());
		log.info("TC001_accessControlValidation from VerifyAccessControl successful");
	}

}
