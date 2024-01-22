package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search_Test extends BaseClass {

	public WebDriver driver;
	SearchPage searchpage;
	HomePage homepage;
	LoginPage loginpage;

	public Search_Test() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenURl(prop.getProperty("browser"));
		searchpage = new SearchPage(driver);
		homepage = new HomePage(driver);
		loginpage = new LoginPage(driver);

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void TC_SF_001_verifySearchWithValidProduct() {

		homepage.enter_Searchbar("macbook");
		homepage.click_searchBtn();

//		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("macbook");
//		driver.findElement(By.xpath("//button[contains(@class,'btn-default ')]")).click();

		Assert.assertTrue(driver.findElement(By.linkText("MacBook")).isDisplayed());

	}

	@Test(priority = 1)
	public void TC_SF_002_verifySearchWithInValidProduct() {

		homepage.enter_Searchbar("kite");
		homepage.click_searchBtn();

		Assert.assertTrue(
				searchpage.getTxt_ProductNotFound1().equalsIgnoreCase("Products meeting the search criteria"));

		Assert.assertTrue(searchpage.getTxt_ProductNotFound2()
				.equalsIgnoreCase("There is no product that matches the search criteria."));
	}

	@Test(priority = 1)
	public void TC_SF_003_verifySearchWithoutEnteringProduct() {

		homepage.click_searchBtn();

		Assert.assertTrue(
				searchpage.getTxt_ProductNotFound1().equalsIgnoreCase("Products meeting the search criteria"));

		Assert.assertTrue(searchpage.getTxt_ProductNotFound2()
				.equalsIgnoreCase("There is no product that matches the search criteria."));
	}

	@Test(priority = 1)
	public void TC_SF_004_verifySearchWithValidProduct_AfterLogin() {

		homepage.click_myAccountDropMenu();
		homepage.click_loginButton();

		loginpage.enter_emailAddress(prop.getProperty("validemail"));
		loginpage.enter_password(prop.getProperty("validpassword"));
		loginpage.click_loginButton();

		homepage.enter_Searchbar("macbook");
		homepage.click_searchBtn();

		Assert.assertTrue(driver.findElement(By.linkText("MacBook")).isDisplayed());

	}

}
