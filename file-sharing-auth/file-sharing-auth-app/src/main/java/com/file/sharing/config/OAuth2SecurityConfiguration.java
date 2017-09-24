package com.file.sharing.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class OAuth2SecurityConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthorizationServerProperties authorizationServerProperties;

	@Autowired
	@Qualifier(value = Constants.PG_DATA_SOURCE_NAME)
	private DataSource dataSource;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess(authorizationServerProperties.getTokenKeyAccess());
	}

	@Bean
	@ConfigurationProperties("jwt")
	JwtAccessTokenConverter jwtAccessTokenConverter() {
		return new JwtAccessTokenConverter();
	}

}
