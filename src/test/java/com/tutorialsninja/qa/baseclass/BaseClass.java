package com.tutorialsninja.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.utilities;

public class BaseClass {

	WebDriver driver;
	public Properties prop;

	public BaseClass() {

		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");

		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public WebDriver initializeBrowserAndOpenURl(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utilities.PAGE_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utilities.IMPLICIT_WAIT_TIME));
		
		
		driver.get(prop.getProperty("url"));

		return driver;

	}

}
