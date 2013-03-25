package com.pb.SeleniumTests;

public enum BrowserType {

	FIREFOX("firefox"), IE("ie");

	private final String browser;

	private BrowserType(String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return browser;
	}

}
