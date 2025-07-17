package com.GenericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.pom.homePage.LoginPage;
import com.pom.homePage.LogoutPage;
import com.utils.ExcelReader;
import org.apache.log4j.Logger;
import com.GenericLibraries.Log;

public class BaseClass {

	// Thread-safe WebDriver instance
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public JsonUtility json = new JsonUtility();
	public JavaUtility java = new JavaUtility();
	public WebDriverUtility web = new WebDriverUtility();
	public ExcelFileUtility excel = new ExcelFileUtility();
//    public LoggerTest log= new LoggerTest();
	public static ExcelReader excelTestData = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/testCasesData.xlsx");
//    public static Logger logger;
	public static Logger log;

	public static WebDriver getDriver() {
		return driver.get();
	}
//    protected WebDriver driver() {
//        return getDriver();
//    }

	@BeforeClass
	public void LaunchingBrowser() throws IOException, ParseException, InterruptedException {

//    	logger = Log.initializeLogger(this.getClass());
		String browser = json.ReadDataFromJson("browser");

		WebDriver localDriver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", IPathConstants.chromeDriverExePath);

			// "Code for incognito mode"
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--disable-notifications");

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			chromeOptions.setExperimentalOption("prefs", prefs);

			localDriver = new ChromeDriver(chromeOptions);

//            localDriver = new ChromeDriver();   

		} else if (browser.equalsIgnoreCase("msedge")) {
			System.setProperty("webdriver.edge.driver", IPathConstants.edgeDriverExePath);
			localDriver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", IPathConstants.firefoxDriverExePath);
			localDriver = new FirefoxDriver();
		}

		localDriver.manage().window().maximize();
		localDriver.get(json.ReadDataFromJson("url"));
		Thread.sleep(4000);

		driver.set(localDriver); // Set thread-local driver
	}

//    @BeforeMethod
//    public void Login() throws IOException, ParseException, InterruptedException {
//        LoginPage lp = new LoginPage(getDriver());
//        lp.getUsernameTF().sendKeys(json.ReadDataFromJson("username"));
//        lp.getPasswordTF().sendKeys(json.ReadDataFromJson("password"));
//        lp.getLoginBtn().click();
//        Thread.sleep(4000);
//    }

	@BeforeMethod
	public void setUp() {
		log = Log.initializeLogger(this.getClass());
	}

//    @AfterMethod
	public void Logout(WebDriver driver) throws InterruptedException {
		LogoutPage lo = new LogoutPage(driver);
		web.clickAfterElementToBeVisible(driver, lo.getLogoutDD());
		web.clickAfterElementToBeVisible(driver, lo.getLogoutDD_value());
	}

	@AfterClass
	public void closingBrowser() throws InterruptedException {
		Thread.sleep(4000);
		getDriver().quit();
		driver.remove(); // Clean up ThreadLocal
	}

	public String getScreenshot(String name) throws Throwable {
		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String destfile = System.getProperty("user.dir") + "/screenshot/" + name + ".png";
		File finaldest = new File(destfile);
		FileUtils.copyFile(srcfile, finaldest);
		return destfile;
	}

}
