package com.masteringselenium.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class DriverBase {

	private static List<DriverFactory> webDriverThreadPool = Collections
			.synchronizedList(new ArrayList<DriverFactory>());
	private static ThreadLocal<DriverFactory> driverFactoryThread;

	@BeforeSuite(alwaysRun = true)
	public static void instantiateWebDriverObject() {
		driverFactoryThread = ThreadLocal.withInitial(() -> {
			DriverFactory driverFactory = new DriverFactory();
			webDriverThreadPool.add(driverFactory);
			return driverFactory;
		});
	}

	public static RemoteWebDriver getDriver() throws Exception {
		return driverFactoryThread.get().getDriver();
	}

	@AfterMethod(alwaysRun = true)
	public static void clearCookies() {
		try {
			driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
		} catch (Exception ignored) {
			System.err.println("Unable to clear cookies, driver object is not viable...");
		}
	}

	@AfterSuite(alwaysRun = true)
	public static void closeDriverObjects() {
		for (DriverFactory driverFactory : webDriverThreadPool) {
			driverFactory.quitDriver();
		}
	}
}
