package com.foflo.po;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.foflo.test.WebElements;

/**
 * Cub Homepage Object
 */


@SuppressWarnings("UnusedDeclaration")
public class CubHomePageObject {

	private WebDriver driver = null;

	@FindBy(xpath = "//span[contains(text(),'Profile Merge')]")
	@CacheLookup
	private WebElement profileMergeButton;

	@FindBy(xpath = "//span[contains(text(),'Profile Preview')]")
	@CacheLookup
	private WebElement profilePreviewButton;

	@FindBy(css = "#user")
	@CacheLookup
	private WebElement settingsDropdown;

	@FindBy(xpath = "//ul[@id='userDropdown']/li[5]")
	@CacheLookup
	private WebElement logoutButton;

	@FindBy(xpath = "//span[contains(text(),'Organization Preview')]")
	private WebElement orgPreviewBox;

	@FindBy(xpath = "//span[contains(text(),'Organization Match')]")
	private WebElement orgMatchBox;

	@FindBy(xpath = "//span[contains(text(),'Search & Assign')]")
	private WebElement searchAndAssignBox;


	public CubHomePageObject(WebDriver driver) {

		this.driver = driver;
	}

	public boolean isCubHomePage() {
		String pageSource = driver.getPageSource();
		return pageSource.contains("Hello");
	}

	public CubLoginPageObject gotoLoginPageByLogout() {
		settingsDropdown.click();
		logoutButton.click();
		return PageFactory.initElements(driver, CubLoginPageObject.class);
	}

	public boolean isProfileMergeBoxPresent() {
		String profileMerge = profileMergeButton.getText();
		return profileMerge.contentEquals("Profile Merge");
	}

	public ProfileMergeBinPageObject gotoProfileMergeBinPage() {
		profileMergeButton.click();
		return PageFactory.initElements(driver, ProfileMergeBinPageObject.class);
	}

	public boolean isProfilePreviewBoxPresent() {
		String profilePreview = profilePreviewButton.getText();
		return profilePreview.contentEquals("Profile Preview");
	}

	public ProfilePreviewBinPageObject gotoProfilePreviewBinPage() {
		profilePreviewButton.click();
		return PageFactory.initElements(driver, ProfilePreviewBinPageObject.class);
	}

	public boolean isOrgPreviewBox() {
		String orgPreview = this.orgPreviewBox.getText();
		return orgPreview.contentEquals("Organization Preview");
	}

	public boolean isOrgMatchBox() {
		String orgMatch = this.orgMatchBox.getText();
		return orgMatch.contentEquals("Organization Match");
	}

	public boolean isSearchAndAssignBox() {
		String orgMatch = this.searchAndAssignBox.getText();
		return orgMatch.contentEquals("Search & Assign");
	}

	public void clickOrgPreviewBox() {
		WebElements.waitAndClick(orgPreviewBox);
	}

	public void clickOrgMatchBox() {
		WebElements.waitAndClick(orgMatchBox);
	}

	public void clickSearchAndAssignBox() {
		WebElements.waitAndClick(searchAndAssignBox);
	}

	public void clickProfilePreviewBox() {
		WebElements.waitAndClick(profilePreviewButton);
	}
}

