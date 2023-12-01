package com.hrofficer;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.hrm.genericutils.BaseClass;
import com.hrm.objectRepo.DeleteEmployeePage;
import com.hrm.objectRepo.EmployeePage;
import com.hrm.objectRepo.HomePage;
import com.hrm.objectRepo.LoginPage;

// Integration [ Delete Employee by HR Officier and check the HR Officier dashboard Employee count ] --> TestScript dependent on TC_01 and TC_54
public class DeleteEmployee_FromHRO_Check_EmployeeCount_TC_09_test extends BaseClass{
	@Test (groups = "integration")
	public void tc_09_test() throws EncryptedDocumentException, IOException, InterruptedException {

		// Retrieve common data from Properties file
		String url = puObj.readDataFromPropertiesFile("url");
		// Trigger the URL
		driver.get(url);
		// Login to the Application as HR Officer (using existing HR Officer credentials)
		String hrOfficerUserEmail = euObj.readExcelData("TC_09", 3, 1);
		String hrOfficerPasswors = euObj.readExcelData("TC_09", 4, 1);
		LoginPage lp = new LoginPage(driver);
		lp.hrOfficerLogin(hrOfficerUserEmail, hrOfficerPasswors);
		// Verify the Alert message using Assert and Accept the Alert
		String expectedPopupMessage;
		expectedPopupMessage = euObj.readExcelData("TC_09", 2, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		HomePage hp = new HomePage(driver);
		// verify the user
		//hp.verifyUser(hrOfficerUserEmail);
		// From Home page get the Initial count of Employees registered in the Application
		int initialEmployeeCount = Integer.parseInt(hp.getDashBoardEmployeeCount());
		System.out.println("Total count of employees before deletion is : " + initialEmployeeCount);
		// Go to Employee page
		hp.navigateToAddEmployee();
		// Search for existing Employee and delete the searched Employee
		EmployeePage ep = new EmployeePage(driver);
		String employeeId = euObj.readExcelData("TC_09", 2, 4);
		String employeeName = euObj.readExcelData("TC_09", 3, 4);
		ep.getSearchTextfield().sendKeys(employeeId);
		
		
		
		
		JavascriptExecutor je=(JavascriptExecutor)driver;
		je.executeScript("window.scrollTo(document.body.scrollWidth,0)");
		
		wuObj.waitUntilElementGetsClickable(driver,driver.findElement(By.xpath("//td[.='"+employeeId+"']/following-sibling::td[.='"+employeeName+"']/following-sibling::td[6]/child::i[@title='Delete Employee']") ), 100);
		
		
		
		
		ep.clickOnDeleteEmployeeIcon(driver, employeeName, employeeId);
		DeleteEmployeePage dep = new DeleteEmployeePage(driver);
		dep.getDeleteButton().click();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_09", 15, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		// Go to Dasboard page and get the total count of Employees registered in the Application
		hp.naviagateToDashboardKpiTracker();
		// From Home page get the Initial count of Employees registered in the Application
		int afterEmployeeCount = Integer.parseInt(hp.getDashBoardEmployeeCount());
		// Verify that upon deletion of Employee, Current count of Employee is decreasing or not
		Assert.assertEquals(afterEmployeeCount,initialEmployeeCount-1);
		// Logout from the Application
		hp.logOutFormApplication();
		// Verify the Alert message using Assert and Accept the Alert
		expectedPopupMessage = euObj.readExcelData("TC_09", 3, 7);
		wuObj.acceptAlertWithAssert(driver, 10, expectedPopupMessage);
		Reporter.log("****TC09 Executed Completely****",true);
	}
}