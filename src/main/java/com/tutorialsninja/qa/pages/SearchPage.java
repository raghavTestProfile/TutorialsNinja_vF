package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(xpath = "//input[@value='Search']/following-sibling::h2")
	private WebElement productNotFound1;

	@FindBy(xpath = "//input[@value='Search']/following-sibling::p")
	private WebElement productNotFound2;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getTxt_ProductNotFound1() {

		return productNotFound1.getText();
	}

	public String getTxt_ProductNotFound2() {

		return productNotFound2.getText();
	}

}
