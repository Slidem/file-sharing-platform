package com.file.sharing.config;

import static com.file.sharing.config.Constants.PG_DATA_SOURCE_NAME;
import static com.file.sharing.config.Constants.PG_JDBC_TX_NAME;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Alexandru
 *
 *         Database configuration.
 *
 */
@Configuration
public class DatabaseConfig {

	// ----------- POSTGRESQL and JDBC template configuration ----------

	@Bean(name = PG_JDBC_TX_NAME)
	public DataSourceTransactionManager pgJdbcTemplateTransactionManager(
			@Qualifier(PG_DATA_SOURCE_NAME) DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(@Qualifier(PG_DATA_SOURCE_NAME) DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = PG_DATA_SOURCE_NAME)
	public DataSource dataSource(Environment environment) {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(environment.getProperty("postgres.jdbc.url"));
		ds.setUsername(environment.getProperty("postgres.jdbc.username"));
		ds.setPassword(environment.getProperty("postgres.jdbc.password"));

		return ds;
	}
}
