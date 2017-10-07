package com.file.sharing.core.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hibernate")
public class HibernateProperties {

	private String dialect;

	private boolean showSql;

	private boolean formatSql;

	private String hbm2ddlAuto;

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public boolean isShowSql() {
		return showSql;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

	public boolean isFormatSql() {
		return formatSql;
	}

	public void setFormatSql(boolean formatSql) {
		this.formatSql = formatSql;
	}

	public String getHbm2ddlAuto() {
		return hbm2ddlAuto;
	}

	public void setHbm2ddlAuto(String hbm2DdlAuto) {
		this.hbm2ddlAuto = hbm2DdlAuto;
	}

	public Properties getAsProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", this.dialect);
		properties.put("hibernate.show_sql", this.showSql);
		properties.put("hibernate.format_sql", this.formatSql);
		properties.put("hibernate.hbm2ddl.auto", this.hbm2ddlAuto);
		return properties;
	}

}
