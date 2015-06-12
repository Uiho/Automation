package com.foflo.po;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Cub Login Page Object
 */
@SuppressWarnings("UnusedDeclaration")
public class CubLoginPageObject {

	private WebDriver driver = null;

	@FindBy(css = "#username")
	private WebElement username;

	@FindBy(css = "#password")
	private WebElement password;

	@FindBy(css = "#forgotPwd")
	private WebElement forgotPwd;

	@FindBy(css = "#loginSubmit")
	private WebElement loginSubmit;

	public CubLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@SuppressWarnings("WeakerAccess")
	public void enterUserName(String username) {
		this.username.clear();
		this.username.sendKeys(username);
	}

	@SuppressWarnings("WeakerAccess")
	public void enterPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public CubHomePageObject gotoCubHomePage(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		loginSubmit.click();
		return PageFactory.initElements(driver, CubHomePageObject.class);
	}

	public boolean isCubLoggedOut() {
		String pageSource = driver.getPageSource();
		return pageSource.contains("Forgot Password");
	}
}
