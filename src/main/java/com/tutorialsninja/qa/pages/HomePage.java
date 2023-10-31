package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// Objects
	
	@FindBy(xpath  = "//button[contains(@class,'btn-default ')]")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBar;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginButton;

	@FindBy(linkText = "Register")
	private WebElement registerButton;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Actions

	public void click_myAccountDropMenu() {

		myAccountDropMenu.click();

	}

	public void click_loginButton() {

		loginButton.click();

	}

	public void click_registerButton() {

		registerButton.click();

	}
	
	public void enter_Searchbar(String item) {

		searchBar.sendKeys(item);
	}

	public void click_searchBtn() {

		searchBtn.click();
	}


}
