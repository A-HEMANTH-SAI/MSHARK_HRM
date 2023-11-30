package com.hrassistant;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.hrm.genericutils.BaseClass;
import com.hrm.objectRepo.EditEmployeePage;
import com.hrm.objectRepo.EmployeePage;
import com.hrm.objectRepo.HomePage;
import com.hrm.objectRepo.LoginPage;
// Integration [ Edit employee with HR Assintant and check the edited Employee details is reflecting in HR Head Employee list ] --> TestScript dependent on TC_01 and TC_54
public class EditEmployee_FromHRA_CheckFromHRH_TC_24_test extends BaseClass {
	
	@Test(groups = "integration")
	public void tc_24_test() throws IOException {
		
		// Retrieve common data from the Properties file
		String url = puObj.readDataFromPropertiesFile("url");
		// Trigger the URL
		driver.get(url);
		// Login to the Application as HR Assistant (using existing HR Assistant credentials)
		String hrAssistantUserEmail = euObj.readExcelData("TC_24", 3, 1);
		String hrAssistantPassword = euObj.readExcelData("TC_24", 4, 1);
		LoginPage lp = new LoginPage(driver);
		lp.hrAssistantLogin(hrAssistantUserEmail, hrAssistantPassword);
		// Verify the Alert message using Assert and Accept the Alert
		String expectedPopupMessage;
		expectedPopupMessage = euObj.readExcelData("TC_24", 2, 10);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		HomePage hp = new HomePage(driver);
		// From Home page Go to Employee page
		hp.navigateToAddEmployee();
		// Search for existing Employee and Edit the First Name of the Employee
		EmployeePage ep = new EmployeePage(driver);
		String employeeFirstName = euObj.readExcelData("TC_24", 3, 4);
		String employeeId = euObj.readExcelData("TC_24", 2, 4);
		ep.getSearchTextfield().sendKeys(employeeId);
		
		
		wuObj.waitUntilElementGetsClickable(driver, driver.findElement(By.xpath("//td[.='"+employeeId+"']/following-sibling::td[.='"+employeeFirstName+"']/following-sibling::td[6]/child::i[@title='Edit Employee']")), 20);
		
		
		ep.clickOnEditEmployeeIcon(driver, employeeFirstName, employeeId);
		String employeeNameToModify = euObj.readExcelData("TC_24", 2, 7);
		EditEmployeePage eep = new EditEmployeePage(driver);
		eep.getFirstNameTextfield().clear();
		eep.getFirstNameTextfield().sendKeys(employeeNameToModify);
		eep.uploadEmployeeFile("./src/test/resources/Test.docx");
		eep.uploadEmployeeProfilePicture("./src/test/resources/Test54.jpeg");
		eep.getUpdateButton().click();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_24", 16, 10);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_24", 3, 10);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Login to Application as HR Head
		String hrUserEmail = puObj.readDataFromPropertiesFile("userEmail");
		String hrPassword = puObj.readDataFromPropertiesFile("password");
		lp.hrHeadLogin(hrUserEmail, hrPassword);
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_24", 2, 10);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Go to Employee page
		hp.navigateToAddEmployee();
		// Search for Employee whose details has been updated & verify for updated Employee's First Name
		ep.getSearchTextfield().sendKeys(employeeId);
		String actualEmployeeFirstName = ep.getFirstEmployeeFirstNameFromList(driver, employeeId);
		Assert.assertEquals(actualEmployeeFirstName, employeeNameToModify, "Fail : Employee Name Updation got failed and is verified.");
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_24", 3, 10);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		Reporter.log("****TC24 Executed Completely****",true);
	}
}