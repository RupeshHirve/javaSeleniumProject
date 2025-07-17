package com.GenericLibraries;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility
{
	public WebDriver driver;
	public static WebDriver staticdriver;
	//public JavascriptExecutor js=((JavascriptExecutor)driver);

	public void waitUntilPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	public void waitForElementToVisible(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void clickAfterElementToBeVisible(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(ele)).click();
	}
	
	public boolean returnFlagAfterVisibilityOfAllElements(WebDriver driver, WebElement eles) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElements(eles));
		boolean allVisible = true;
		for (WebElement ele : eleList) {
		    if (!wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed()) {
		        allVisible = false;
		        break;
		    }
		}
		return allVisible;
	}

	public boolean returnFlagAfterInVisibilityOfAllElements(WebDriver driver, WebElement eles) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Boolean eleList = wait.until(ExpectedConditions.invisibilityOfAllElements(eles));
		return eleList;
	}
	
	public void waitForElementToBeClickable(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver , 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void clickAfterElementToBeClickable(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver , 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForElementInvisible(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver , 10);
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void sendKeys(WebDriver driver, WebElement ele, String key)
	{
		WebDriverWait wait=new WebDriverWait(driver , 10);
		wait.until(ExpectedConditions.visibilityOf(ele)).sendKeys(key);
	}
	
	public void dropDownVisibleText(WebElement ele , String value)
	{
		Select s=new Select(ele);
		s.selectByVisibleText(value);
	}

	public void dropDownIndex(WebElement ele , int index)
	{
		Select s=new Select(ele);
		s.selectByIndex(index);
	}

	public void dropDownValue(WebElement ele , String value)
	{
		Select s=new Select(ele);
		s.selectByValue(value);
	}
	
//	public void dropdownOperate(WebDriver driver, WebElement element1, WebElement element2) throws InterruptedException {
//		web.clickAfterElementToBeVisible(driver, element1);
//		Thread.sleep(500);
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element2);
//		Thread.sleep(500);		
//	}
	
	public void mouseHover(WebDriver driver, WebElement ele)
	{
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
	}

	public void rigthClick(WebDriver driver, WebElement ele)
	{
		Actions a=new Actions(driver);
		a.contextClick(ele).perform();
	}

	public void DoubleClick(WebDriver driver, WebElement ele)
	{
		Actions a=new Actions(driver);
		a.doubleClick(ele).perform();
	}

	public void SwitchToWindow(WebDriver driver, String partialWinTitle)
	{
		Set<String> win = driver.getWindowHandles();
		Iterator<String> it = win.iterator();
		while(it.hasNext())
		{
			String winId = it.next();
			String title = driver.switchTo().window(winId).getTitle();
			if(title.contains(partialWinTitle))
			{
				break;
			}
		}
	}

	public void AcceptAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}

	public void DismissAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}

	public void switchFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchFrame(WebDriver driver, WebElement ele)
	{
		driver.switchTo().frame(ele);
	}
	public void switchFrame(WebDriver driver, String name)
	{
		driver.switchTo().frame(name);
	}

	public void javaScriptClick(WebElement ele, WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}

	public void javaScriptScrollBy(int x, int y, WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("scrollBy("+x+","+y+")","");
	}

	public void windowScroll(WebDriver driver, int x, int y)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(x,y)");
	}

	public void scrolToElement(WebDriver driver, WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ele);
	}
	
	/*public static void typeKey(String str) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, AWTException
	 {
	 Robot r=new Robot();
	 Field[] declaredFields = KeyEvent.class.getDeclaredFields();
	 char[] a = str.toCharArray();
	 for(char b:a)
	 {
	 int c=getKey(""+b);
	 r.keyPress(c);
	 r.keyRelease(c);
	 }
	 }*/
	
	public void robotKey(int n) throws AWTException
	{
		Robot r=new Robot();
		r.keyPress(n);
		r.keyRelease(n);
	}
	
	public boolean isPresent(WebElement ele)
	{
		try {
			ele.isDisplayed();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void slidingRight(WebElement ele, int count) throws Throwable
	{
		ele.click();
		while(count!=0)
		{
			robotKey(KeyEvent.VK_RIGHT);
			count--;
		}
	}
	public void slidingLeft(WebElement ele, int count) throws Throwable
	{
		ele.click();
		while(count!=0)
		{
			robotKey(KeyEvent.VK_LEFT);
			count--;
		}
	}
	public void javaScriptSendKeys(WebDriver driver, WebElement ele, String key)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].value='"+ key +"';", ele);
	}
	
	public void sendKeysAlternate(WebDriver driver, String xpath, String key) 
	{
		((JavascriptExecutor)driver).executeScript("document.getElementById('xpath').value='key'");
	}
	public void sendkeysAction(WebDriver driver,WebElement ele, String key)
	{
		Actions a=new Actions(driver);
		a.moveToElement(ele).click().sendKeys(key).perform();
	}
	public void sendkeysAction(WebDriver driver,WebElement ele, Keys key)
	{
		Actions a=new Actions(driver);
		a.moveToElement(ele).click().sendKeys(key).perform();
	}
	
	public void backSpaceWithCount(WebDriver driver, WebElement  ele, int count) throws InterruptedException {
		clickAfterElementToBeVisible(driver, ele);
		Thread.sleep(500);
		for (int i = 0; i <= count; i++) {
			ele.sendKeys(Keys.BACK_SPACE);
		}
	}
}

