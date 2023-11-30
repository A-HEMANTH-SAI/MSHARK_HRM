package com.admin;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.hrm.genericutils.BaseClass;
import com.hrm.genericutils.DataProviderClass;
import com.hrm.objectRepo.HomePage;
import com.hrm.objectRepo.LoginPage;

public class LoginAs_Head_Officer_Assistant extends BaseClass{

	@Test(dataProviderClass=DataProviderClass.class,dataProvider="storeData")
	public void loginAndCheckAccess(String un,String pw,String ddText) throws InterruptedException, IOException {
		driver.get(puObj.readDataFromPropertiesFile("url"));
		LoginPage lp=new LoginPage(driver);
		HomePage hp=new HomePage(driver);
		lp.getHrEmailTextfield().sendKeys(un);;
		lp.getHrPasswordTextfield().sendKeys(pw);;
		lp.selectDropdownByValue(lp.getHrTypeDropdown(), ddText);
		lp.getSignInButton().click();
		wuObj.acceptAlert(driver, 10);
		if(ddText.equalsIgnoreCase(puObj.readDataFromPropertiesFile("hrhddValue"))) {
		Assert.assertTrue(hp.getAdminTab().isDisplayed());
		Assert.assertTrue(hp.getBranchTab().isDisplayed());
		Assert.assertTrue(hp.getCorporateTab().isDisplayed());
		Assert.assertTrue(hp.getEmployeeTab().isDisplayed());
		Assert.assertTrue(hp.getDashBoardTab().isDisplayed());
		}else if(ddText.equalsIgnoreCase(puObj.readDataFromPropertiesFile("hroddValue"))){
			Assert.assertTrue(hp.getDashBoardTab().isDisplayed());
			Assert.assertTrue(hp.getEmployeeTab().isDisplayed());
		}else if(ddText.equalsIgnoreCase(puObj.readDataFromPropertiesFile("hraddValue"))) {
			Assert.assertTrue(hp.getDashBoardTab().isDisplayed());
			Assert.assertTrue(hp.getEmployeeTab().isDisplayed());
		}
		hp.logOutFormApplication();
		wuObj.acceptAlert(driver, 10);
		Reporter.log("****TC_HOA Executed Completely****",true);
	}

}
