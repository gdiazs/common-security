package com.guillermods.common.security.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.guillermods.common.security.config.TestUtil;
import com.guillermods.common.security.config.WebSecurityConfigurationAware;

public class AuthenticationControllerTest extends WebSecurityConfigurationAware {



	
	@Test
	public void testAuthenticationRequest() throws Exception {
		AuthenticationRequestDto authentication = new AuthenticationRequestDto();
		authentication.setUsername("admin");
		authentication.setPassword("Test1234");
		String jsonAuthentication = TestUtil.convertObjectToJsonString(authentication);
		System.out.println(jsonAuthentication);
		ResultActions res = mockMvc.perform(post("/auth").contentType(TestUtil.APPLICATION_JSON_UTF8)
		        .content(jsonAuthentication));
		res.andExpect(status().isOk());
	}


}
