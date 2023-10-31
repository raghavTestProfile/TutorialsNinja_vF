package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 30;

	public static String timeStamp() {

//		Time time = new Time(0);
//		String d1 = time.toString().replace(" ", "_").replace(":", "_");
//		return "r"+d1+"@gmail.com";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = now.toString().replace(" ", "_").replace(":", "_").replace(".", "_");
		return "r" + time + "@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

		File fis = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjatestData1.xlsx");

		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(fis);

			workbook = new XSSFWorkbook(fisExcel);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet("Sheet1");

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();

				switch (celltype) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:

					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;

				case BOOLEAN:

					data[i][j] = cell.getBooleanCellValue();
					break;

				}

			}

		}

		return data;

	}

	public static String captureScreenshot(WebDriver driver, String testName) {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			FileHandler.copy(screenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return destinationScreenshotPath;
	}

}
