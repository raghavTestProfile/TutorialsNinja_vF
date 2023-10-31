package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.utilities;

public class Login_Test extends BaseClass {

	public WebDriver driver;
	HomePage homepage;
	LoginPage loginpage;
	AccountPage accountpage;

	public Login_Test() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenURl(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		loginpage = new LoginPage(driver);
		accountpage = new AccountPage(driver);
		homepage.click_myAccountDropMenu();
		homepage.click_loginButton();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void TC_LF_001_verifyLoginWithValidCredentials() {

		loginpage.enter_emailAddress(prop.getProperty("validemail"));
		loginpage.enter_password(prop.getProperty("validpassword"));
		loginpage.click_loginButton();

		Assert.assertEquals(accountpage.getText_EditAccInfoTxt(), "Edit your account information");

	}

	@Test(priority = 2)
	public void TC_LF_002_verifyLoginWithInValidCredentials() {

		loginpage.enter_emailAddress(utilities.timeStamp());
		loginpage.enter_password("123321");
		loginpage.click_loginButton();

		Assert.assertTrue(loginpage.getText_credentailsMismatchError()
				.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password."));

	}

	@Test(priority = 3)
	public void TC_LF_004_verifyLoginWithValidEmailAndInvalidPassword() {

		loginpage.enter_emailAddress(prop.getProperty("validemail"));
		loginpage.enter_password("1234");
		loginpage.click_loginButton();

		Assert.assertTrue(loginpage.getText_credentailsMismatchError()
				.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password."));
	}

	@Test(priority = 4)
	public void TC_LF_003_verifyLoginWithInalidEmailAndValidPassword() {

		loginpage.enter_emailAddress(utilities.timeStamp());
		loginpage.enter_password(prop.getProperty("validpassword"));
		loginpage.click_loginButton();

		Assert.assertTrue(loginpage.getText_credentailsMismatchError()
				.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password."));

	}

	@Test(priority = 4)
	public void TC_LF_005_verifyLoginWithputProvidingCredentials() {

		loginpage.click_loginButton();

		Assert.assertTrue(loginpage.getText_credentailsMismatchError()
				.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password."));

	}

	@DataProvider
	public Object[][] supplyTestData() {

		Object[][] data = { { "abs@cmdail.com", "1234" }, { "abs@camdail.com", "12324" },
				{ "absdas@cadmail.com", "12324" } };
		return data;

	}

	@Test(priority = 2, dataProvider = "supplyTestData")
	public void TC_LF_002_verifyLoginWithInValidCredentialsDataProvider(String email, String password) {

		loginpage.enter_emailAddress(email);
		loginpage.enter_password(password);
		loginpage.click_loginButton();

		Assert.assertTrue(loginpage.getText_credentailsMismatchError()
				.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password."));

	}

	@DataProvider(name = "ExcelData")
	public Object[][] supplyTestDataExcel() {

		Object[][] data = utilities.getTestDataFromExcel("Sheet1");
		return data;

	}

	@Test(priority = 2, dataProvider = "ExcelData")
	public void TC_LF_002_verifyLoginWithInValidCredentialsDataProviderExcel(String email, String password) {

		loginpage.enter_emailAddress(email);
		loginpage.enter_password(password);
		loginpage.click_loginButton();

		Assert.assertTrue(loginpage.getText_credentailsMismatchError()
				.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password."));

	}

}
