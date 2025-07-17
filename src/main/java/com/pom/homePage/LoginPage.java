package com.pom.homePage;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericLibraries.BaseClass;

public class LoginPage extends BaseClass{
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement usernameTF;
	
	@FindBy(name="password")
	private WebElement passwordTF;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginBtn;

	@FindBy(xpath="//i[contains(@class,'alert-content-icon')]//following-sibling::p")
	private WebElement invalidCredentials;
	
	@FindBy(xpath="//div[@class='orangehrm-login-forgot']//p[text()='Forgot your password? ']")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath="//*[text()='Reset Password']")
	private WebElement resetPasswordPage;
	
	@FindBy(xpath="//label[text()='Username']//ancestor::div[contains(@class,'input-field-bottom-space')]//span[contains(@class,'input-group__message')]")
	private WebElement required_username;
	
	@FindBy(xpath="//label[text()='Password']//ancestor::div[contains(@class,'input-field-bottom-space')]//span[contains(@class,'input-group__message')]")
	private WebElement required_password;
	

	public WebElement getResetPasswordPage() {
		return resetPasswordPage;
	}
	
	public WebElement getForgotPasswordLink() {
		return forgotPasswordLink;
	}
	
	public WebElement getInvalidCredentials() {
		return invalidCredentials;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public WebElement getUsernameTF() {
		return usernameTF;
	}
	
	public WebElement getPasswordTF() {
		return passwordTF;
	}
	
	public WebElement getRequired_username() {
		return required_username;
	}

	public WebElement getRequired_password() {
		return required_password;
	}
	
	
	public void invalidLogin(WebDriver driver) throws Throwable {
		web.sendKeys(driver, usernameTF, excel.getExcelData("LoginTest", 2, 1));
		web.sendKeys(driver, passwordTF, excel.getExcelData("LoginTest", 2, 2));
		web.clickAfterElementToBeVisible(driver, loginBtn);
		Thread.sleep(500);
	}
	
	public void loginWithAdmin(WebDriver driver) throws InterruptedException, IOException, ParseException {
//		usernameTF.sendKeys(json.ReadDataFromJson("username"));
//		passwordTF.sendKeys(json.ReadDataFromJson("password"));
//		loginBtn.click();
//        Thread.sleep(4000);
		web.sendKeys(driver, usernameTF, json.ReadDataFromJson("username"));
		web.sendKeys(driver, passwordTF, json.ReadDataFromJson("password"));
		web.clickAfterElementToBeVisible(driver, loginBtn);
	}
	
	public void login_with_blank_username_password(WebDriver driver) throws InterruptedException {
		web.clickAfterElementToBeVisible(driver, loginBtn);
	}
	
	public void ESS_login(WebDriver driver, String username, String password) {
		web.sendKeys(driver, usernameTF, username);
		web.sendKeys(driver, passwordTF, password);
		web.clickAfterElementToBeVisible(driver, loginBtn);
	}
	
}
