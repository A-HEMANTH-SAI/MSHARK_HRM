package com.hrm.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.genericutils.WebDriverUtils;

public class LoginPage extends WebDriverUtils {
	WebDriverUtils wdu=new WebDriverUtils();
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "hr_email")
	WebElement hrEmailTextfield;

	public WebElement getHrEmailTextfield() {
		return hrEmailTextfield;
	}

	@FindBy(name = "hr_password")
	WebElement hrPasswordTextfield;

	public WebElement getHrPasswordTextfield() {
		return hrPasswordTextfield;
	}

	@FindBy(name = "hr_type")
	WebElement hrTypeDropdown;

	public WebElement getHrTypeDropdown() {
		return hrTypeDropdown;
	}

	@FindBy(xpath = "//option[@value='HR Head']")
	WebElement hrHeadDropdownOption;

	@FindBy(xpath = "//option[@value='HR Officer']")
	WebElement hrOfficerDropdownOption;

	@FindBy(xpath = "//option[@value='HR Assistant']")
	WebElement hrAssistantDropdownOption;

	@FindBy(name = "login_hr")
	WebElement signInButton;

	public WebElement getSignInButton() {
		return signInButton;
	}
	
	public void hrOfficerLogin(String username, String password) {
		hrEmailTextfield.sendKeys(username);
		hrPasswordTextfield.sendKeys(password);
		wdu.selectDropdownByText(hrTypeDropdown,"→ HR Officer");
		signInButton.click();
	}
	public void hrAssistantLogin(String username, String password) {
		hrEmailTextfield.sendKeys(username);
		hrPasswordTextfield.sendKeys(password);
		wdu.selectDropdownByText(hrTypeDropdown,"→ HR Assistant");
		signInButton.click();
	}
	
	public void hrHeadLogin(String username, String password) {
		hrEmailTextfield.sendKeys(username);
		hrPasswordTextfield.sendKeys(password);
		wdu.selectDropdownByText(hrTypeDropdown,"→ HR Head");
		signInButton.click();
	}
	
}