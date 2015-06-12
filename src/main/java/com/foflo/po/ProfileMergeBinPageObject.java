package com.foflo.po;

import com.foflo.test.AbstractSeleniumTest;
import com.foflo.test.WebElements;
import com.foflo.test.tests.CubInAutomationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Cub Profile Merge Bin Page Object
 */

@SuppressWarnings("UnusedDeclaration")
public class ProfileMergeBinPageObject extends AbstractSeleniumTest {
	
	private Logger logger = LoggerFactory.getLogger(ProfileMergeBinPageObject.class);

	@FindBy(xpath = "//button[contains(text(),'Start')]")
	private WebElement startButton;

	@FindBy(css = "div[class^='title']")
	private WebElement profileMergeBinPageTitle;

	public ProfileMergeBinPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public ProfileMergePageObject gotoProfileMerge() {
		WebElements.waitAndClick(startButton);
		return PageFactory.initElements(driver, ProfileMergePageObject.class);
	}
	

	public boolean isProfileMergeBinPage() {
		waitUntil((WebDriver input) -> !profileMergeBinPageTitle.getText().isEmpty());
		String profileMergeBinTitle = profileMergeBinPageTitle.getText();
		return profileMergeBinTitle.equals("Profile Merge Bin");
	}

}