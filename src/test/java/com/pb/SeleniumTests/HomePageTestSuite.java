package com.pb.SeleniumTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTestSuite extends SeleniumBase {

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = getDriver();
	}

	@AfterMethod
	public void tearDown() {
		quitDriver();
	}

	@Test(description = "Test method")
	public void t1() {
		driver.get("http://www.google.pl");
		Assert.assertTrue(false);
	}

	public void t2() {
		Assert.assertTrue(true);
	}
}
