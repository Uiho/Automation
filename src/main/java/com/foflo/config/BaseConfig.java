package com.foflo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foflo.dao.CubPersonDao;
import com.foflo.dao.impl.CubPersonDaoImpl;

/**
 * Basic test configuration.
 */
@Configuration
public class BaseConfig {

	private static final Logger log = LoggerFactory.getLogger(BaseConfig.class);

	@Bean
	public static CubPersonDao cubPersonEventDaoConfigurer() {
		return new CubPersonDaoImpl();
	}
}
