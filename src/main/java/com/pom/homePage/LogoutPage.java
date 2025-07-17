package com.pom.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	public LogoutPage(WebDriver driver){
	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//p[@class=\"oxd-userdropdown-name\"]")
	private WebElement logoutDD;
	
	@FindBy(xpath="//a[text()=\"Logout\"]")
	private WebElement logoutDD_value;

	public WebElement getLogoutDD() {
		return logoutDD;
	}

	public WebElement getLogoutDD_value() {
		return logoutDD_value;
	}
}
