package com.foflo.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public abstract class AbstractSeleniumTest {
	
	protected void waitUntil(ExpectedCondition<Object> condition) {
//		new WebDriverWait(driver, waitSeconds).until(condition);
	}
	
	protected void waitUntilReady(WebElement webElement) {
		waitUntil((WebDriver input) -> webElement.isDisplayed() && webElement.isEnabled());
	}
	
	protected WebDriver driver;
}
