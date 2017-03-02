package com.guillermods.common.security.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.test.web.servlet.ResultActions;

import com.guillermods.common.security.config.TestUtil;
import com.guillermods.common.security.config.WebSecurityConfigurationAware;

public class AuthenticationControllerTest extends WebSecurityConfigurationAware {

	@Test
	public void testAuthenticationRequest() throws Exception {
		AuthenticationRequestDto authentication = new AuthenticationRequestDto();
		authentication.setUsername("admin");
		authentication.setUsername("Test1234");
		
		Device device = new Device() {
			
			@Override
			public boolean isTablet() {
				return false;
			}
			
			@Override
			public boolean isNormal() {
				return true;
			}
			
			@Override
			public boolean isMobile() {
				return false;
			}
			
			@Override
			public DevicePlatform getDevicePlatform() {
				
				return DevicePlatform.UNKNOWN;
			}
		};
		ResultActions res = mockMvc.perform(post("/auth", authentication, device).contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(authentication)));
		res.andExpect(status().isOk());
	}

}
