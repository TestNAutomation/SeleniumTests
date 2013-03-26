package com.pb.SeleniumTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class SeleniumBase {

	private static ResourceBundle _prop = ResourceBundle.getBundle("settings");
	private static String hubURL;
	private static BrowserType browserType;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			getBrowserType();
			return loadDriver();
		}
	};

	public static WebDriver getDriver() {
		return driver.get();
	}

	protected static void quitDriver() {
		driver.get().quit();
		driver.remove();
	}

	private static WebDriver loadDriver() {
		//TODO: Double check with and without selenium grid
		hubURL = _prop.getString("hubURL");
		try {
			URL ulr = new URL(hubURL);
			return new Augmenter().augment(new RemoteWebDriver(ulr,
					getDesiredCapabilities(browserType)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnreachableBrowserException e) {
			e.printStackTrace();
		}
		return getDriverForBrowserType();
	}

	private static WebDriver getDriverForBrowserType() {
		switch (browserType) {
		case FIREFOX:
			return new FirefoxDriver(getDesiredCapabilities(browserType));
		case IE:
			return new InternetExplorerDriver(
					getDesiredCapabilities(browserType));
		default:
			return new HtmlUnitDriver(getDesiredCapabilities(browserType));
		}
	}

	private static void getBrowserType() {
		for (BrowserType browser : BrowserType.values()) {
			if (browser.toString().toLowerCase()
					.equals(_prop.getString("browser").toLowerCase())) {
				browserType = browser;
			}
		}

		if (browserType == null) {
			browserType = BrowserType.FIREFOX;
		}
	}

	private static DesiredCapabilities getDesiredCapabilities(
			BrowserType browserType) {
		DesiredCapabilities capability;

		switch (browserType) {
		case FIREFOX:
			capability = DesiredCapabilities.firefox();
			break;
		case IE:
			capability = DesiredCapabilities.internetExplorer();
			break;
		default:
			capability = DesiredCapabilities.htmlUnit();
			break;
		}

		return capability;
	}

}
