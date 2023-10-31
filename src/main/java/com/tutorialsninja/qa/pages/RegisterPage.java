package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstname;

	@FindBy(id = "input-lastname")
	private WebElement lastname;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(id = "input-confirm")
	private WebElement confirmpassword;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continuebtn;

	@FindBy(xpath = "// input[@type='checkbox']")
	private WebElement privacycheckbox;

	@FindBy(xpath = "//input[@name='newsletter']")
	private WebElement subscribe;

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement accCreatedtxt;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement AccAlreadyCreatedErr;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterMandatoryFields(String fname, String lname, String mail, String phone, String pwd, String cpwd) {

		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		email.sendKeys(mail);
		telephone.sendKeys(phone);
		password.sendKeys(pwd);
		confirmpassword.sendKeys(cpwd);
		privacycheckbox.click();

	}

	public void click_subscribe() {

		subscribe.click();

	}

	public void click_continue() {

		continuebtn.click();
	}

	public String getTxt_AccountCreation() {

		return accCreatedtxt.getText();
	}

	public String getTxt_AccountalreadyCreated() {

		return AccAlreadyCreatedErr.getText();
	}
}
