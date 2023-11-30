package com.hrm.genericutils;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	// Instantiate utility specific class objects
	public DatabaseUtils dbObj = new DatabaseUtils();
	public ExcelUtils euObj = new ExcelUtils();
	public JavaUtils juObj = new JavaUtils();
	public PropertyFileUtils puObj = new PropertyFileUtils();
	public WebDriverUtils wuObj = new WebDriverUtils();
	public WebDriver driver;
	public static WebDriver ssDriver;

	@BeforeSuite(alwaysRun = true)
	public void config_BS() throws SQLException {
		// Connect to the Database
		dbObj.connectToDatabase();
		Reporter.log("****Connected to DB****",true);
	}
	@BeforeClass(alwaysRun = true)
	public void config_BC() throws IOException {
		
		String browser = puObj.readDataFromPropertiesFile("browser");
		// Useful for compatability testing
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.err.println("Invalid Browser");
		}
		ssDriver = driver;
		// Maximize the Browser screen
		wuObj.maximizeBrowser(driver);
		// Wait for all WebElements to get loaded
		wuObj.waitForAllElementsToLoad(driver, 10);
	}

	@AfterClass(alwaysRun = true)
	public void config_AC() {
		// Terminate the session
		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void config_AS() throws SQLException {
		// Disconnect from Testing Environment Database
		dbObj.closeDatabaseConnection();
		Reporter.log("****Disconnected from DB****",true);
	}
}