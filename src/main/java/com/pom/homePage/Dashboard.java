package com.pom.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GenericLibraries.BaseClass;

public class Dashboard extends BaseClass {

	public Dashboard(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(@class,'main-menu-item') and text()='Admin']")
	private WebElement mainMenuAdmin;

	@FindBy(xpath = "//i[contains(@class,'plus')]//parent::button")
	private WebElement addButtonUsernameTabAdmin;

	@FindBy(xpath = "//i[contains(@class,'plus')]//parent::button")
	private WebElement addButtonEmpListTabPIM;

	@FindBy(xpath = "//label[text()='User Role']//ancestor::div[contains(@class,'input-field-bottom-space')]//div[@class='oxd-select-text-input']")
	private WebElement userRoleDD;

	@FindBy(xpath = "//label[text()='Status']//ancestor::div[contains(@class,'input-field-bottom-space')]//div[@class='oxd-select-text-input']")
	private WebElement statusDD;

	@FindBy(xpath = "//span[contains(@class,'main-menu-item') and text()='PIM']")
	private WebElement mainMainuPIM;

	@FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card']")
	private WebElement employeeList;

	@FindBy(xpath = "//a[@href=\"/web/index.php/pim/viewMyDetails\"]")
	private WebElement MyInfoLink;

	@FindBy(xpath = "//input[@name=\"firstName\"]")
	private WebElement FirstNameTF;

	@FindBy(xpath = "//label[text()='Employee Name']//ancestor::div[contains(@class,'input-field-bottom-space')]//input")
	private WebElement employeeNameTF;

	@FindBy(xpath = "//label[text()='Username']//ancestor::div[contains(@class,'input-field-bottom-space')]//input")
	private WebElement employeeUsernameTF;

	@FindBy(xpath = "//label[text()='Password']//ancestor::div[contains(@class,'input-field-bottom-space')]//input")
	private WebElement employeePasswordTF;

	@FindBy(xpath = "//label[text()='Confirm Password']//ancestor::div[contains(@class,'input-field-bottom-space')]//input")
	private WebElement employeeConfirmPasswordTF;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement addEmployeeSaveBtn;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchBtn;

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement firstNameAddEmployeeTF;

	@FindBy(xpath = "//input[@name='middleName']")
	private WebElement middleNameAddEmployeeTF;

	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement lastNameAddEmployeeTF;

	@FindBy(xpath = "//label[text()='Employee Id']//ancestor::div[contains(@class,'input-field-bottom-space')]//input")
	private WebElement empIDAddEmployeeTF;

	@FindBy(xpath = "(//div[contains(@class,'table-row')]//i[contains(@class,'checkbox-input-icon')])[last()]//parent::span[contains(@class,'checkbox-input')]")
	private WebElement checkboxCheckbox;

	@FindBy(xpath = "//div[contains(@class,'table-row')]//i[contains(@class,'bi-trash')]")
	private WebElement deleteIcon;

	@FindBy(xpath = "//div[contains(@class,'orangehrm-dialog-popup')]//button[contains(@class,'danger')]")
	private WebElement popUpDeleteBtn;

	public WebElement getMainMenuAdmin() {
		return mainMenuAdmin;
	}

	public WebElement getAddButtonUsernameTab() {
		return addButtonUsernameTabAdmin;
	}

	public WebElement getAddButtonUsernameTabAdmin() {
		return addButtonUsernameTabAdmin;
	}

	public WebElement getAddButtonEmpListTabPIM() {
		return addButtonEmpListTabPIM;
	}

	public WebElement getUserRoleDD() {
		return userRoleDD;
	}

	public WebElement getStatusDD() {
		return statusDD;
	}

	public WebElement getMyInfoLink() {
		return MyInfoLink;
	}

	public WebElement getFirstNameTF() {
		return FirstNameTF;
	}

	public WebElement getEmployeeNameTF() {
		return employeeNameTF;
	}

	public WebElement getEmployeeUsernameTF() {
		return employeeUsernameTF;
	}

	public WebElement getEmployeePasswordTF() {
		return employeePasswordTF;
	}

	public WebElement getEmployeeConfirmPasswordTF() {
		return employeeConfirmPasswordTF;
	}

	public WebElement getAddEmployeeSaveBtn() {
		return addEmployeeSaveBtn;
	}

	public WebElement getMainMainuPIM() {
		return mainMainuPIM;
	}

	public WebElement getEmployeeList() {
		return employeeList;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getFirstNameAddEmployeeTF() {
		return firstNameAddEmployeeTF;
	}

	public WebElement getMiddleNameAddEmployeeTF() {
		return middleNameAddEmployeeTF;
	}

	public WebElement getLastNameAddEmployeeTF() {
		return lastNameAddEmployeeTF;
	}

	public WebElement getEmpIDAddEmployeeTF() {
		return empIDAddEmployeeTF;
	}

	public WebElement getCheckboxCheckbox() {
		return checkboxCheckbox;
	}

	public WebElement getDeleteIcon() {
		return deleteIcon;
	}

	public WebElement getPopUpDeleteBtn() {
		return popUpDeleteBtn;
	}

	public void addUserRoleToUser(WebDriver driver, String role, String status, String empName, String empUserName,
			String empPassword, String emplConfirmPassword) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		web.clickAfterElementToBeVisible(driver, mainMenuAdmin);
		web.clickAfterElementToBeVisible(driver, addButtonUsernameTabAdmin);
		web.clickAfterElementToBeVisible(driver, userRoleDD);
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + role + "']"))).click();
		Thread.sleep(500);
		web.clickAfterElementToBeVisible(driver, statusDD);
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + status + "']"))).click();
		Thread.sleep(500);
		web.sendKeys(driver, employeeNameTF, empName);
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + empName + "']"))).click();
		web.sendKeys(driver, employeeUsernameTF, empUserName);
		web.sendKeys(driver, employeePasswordTF, empPassword);
		web.sendKeys(driver, employeeConfirmPasswordTF, emplConfirmPassword);
		web.clickAfterElementToBeVisible(driver, addEmployeeSaveBtn);
		Thread.sleep(500);
	}

	public void addNewUser(WebDriver driver, String firstName, String middleName, String lastName, int empId,
			int backSpaceCount) throws InterruptedException {
		web.clickAfterElementToBeVisible(driver, mainMainuPIM);
		web.clickAfterElementToBeVisible(driver, addButtonEmpListTabPIM);
		web.sendKeys(driver, firstNameAddEmployeeTF, firstName);
		web.sendKeys(driver, middleNameAddEmployeeTF, middleName);
		web.sendKeys(driver, lastNameAddEmployeeTF, lastName);
		web.backSpaceWithCount(driver, empIDAddEmployeeTF, backSpaceCount);
		web.sendKeys(driver, empIDAddEmployeeTF, String.valueOf(empId));
		web.clickAfterElementToBeVisible(driver, addEmployeeSaveBtn);
	}

	public void deleteUser(WebDriver driver, String username) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		web.clickAfterElementToBeVisible(getDriver(), getMainMenuAdmin());
		web.sendKeys(getDriver(), getEmployeeUsernameTF(), username);
		web.clickAfterElementToBeVisible(getDriver(), getSearchBtn());
		web.clickAfterElementToBeVisible(driver, getCheckboxCheckbox());
		web.clickAfterElementToBeVisible(driver, getDeleteIcon());
		web.clickAfterElementToBeVisible(driver, getPopUpDeleteBtn());
	}

}
