package com.foflo.po;

import com.foflo.dao.CubPersonDao;
import com.foflo.test.AbstractSeleniumTest;
import com.foflo.test.WebElements;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Cub Profile Merge Page Object
 */

public class ProfileMergePageObject extends AbstractSeleniumTest {	
	
	private final Logger logger = LoggerFactory
			.getLogger(ProfileMergePageObject.class);


	@FindBy(css = "div[class^='bigHeader']")
	private WebElement pageTitle;

	@FindBy(xpath = "//div[contains(text(),'Profiles this session')]")
	private WebElement profilesSession;

	@FindBy(css = "div[class$='profileFields']>div:nth-child(2)")
	private WebElement firstNameLeft;

	@FindBy(css = "div[class$='profileFields']>div:nth-child(5)")
	private WebElement lastNameLeft;

	@FindBy(css = "div[class^='profileFields']>div:nth-child(2)")
	private WebElement firstNameRight;

	@FindBy(css = "div[class^='profileFields']>div:nth-child(5)")
	private WebElement lastNameRight;

	@FindBy(css = "div[class$='profileFields']+div>div>div")
	private WebElement descTextBox;

	@FindBy(css = "button[id='confirm']")
	private WebElement confirmMergeButton;

	@FindBy(css = "div[id='leftContainer'] div[class^='terminalDisplay']")
	private WebElement personID;

	@FindBy(css = "div[class='boldTitle ng-binding']")
	private WebElement numberOfMatches;
	
	@FindBy(xpath = "//*[@id='leftContainer']/div/div[2]/div[1]/div/div/img")
	private WebElement personPhoto;
	
	private String currentPersonId;
	
	private String numOfMatches;

	public ProfileMergePageObject(WebDriver driver) {

		this.driver = driver;
	}

	public boolean isProfileMergePage() {
		// wait until profile merge page title shows up
		waitUntil((WebDriver input) -> !pageTitle.getText().isEmpty());
		return pageTitle.getText().equals("Profile Merge");
	}

	public void verifyNameAtBothSides() {
		waitForPersonId();
		currentPersonId = personID.getText();

		logger.info("Current person ID: " + currentPersonId);
		logger.info("Left Name: " + firstNameLeft.getText() + " "
				+ lastNameLeft.getText());
		logger.info("Right Name: " + firstNameRight.getText() + " "
				+ lastNameRight.getText());
	}

	public void performMerge() {
		waitForPersonId();
		// get total number of potential matches from substring of element text
		numOfMatches = numberOfMatches.getText();
		String subString = numOfMatches
				.substring(numOfMatches.lastIndexOf(' ') + 1);
		int num = Integer.parseInt(subString);
		logger.info("numberOfMatches: " + numOfMatches);
		logger.info("total number of potential matches: " + num);

		// keep pressing "Confirm Merge" till reaching the last 
		int i = 0;
		while (i < num) {
			lastNameRight.click();
			WebElements.waitAndClick(confirmMergeButton);
			i++;
		}
	}

	public void waitForNextProfile() {
		String firstName = firstNameRight.getText();
		logger.info("Current first name: " + firstName);
		// wait until page is loaded with the next person's profile
		WebDriverWait wait = new WebDriverWait(driver, 30000, 1000);
		wait.until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver _driver) {
				String nextFirstname = _driver
						.findElement(
								By.cssSelector("div[class^='profileFields']>div:nth-child(2)"))
						.getText();
				logger.info("Next First Name : " + nextFirstname);
				return !firstName.equals(nextFirstname);
			}
		});
	}

	public void waitForPersonId() {
		// wait until right side person ID pops up
		WebDriverWait wait = new WebDriverWait(driver, 10000, 500);
		wait.until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver _driver) {
				int length = personID.getText().trim().length();
				return length > 2;
			}
		});
	}
	
	public Long getPersonID() {
		String personId = personID.getText();
		String subString = personId.substring(personId.lastIndexOf(' ')+1);      logger.info("subString : "+subString);
		return Long.parseLong(subString);
	}

	public boolean doesPersonPhotoExist() {
		String src = personPhoto.getAttribute("src");
		logger.info("img src: "+src);
		if(src.trim().length()>0){
			return true;
		}
		return false;
	}
	
	public void verityPhotoDisplay(CubPersonDao cubPersonDao){
		waitForPersonId();
		if(doesPersonPhotoExist()
				&& cubPersonDao.getPersonImageCount(getPersonID())>0){
			logger.info("@Profile photo've been found in UI and DB.");
		}else{
			logger.info("No profile photo.");
		}
	}

	public void verifyPersonNameDisplay(CubPersonDao cubPersonDao){
		
		waitForPersonId();
		String id = personID.getText();
		String subString = id.substring(id.lastIndexOf(' ')+1);     
		long personId =Long.parseLong(subString);       logger.info("personId : "+personId);
		
		String firstName = firstNameLeft.getText().toLowerCase();		logger.info("firstName: : "+firstName);
		String lastName = lastNameLeft.getText().toLowerCase();		logger.info("lastName: : "+lastName);
		
		int count = cubPersonDao.getPersonNameCountForMergeProfile(personId, firstName, lastName);
	}


}
