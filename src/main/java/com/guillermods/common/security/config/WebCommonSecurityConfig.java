/**
 * Copyright (C) 2016 Guillermo Díaz Solís. Todos los derechos reservados.
 */
package com.guillermods.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.guillermods.common.security.web.filter.AuthenticationTokenFilter;

/**
 * Configuración de seguridad de la aplicación
 *
 * @author Guillermo B Díaz Solís
 * @since 12 de jul. de 2016
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebCommonSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  @Qualifier("authenticationEntryPoint")
  private AuthenticationEntryPoint authenticationEntryPoint;

  /**
   * Configuración del servicio que resuelve los usuarios de la aplicación
   *
   * @param auth Inyectado por Spring
   * @throws Exception En caso de haber un error a la hora de obtener algun usuario
   */
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }

  /**
   * Configuración de seguridad de la aplicación web
   *
   * @param http Recibe un HttpSecuriy inyectado por Spring
   * @throws Exception Lanza una excepción en caso de romper alguna de las relgas o al haber una
   *         mala configuración.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
  	System.out.println("seguridad");
      http.csrf()
      .disable()
      .exceptionHandling()
      .authenticationEntryPoint(authenticationEntryPoint).and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS, "/**")
      .permitAll().antMatchers("/auth/**")
      .permitAll()
      .anyRequest()
      .authenticated();
      http
      .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    authenticationTokenFilter.setAuthenticationManager(super.authenticationManagerBean());
    return authenticationTokenFilter;
  }

}
