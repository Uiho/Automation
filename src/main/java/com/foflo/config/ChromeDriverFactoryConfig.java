package com.foflo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.function.Supplier;

/**
 * Selenium chrome driver configuration.
 */
@Configuration
public class ChromeDriverFactoryConfig {

	@Bean
	@DependsOn("systemProperties")
	Supplier<WebDriver> getDriverFactory() {
		return ChromeDriver::new;
	}

}
