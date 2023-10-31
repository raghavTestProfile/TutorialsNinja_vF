package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailAddress;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(className = "alert-dismissible")
	private WebElement credentailsMismatchError;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enter_emailAddress(String email) {

		emailAddress.sendKeys(email);

	}

	public void enter_password(String pwd) {

		password.sendKeys(pwd);

	}

	public void click_loginButton() {

		loginButton.click();

	}

	public String getText_credentailsMismatchError() {

		return credentailsMismatchError.getText();

	}

}
