package com.hrofficer;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.hrm.genericutils.BaseClass;
import com.hrm.objectRepo.HomePage;
import com.hrm.objectRepo.LoginPage;

// Smoke [ Login to Application as HR Officer ] --> TestScript dependent on TC_01
public class HrOfficerLogin_TC_02_test extends BaseClass {
	
	@Test (groups = "smoke")
	public void tc_02_test() throws IOException {
		
		// Retrieve common data from Properties file or Excel for Login
		String url = puObj.readDataFromPropertiesFile("url");
		String hrOfficerUserEmail = euObj.readExcelData("TC_02", 2, 1);
		String hrOfficerPassword = euObj.readExcelData("TC_02", 3, 1);
		// Trigger the URL
		driver.get(url);
		// Add implicit wait for WebElements
		wuObj.waitForAllElementsToLoad(driver, 10);
		// Login to the Application as HR Officer (using existing HR Officer credentials)
		LoginPage lp = new LoginPage(driver);
		lp.hrOfficerLogin(hrOfficerUserEmail, hrOfficerPassword);
		// Verify the Alert message using Assert and Accept the Alert
		String expectedPopupMessage;
		expectedPopupMessage = euObj.readExcelData("TC_02", 2, 9);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Verify HR Officer is able to Login to the Application or not by validating Title of page
		String expPageTitle = euObj.readExcelData("TC_02", 2, 6);
		wuObj.verifyTitle(driver, 10, expPageTitle);
		HomePage hp = new HomePage(driver);
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_02", 3, 9);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		Reporter.log("****TC02 Executed Completely****",true);
	}
}