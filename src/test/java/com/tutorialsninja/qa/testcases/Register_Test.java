package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.utilities;

public class Register_Test extends BaseClass {

	public WebDriver driver;
	HomePage homepage;
	RegisterPage registerpage;

	public Register_Test() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenURl(prop.getProperty("browser"));

		homepage = new HomePage(driver);
		registerpage = new RegisterPage(driver);
		homepage.click_myAccountDropMenu();
		homepage.click_registerButton();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void TC_RF_001_verifyRegisterAccountWithMandatoryFields() {

		registerpage.enterMandatoryFields("Fname", "Lname", utilities.timeStamp(), "9999999999", "password",
				"password");
		registerpage.click_continue();
		Assert.assertEquals(registerpage.getTxt_AccountCreation(), "Your Account Has Been Created!");

	}

	@Test(priority = 2)
	public void TC_RF_001_verifyRegisterAccountWithAllFields() {

		registerpage.enterMandatoryFields("Fname", "Lname", utilities.timeStamp(), "9999999999", "password",
				"password");

		registerpage.click_subscribe();

		registerpage.click_continue();

		Assert.assertEquals(registerpage.getTxt_AccountCreation(), "Your Account Has Been Created!");

	}

	@Test(priority = 3)
	public void TC_RF_009_verifyRegisterAccountWithExsitingEmailAddress() {

		registerpage.enterMandatoryFields("Fname", "Lname", "raghav@b.com", "9999999999", "password", "password");

		registerpage.click_continue();

		Assert.assertEquals(registerpage.getTxt_AccountalreadyCreated(),
				"Warning: E-Mail Address is already registered!");

	}

	@Test(priority = 3)
	public void TC_RF_004_verifyRegisterAccountWithoutGivingAnyFields() {

		registerpage.click_continue();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div"))
				.getText().equalsIgnoreCase("First Name must be between 1 and 32 characters!"));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText()
				.equalsIgnoreCase("Last Name must be between 1 and 32 characters!"));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText()
				.equalsIgnoreCase("E-Mail Address does not appear to be valid!"));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
				.getText().equalsIgnoreCase("Telephone must be between 3 and 32 characters!"));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText()
				.equalsIgnoreCase("Password must be between 4 and 20 characters!"));

		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(),
				"Warning: You must agree to the Privacy Policy!");

	}

}
