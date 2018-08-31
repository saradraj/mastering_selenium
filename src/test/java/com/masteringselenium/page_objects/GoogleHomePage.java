package com.masteringselenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.masteringselenium.config.DriverBase;

public class GoogleHomePage {
	private final RemoteWebDriver driver = DriverBase.getDriver();

	private WebElement searchBar = driver.findElement(By.name("q"));
	private WebElement googleSearch = driver.findElement(By.name("q"));
	private WebElement imFeelingLucky = driver.findElement(By.name("q"));

	public GoogleHomePage() throws Exception {
	}

	public GoogleHomePage enterSearchTerm(String searchTerm) {
		searchBar.clear();
		searchBar.sendKeys(searchTerm);

		return this;
	}

	public GoogleHomePage submitSearch() {
		googleSearch.submit();

		return this;
	}

	public void getLucky() {
		imFeelingLucky.click();
	}

}
