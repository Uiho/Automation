package com.foflo.po;

import com.foflo.test.AbstractSeleniumTest;
import com.foflo.test.WebElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Cub Profile Preview Bin Page Object
 */

@SuppressWarnings("UnusedDeclaration")
public class ProfilePreviewBinPageObject extends AbstractSeleniumTest {

	@FindBy(xpath = "//button[contains(text(),'Start')]")
	private WebElement startButton;

	@FindBy(css = "div[class^='title']")
	private WebElement profilePreviewBinPageTitle;

	public ProfilePreviewBinPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public ProfilePreviewPageObject gotoProfilePreview() {
		WebElements.waitAndClick(startButton);
		return PageFactory.initElements(driver, ProfilePreviewPageObject.class);
	}

	public boolean isProfilePreviewBinPage() {
		waitUntil((WebDriver input) -> !profilePreviewBinPageTitle.getText().isEmpty());

		String profileMergeBinTitle = profilePreviewBinPageTitle.getText();
		return profileMergeBinTitle.equals("Profile Preview Bin");
	}

	
	public void clickStartButton() {
		WebElements.waitAndClick(startButton);
	}

}