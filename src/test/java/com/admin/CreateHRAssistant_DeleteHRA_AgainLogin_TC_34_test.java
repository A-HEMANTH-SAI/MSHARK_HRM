package com.admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.hrm.genericutils.BaseClass;
import com.hrm.objectRepo.AddAdminPage;
import com.hrm.objectRepo.AdminPage;
import com.hrm.objectRepo.DeleteAdminPage;
import com.hrm.objectRepo.HomePage;
import com.hrm.objectRepo.LoginPage;

//E2E [ Create HR Assistant and check for login and log out of new HR Assistant, then delete that New HR Assistant and try to login it ]
public class CreateHRAssistant_DeleteHRA_AgainLogin_TC_34_test extends BaseClass {
	
	@Test(groups = "system")
	public void tc_34_test() throws IOException {
		// Read common data from the Properties file
		String url = puObj.readDataFromPropertiesFile("url");
		String userEmail = puObj.readDataFromPropertiesFile("userEmail");
		String password = puObj.readDataFromPropertiesFile("password");
		driver.get(url);
		// Login to Application as HR Head
		LoginPage lp = new LoginPage(driver);
		lp.hrHeadLogin(userEmail, password);
		// Verify the Alert message using Assert and Accept the Alert
		String expectedPopupMessage;
		expectedPopupMessage = euObj.readExcelData("TC_34", 2, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// From Home page Go to Admin page
		HomePage hp = new HomePage(driver);
		// From Home page navigate to Admin page
		hp.navigateToAddAdmin();
		// Add New Admin to the Application with position as HR Assistant
		AdminPage ap = new AdminPage(driver);
		ap.getAddAdminButton().click();
		AddAdminPage aap = new AddAdminPage(driver);
		String companyId = euObj.readExcelData("TC_34", 2, 1);
		aap.getCompanyIdTextfield().sendKeys(companyId);
		String adminFirstName = euObj.readExcelData("TC_34", 3, 1);
		aap.getFirstNameTextfield().sendKeys(adminFirstName);
		String adminLastName = euObj.readExcelData("TC_34", 4, 1);
		aap.getLastNameTextfield().sendKeys(adminLastName);
		String adminMiddleName = euObj.readExcelData("TC_34", 5, 1);
		aap.getMiddleNameTextfield().sendKeys(adminMiddleName);
		String adminContactNo = euObj.readExcelData("TC_34", 6, 1);
		aap.getContactNoTextfield().sendKeys(adminContactNo);
		String hrPositionType = euObj.readExcelData("TC_34", 2, 4);
		aap.selectHrPositionByValue(driver, hrPositionType);
		String adminEmailAddress = euObj.readExcelData("TC_34", 7, 1);
		aap.getEmailAddressTextfield().sendKeys(adminEmailAddress);
		String adminPassword = euObj.readExcelData("TC_34", 8, 1);
		aap.getPasswordTextfield().sendKeys(adminPassword);
		aap.getSaveButton().click();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 11, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 3, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Login to the Application as HR Assistant (using Previously added Admin credentials)
		lp.hrAssistantLogin(adminEmailAddress, adminPassword);
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 2, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Verify the Login process using current User Email Id displayed in User's Home Page
		String currentUserEmailId = hp.getCurrentUserEMailId();
		if (currentUserEmailId.equals(adminEmailAddress)) {
			System.out.println(
					"PASS : Newly created HR Assistant successfully login to the application using the valid credential and is verified.");
		} else {
			System.err.println(
					"FAIL : Newly created HR Assistant unable to login to the application using the valid credentials and is verified");
		}
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 3, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Login to the Application as HR Head
		lp.hrHeadLogin(userEmail, password);
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 2, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Go to Admin page
		hp.navigateToAddAdmin();
		// Delete the previously created Admin (HR Assistant)
		String assistantAdminFirstName = euObj.readExcelData("TC_34", 3, 1);
		ap.getSearchTextfield().sendKeys(assistantAdminFirstName);
		String assistantAdminCompanyId = euObj.readExcelData("TC_34", 2, 1);
		
		
		wuObj.waitUntilElementGetsClickable(driver, driver.findElement(By.xpath("//td[.='"+assistantAdminCompanyId+"']/following-sibling::td[.='"+assistantAdminFirstName+"']/following-sibling::td[7]/i[@title='Delete Employee']")), 20);
		
		ap.clickOnDeleteAdminIcon(driver, assistantAdminFirstName, assistantAdminCompanyId);
		DeleteAdminPage dap = new DeleteAdminPage(driver);
		dap.getDeleteButton().click();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 12, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 3, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Login to Application as HR Assistant using Deleted HR Assistant credentials
		lp.hrAssistantLogin(adminEmailAddress, adminPassword);
		// Verify the Alert message and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_34", 4, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		Reporter.log("****TC34 Executed Completely****",true);
	}
}