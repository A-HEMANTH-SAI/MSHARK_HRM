package com.hrm.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='info']")
	private WebElement currentUserEmailId;

	@FindBy(xpath = "//p[text()='Dashboard']")
	WebElement dashBoardTab;

	public WebElement getDashBoardTab() {
		return dashBoardTab;
	}

	@FindBy(xpath = "//p[normalize-space(text())='CORPORATE']")
	WebElement corporateTab;

	public WebElement getCorporateTab() {
		return corporateTab;
	}

	@FindBy(xpath = "//p[normalize-space(text())='Add Corporate']")
	WebElement addCorporateOption;

	public WebElement getAddCorporateOption() {
		return addCorporateOption;
	}

	@FindBy(xpath = "//p[normalize-space(text())='BRANCHES']")
	WebElement branchTab;

	public WebElement getBranchTab() {
		return branchTab;
	}

	@FindBy(xpath = "//p[normalize-space(text())='Add Braches']")
	WebElement addBranchesOption;

	public WebElement getAddBranchesOption() {
		return addBranchesOption;
	}

	@FindBy(xpath = "//p[normalize-space(text())='EMPLOYEE']")
	WebElement employeeTab;

	public WebElement getEmployeeTab() {
		return employeeTab;
	}

	@FindBy(xpath = "//p[normalize-space(text())='Add Employee']")
	WebElement addEmployeeOption;

	public WebElement getAddEmployeeOption() {
		return addEmployeeOption;
	}

	@FindBy(xpath = "//p[normalize-space(text())='ADMIN']")
	WebElement adminTab;

	public WebElement getAdminTab() {
		return adminTab;
	}

	@FindBy(xpath = "//p[normalize-space(text())='Add Admin']")
	WebElement addAdminOption;

	public WebElement getAddAdminOption() {
		return addAdminOption;
	}

	@FindBy(xpath = "//span[.='Employee']/following-sibling::span")
	WebElement dashBoardEmployeeCount;


	@FindBy(xpath = "//i[@class='fa fa-user']")
	WebElement userProfileIcon;

	public WebElement getUserProfileIcon() {
		return userProfileIcon;
	}

	@FindBy(xpath = "//i[@class='fa fa-power-off']")
	WebElement logOutLink;

	public WebElement getLogOutLink() {
		return logOutLink;
	}

	/**
	 * This Business Logic is to navigate to the Corporate page
	 */
	public void navigateToAddCorporate() {
		corporateTab.click();
		addCorporateOption.click();
	}

	/**
	 * This Business Logic is to navigate to the Branches page
	 */
	public void navigateToAddBranches() {
		branchTab.click();
		addBranchesOption.click();
	}

	/**
	 * This Business Logic is to navigate to the Employee page
	 */
	public void navigateToAddEmployee() {
		employeeTab.click();
		addEmployeeOption.click();
	}

	/**
	 * This Business Logic is to navigate to the Admin page
	 */
	public void navigateToAddAdmin() {
		adminTab.click();
		addAdminOption.click();
	}

	/**
	 * This Business Logic is to navigate to the Dashboard page
	 */
	public void naviagateToDashboardKpiTracker() {
		dashBoardTab.click();
	}

	/**
	 * This Business Logic is to Logout from the application
	 */
	public void logOutFormApplication() {
		userProfileIcon.click();
		logOutLink.click();
	}
	public String getDashBoardEmployeeCount() {
		return dashBoardEmployeeCount.getText();
	}
	
	public String getCurrentUserEMailId() {
		return currentUserEmailId.getText();
		
	}
}