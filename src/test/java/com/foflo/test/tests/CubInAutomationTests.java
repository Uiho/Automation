package com.foflo.test.tests;

import static org.junit.Assert.assertTrue;

import java.util.function.Supplier;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import com.foflo.CubInAutomation;
import com.foflo.config.BaseConfig;
import com.foflo.config.ChromeDriverFactoryConfig;
import com.foflo.config.CubDatabaseConfig;
import com.foflo.config.SeleniumConfig;
import com.foflo.config.TestConfig;
import com.foflo.po.CubHomePageObject;
import com.foflo.po.CubLoginPageObject;

@ContextConfiguration(
		classes = {
				TestConfig.class,
				CubDatabaseConfig.class,
				BaseConfig.class,
				SeleniumConfig.class,
				ChromeDriverFactoryConfig.class
		}
)
@TestPropertySource(locations = {
		"classpath:target-${target:default}.properties"
})
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CubInAutomation.class)
@WebAppConfiguration
public abstract class CubInAutomationTests extends AbstractTestNGSpringContextTests {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public Supplier<WebDriver> driverFactory;
	
	@Autowired
	public Environment env;
	
	public WebDriver driver;
	
	@Test
	public void startCubxAutomationApplicationTests() {
		
		logger.info("getting started CubxAutomationApplicationTests");
		
		assertTrue(true);
	}
}
