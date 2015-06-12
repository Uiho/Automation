package com.foflo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Selenium requires us to have some system properties set before it'll function right.
 * This class will take a list of properties in the Spring environment and push them into
 * Java system properties.
 */
@Configuration
@PropertySource(value = "classpath:db-cub-${target}.properties")
public class CubDatabaseConfig {

	private static final Logger log = LoggerFactory.getLogger(CubDatabaseConfig.class);

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	DataSource dataSource;

	/**
	 * Fetch a JDBC template for this database.
	 *
	 * @return the JDBC template to set the data.
	 */
	@Bean
	JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(100);
		config.setDriverClassName(env.getRequiredProperty("cub.driverClassName"));
		config.setJdbcUrl(env.getRequiredProperty("cub.url"));
		config.addDataSourceProperty("user", env.getRequiredProperty("cub.username"));
		config.addDataSourceProperty("password", env.getRequiredProperty("cub.password"));
		log.info("Creating new DataSource with config={}", config);
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

}
