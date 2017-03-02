package com.guillermods.common.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.guillermods.common.security.jwt.TokenGenerator;
import com.guillermods.common.security.service.UserDetailsServiceImpl;
import com.guillermods.common.security.web.EntryPointUnauthorizedHandler;

@Configuration
@ComponentScan(basePackageClasses = {UserDetailsServiceImpl.class, EntryPointUnauthorizedHandler.class})
@Import(value = {PersistenceTestConfig.class})
@PropertySource({"classpath:application.properties"})
public class RootTestConfig {

  @Value("${token.secret}")
  private String secret;

  @Value("${token.expiration}")
  private String expiration;

  @Bean
  public TokenGenerator tokenUtils() {
	  TokenGenerator tokenGenerator = new TokenGenerator(secret, new Long(expiration));
    return tokenGenerator;
  }
}