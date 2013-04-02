package com.pb.SeleniumTests;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenShotListner extends TestListenerAdapter {

	private static final String DATE_FORMAT = "MMddyyHHmm";
	private static final String SCREEN_SHOT_EXT = ".png";

	@Override
	public void onTestFailure(ITestResult tr) {
		captureScreenShot(tr.getName());
		super.onTestFailure(tr);
	}

    @Override
    public void onFinish(org.testng.ITestContext testContext) {
        //TODO: change xml to xls to put results into database
    }

	private void captureScreenShot(String methodName) {
		File screenshot = new File("target"
				+ File.separator
				+ "surefire-reports"
				+ File.separator
				+ "screenshots"
				+ File.separator
				+ DateFormatUtils.format(System.currentTimeMillis(),
						DATE_FORMAT) + "_" + methodName + SCREEN_SHOT_EXT);

		if (!screenshot.exists()) {
			new File(screenshot.getParent()).mkdirs();
			try {
				screenshot.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		WebDriver driver = SeleniumBase.getDriver();
		if (driver != null) {
			try {
				new FileOutputStream(screenshot)
						.write(((TakesScreenshot) driver)
								.getScreenshotAs(OutputType.BYTES));
				Reporter.log("<a href=" + screenshot.getAbsolutePath()
						+ " target='_blank' >" + screenshot.getName() + "</a>");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (WebDriverException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
