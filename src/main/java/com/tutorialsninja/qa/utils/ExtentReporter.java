package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	public static ExtentReports generateExtentReporter() {

		ExtentReports extentreport = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentReportFile);

		sparkreporter.config().setReportName("Tutorials_Ninja_Automation_Execution_Results");

		sparkreporter.config().setDocumentTitle("Automation Extent Reports");
		sparkreporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");

		extentreport.attachReporter(sparkreporter);

		Properties confProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
		try {
			FileInputStream fis = new FileInputStream(configPropFile);
			confProp.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentreport.setSystemInfo("Application URL", confProp.getProperty("url"));
		extentreport.setSystemInfo("Browser Name", confProp.getProperty("browser"));
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentreport;

	}

}
