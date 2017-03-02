/**
 * Copyright (C) 2016 Guillermo Díaz Solís.
 * Todos los derechos reservados.
 */
package com.guillermods.common.security.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.guillermods.common.security.config.SecurityConstants;
import com.guillermods.common.security.jwt.TokenGenerator;



public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	final static Logger logger = Logger.getLogger(AuthenticationTokenFilter.class);

	@Autowired
	private TokenGenerator tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		logger.info("Filtering headers...");

		tokenUtils = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())
				.getBean(TokenGenerator.class);
		userDetailsService = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())
				.getBean(UserDetailsService.class);

		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, " + SecurityConstants.TOKEN_HEADER);

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// Obtiene de las cabeceras el token
		String authToken = httpRequest.getHeader(SecurityConstants.TOKEN_HEADER);

		logger.info(SecurityConstants.TOKEN_HEADER + ": " + authToken);

		// Obtiene del token el username
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (this.tokenUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

}
