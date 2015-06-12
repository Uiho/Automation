package com.foflo.po;

import com.foflo.test.AbstractSeleniumTest;
import com.foflo.test.WebElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Cub Profile Preview Page Object
 */

@SuppressWarnings("UnusedDeclaration")
public class ProfilePreviewPageObject extends AbstractSeleniumTest {

	private final Logger logger = LoggerFactory
			.getLogger(ProfilePreviewPageObject.class);

	@FindBy(css = "div[class^='bigHeader']")
	private WebElement pageTitle;

	@FindBy(xpath = "//button[contains(text(),'Start')]")
	private WebElement startButton;

	@FindBy(xpath = "//div[contains(text(),'Profiles this session')]")
	private WebElement profilesSession;

	@FindBy(css = "button[id='doneButton']")
	private WebElement doneButton;

	@FindBy(css = "div[class=\"boldTitle ng-binding\"]")
	private WebElement personID;

	@FindBy(css = "div[section=\"name\"] div.textSmall.prof_prev_bor_bottom > span")
	private WebElement personFullName;

	@FindBy(css = "#unread-note:nth-child(2)")
	private WebElement commentLabelWithNoData;

	@FindBy(css = "#unread-note:nth-child(1)")
	private WebElement commentLabelWithData;

	@FindBy(css = "#comment")
	private WebElement commentTextBox;

	@FindBy(css = "#saveCommentId")
	private WebElement saveCommentBtn;

	@FindBy(css = "#unread-note:nth-child(1) > div > em")
	private WebElement commentText;

	@FindBy(css = "div[ng-class=\"myClass13\"][ng-hide^=\"profile\"]")
	private WebElement bioWithNoData;

	@FindBy(css = "div[ng-class=\"myClass13\"]:nth-child(2)")
	private WebElement bioWithData;

	@FindBy(css = "#bioText")
	private WebElement bioTextBox;

	@FindBy(css = "#sourceBio")
	private WebElement bioSourceUrlTextBox;

	@FindBy(css = "#bioSave")
	private WebElement bioSaveBtn;

	@FindBy(css = "div[ng-model=\"myText\"]")
	private WebElement bioText;

	public ProfilePreviewPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public void gotoProfilePreview() {
		startButton.click();
		waitUntilReady(profilesSession);
	}

	public boolean isProfilePreviewPage() {
		waitUntil((WebDriver input) -> !pageTitle.getText().isEmpty());
		return pageTitle.getText().equals("Profile Preview");
	}

	public String get_person_name() {
		WebElements.waitUntilElementFound(personFullName);
		WebElements.waitUntilElementFoundText(personFullName);
		String personName = this.personFullName.getText();
		return personName;
	}

	public void pressDoneButton() {

		WebElements.waitAndClick(doneButton);

		WebElements.waitUntilElementFoundText(personID);
		String idElementText = personID.getText();

		System.out.println("Previous person ID:" + idElementText);
		doneButton.click();
		waitUntil((WebDriver input) -> !idElementText
				.equals(personID.getText()));

		WebElements.waitUntilElementFoundText(personID);
		System.out.println("New person ID:" + personID.getText());
	}

	public void add_new_comment() {
		WebElements.waitUntilElementFound(personFullName);
		WebElements.waitUntilElementFoundText(personFullName);
		if (commentLabelWithNoData.isDisplayed()) {
			WebElements.waitAndClick(commentLabelWithNoData);
		} else if (commentLabelWithData.isDisplayed()) {
			WebElements.waitAndClick(commentLabelWithData);
		}
		WebElements.waitUntilElementFound(commentTextBox);
		commentTextBox.sendKeys("Test Comment");
		WebElements.waitAndClick(saveCommentBtn);
	}

	public String get_comment_text() {
		WebElements.waitUntilElementFound(commentText);
		WebElements.waitUntilElementFoundText(commentText);
		String comment = commentText.getText();
		return comment;
	}

	public void add_new_bio(String bio, String url) {

		WebElements.waitUntilElementFound(personFullName);
		WebElements.waitUntilElementFoundText(personFullName);

		if (bioWithData.isDisplayed()) {
			logger.info("Found Bio");
			WebElements.waitAndClick(bioWithData);
		}
		else if (bioWithNoData.isDisplayed()) {
			logger.info("Bio with no data");
			WebElements.waitAndClick(bioWithNoData);
		} 
		WebElements.waitUntilElementFound(bioTextBox);
		bioTextBox.sendKeys(bio);
		bioSourceUrlTextBox.sendKeys(url);
		WebElements.waitAndClick(bioSaveBtn);
		// WebElements.waitUntilElementNotFound(bioText);

		int counter = 0;
		// Times out if unable to get the new bio text same as what added
		while (counter <= 60) {
			String str =get_bio_text();
			
			try {
				logger.info("New bio:  " + str);
				logger.info("property bio:  " + bio);

				// Loop until the new bio is the same as what added
				if(str !=null && bio !=null){
					if (str.equals(bio)) {
						logger.info("Bio changed Successfully!");
						break;
					}
				}
				Thread.sleep(1000);
				counter++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String get_bio_text() {
		if(!bioText.isDisplayed()) return null;
		WebElements.waitUntilElementFound(bioText);
		WebElements.waitUntilElementFoundText(bioText);
		String biography = bioText.getText();
		return biography;
	}
}
