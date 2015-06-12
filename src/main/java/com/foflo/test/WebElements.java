package com.foflo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Static methods around webelements
 */
public class WebElements {

	public static void waitAndClick(WebElement element) {
		int waitTime = 30;
		long whenCalled = System.currentTimeMillis() / 1000;
		long timeNow = 0;
		boolean isClickable = false;

		while (timeNow < waitTime && !isClickable) {
			long callTime = System.currentTimeMillis() / 1000;
			timeNow = callTime - whenCalled;
			try {
				Thread.sleep(500);
				element.click();
				isClickable = true;
			} catch (Exception e) {
				//System.out.println("not found.. keep looking.." + timeNow);
			}
		}
	}

	public static void waitUntilElementFound(WebElement element) {
		int waitTime = 30;
		long whenCalled = System.currentTimeMillis() / 1000;
		long timeNow = 0;
		boolean isFound = false;

		while (timeNow < waitTime && !isFound) {
			long callTime = System.currentTimeMillis() / 1000;
			timeNow = callTime - whenCalled;
			try {
				Thread.sleep(500);
				if (element.isDisplayed()) {
					if (element.isEnabled()) {
						isFound = true;
					}
				}
			} catch (Exception e) {
				//System.out.println("not found.. keep looking.." + timeNow);
			}
		}
	}

	public static void waitUntilElementNotFound(WebElement element) {
		int waitTime = 30;
		long whenCalled = System.currentTimeMillis() / 1000;
		long timeNow = 0;
		boolean isNotFound = false;

		while (timeNow < waitTime && !isNotFound) {
			long callTime = System.currentTimeMillis() / 1000;
			timeNow = callTime - whenCalled;
			try {
				Thread.sleep(500);
				if ((!element.isDisplayed())) {
					if ((!element.isEnabled())) {
						isNotFound = true;
					}
				}
			} catch (Exception e) {
				//System.out.println("not found.. keep looking.." + timeNow);
			}
		}
	}


	public static void waitUntilElementFoundText(WebElement element) {
		int waitTime = 30;
		long whenCalled = System.currentTimeMillis() / 1000;
		long timeNow = 0;
		boolean isFound = false;

		while (timeNow < waitTime && !isFound) {
			long callTime = System.currentTimeMillis() / 1000;
			timeNow = callTime - whenCalled;
			String text = element.getText();
			try {
				Thread.sleep(500);
				if (text != null && !text.isEmpty() && !text.trim().isEmpty()) {
					isFound = true;
				}
			} catch (Exception e) {
				//System.out.println("text not found.. keep looking.." + timeNow);
			}
		}
	}

	public static void waitAndClickRadioButton(WebElement element, int second) {
		int waitTime = second;
		long whenCalled = System.currentTimeMillis() / 1000;
		long timeNow = 0;
		boolean isClickable = false;

		while (timeNow < waitTime && !isClickable) {
			long callTime = System.currentTimeMillis() / 1000;
			timeNow = callTime - whenCalled;
			try {
				Thread.sleep(500);
				element.click();
				if (element.isSelected() == true) {
					isClickable = true;
				}
			} catch (Exception e) {
				//System.out.println("not clickable yet.. keep looking.." + timeNow);
			}
		}
	}

	public static void waitUntilClickAndComplete(WebElement element, WebDriver driver) {
		boolean isClickable = false;
		boolean isComplete = false;
		
		while (!isClickable) {
			try {
				Thread.sleep(500);
				element.click();
				isClickable = true;
			} catch (Exception e) {
//				log.info("not clickable yet.. keep looking..");
			}
		}

		while (!isComplete) {
			try{
				Thread.sleep(500);
				String bodyStyle = driver.findElement(By.tagName("body")).getAttribute("style");
				if ("overflow-x: auto;".equals(bodyStyle)) {
					isComplete = true;
				}
			} catch (Exception e) {
//				log.info("not clickable yet.. keep looking..");
			}
		}
	}
}