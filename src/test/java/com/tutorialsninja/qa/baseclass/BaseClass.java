package com.tutorialsninja.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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

			if (prop.getProperty("HeadlessRun").equalsIgnoreCase("true")) {

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				driver = new ChromeDriver(options);

			} else
				driver = new ChromeDriver();

		}

		if (browserName.equalsIgnoreCase("firefox")) {

			if (prop.getProperty("HeadlessRun").equalsIgnoreCase("true")) {

				FirefoxOptions options = new FirefoxOptions();

				driver = new FirefoxDriver(options);

			} else
				driver = new FirefoxDriver();

		}

		if (browserName.equalsIgnoreCase("Edge")) {
			if (prop.getProperty("HeadlessRun").equalsIgnoreCase("true")) {

				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");

				driver = new EdgeDriver(options);

			} else
				driver = new EdgeDriver();
		}

		driver.manage().window().setSize(new Dimension(1440, 900));
		driver.manage().deleteAllCookies();
		// driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utilities.PAGE_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utilities.IMPLICIT_WAIT_TIME));

		driver.get(prop.getProperty("url"));

		return driver;

	}

}
