package com.foflo.test.tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.foflo.po.CubHomePageObject;
import com.foflo.po.CubLoginPageObject;

public class ProfileMergeTestPageDisplay extends CubInAutomationTests{
	
	
	@Test
	public void is_profile_merge_box_present() {
		
		logger.info("ProfileMergeTestPageDisplay ");
		
		this.driver = driverFactory.get();
		
		driver.get(env.getRequiredProperty("cubURL"));
		
		driver.manage().window().maximize();
		
		CubLoginPageObject cubLoginPageObject = PageFactory.initElements(driver, CubLoginPageObject.class);
		
		CubHomePageObject homePage = cubLoginPageObject.gotoCubHomePage(env.getRequiredProperty("cubUserName"),env.getRequiredProperty("cubPassword"));
	}

}
