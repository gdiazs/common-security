package com.guillermods.common.security.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.guillermods.common.security.config.TestUtil;
import com.guillermods.common.security.config.WebTestConfigAware;

public class AuthenticationControllerTest extends WebTestConfigAware {

  @Test
  public void testAuthenticationRequest() throws Exception {
    AuthenticationRequestDto authentication = new AuthenticationRequestDto();
    authentication.setUsername("admin");
    authentication.setPassword("Test1234");

    String jsonAuthentication = TestUtil.convertObjectToJsonString(authentication);

    ResultActions res = mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonAuthentication));
    res.andExpect(status().isOk());


  }



}
