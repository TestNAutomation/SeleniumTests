package com.pb.SeleniumTests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SetUpTests {

	@BeforeSuite
	public void setUp() {
		System.out.println("Before suit");
	}

	@AfterSuite
	public void tearDown() {
		System.out.println("After suit");
	}
}
