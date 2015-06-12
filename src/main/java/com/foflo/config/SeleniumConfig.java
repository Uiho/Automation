package com.foflo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

/**
 * Selenium requires us to have some system properties set before it'll function right.
 * This class will take a list of properties in the Spring environment and push them into
 * Java system properties.
 */
@Configuration
@PropertySource(value = "classpath:env-${env:default}.properties")
public class SeleniumConfig {

	private static final Logger log = LoggerFactory.getLogger(SeleniumConfig.class);
	private static final String PropertiesToSystemProperties = "properties.to.system.properties";

	@Autowired
	ConfigurableEnvironment env;

	/**
	 * Side effects: sets system properties.
	 *
	 * @return The system properties set.
	 */
	@Bean(name = "systemProperties")
	Map<String, String> getApplicationProperties() {
		String propList = env.getProperty(PropertiesToSystemProperties);
		if (propList == null) {
			throw new IllegalArgumentException("No properties " + PropertiesToSystemProperties + " found");
		}
		Map<String, String> pushMap = new HashMap<>();
		String[] props = propList.split(",");
		if (props.length == 0) {
			throw new IllegalArgumentException("Properties " + PropertiesToSystemProperties + " does not specify any properties to push");
		}
		for (String key : props) {
			String value = env.getProperty(key);
			log.info("Pushing property {}={} to system properties", key, value);
			System.setProperty(key, value);
			pushMap.put(key, value);
		}
		return pushMap;
	}

}
