package com.masteringselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.masteringselenium.config.DriverType;
import static com.masteringselenium.config.DriverType.FIREFOX;
import static com.masteringselenium.config.DriverType.valueOf;

public class WebDriverThread {
	private WebDriver webdriver;
	private DriverType selectedDriverType;

	private final DriverType defaultDriverType = FIREFOX;
	private final String browser = System.getProperty("browser").toUpperCase();
	private final String opeartingSystem = System.getProperty("os.name").toUpperCase();
	private final String systemArchitecture = System.getProperty("os.arch");

	public WebDriver getDriver() {
		if (null == webdriver) {
			selectedDriverType = determineEffectiveDriverType();
			DesiredCapabilities desiredCapabilites = selectedDriverType.getDesiredCapabilities();
			instantiateWebDriver(desiredCapabilites);

		}
		return webdriver;
	}

	public void quitDriver() {
		if (null != webdriver) {
			webdriver.quit();
			webdriver = null;
		}
	}

	private DriverType determineEffectiveDriverType() {
		DriverType driverType = defaultDriverType;
		try {
			driverType = valueOf(browser);
		} catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		} catch (NullPointerException ignored) {
			System.err.println("No driver specified, defaulting to '" + driverType + "'...");
		}
		return driverType;
	}

	private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) {
		System.out.println(" ");
		System.out.println("Current Operating System: " + opeartingSystem);
		System.out.println("Current Architecture: " + systemArchitecture);
		System.out.println("Current Browser Selection: " + selectedDriverType);
		System.out.println(" ");
		webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
	}

}
