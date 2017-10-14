package com.file.sharing.back.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author slidem
 *
 */
@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		 requestMatchers().antMatchers("/**")
		 .and()
		 .authorizeRequests()
		 .anyRequest()
		 .access("#oauth2.hasScope('filesharing')");
	}

	@Primary
	@Bean
	public RemoteTokenServices tokenService(OauthProperties oauthProperties) {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl(oauthProperties.getCheckTokenUrl());
		tokenService.setClientId(oauthProperties.getClientId());
		tokenService.setClientSecret(oauthProperties.getClientSecret());
		return tokenService;
	}
	
	@Bean
	@ConfigurationProperties(prefix="oauth")
	public OauthProperties oauthProperties() {
		return new OauthProperties();
	}

}
