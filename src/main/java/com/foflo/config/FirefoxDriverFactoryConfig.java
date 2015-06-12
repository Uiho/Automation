package com.foflo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.function.Supplier;

/**
 * Selenium firefox driver configuration.
 */
@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
@Configuration
public class FirefoxDriverFactoryConfig {

	@Bean
	@DependsOn("systemProperties")
	Supplier<WebDriver> getDriverFactory() {
		return FirefoxDriver::new;
	}

}
