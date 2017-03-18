package com.guillermods.common.security.config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfig.class})
@WebAppConfiguration
public class WebTestConfigAware {

  @Autowired
  private WebApplicationContext context;

  protected MockMvc mockMvc;

  @Autowired
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    DeviceResolverRequestFilter deviceResolverRequestFilter = new DeviceResolverRequestFilter();

    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .addFilters(this.springSecurityFilterChain, deviceResolverRequestFilter).build();
  }



}
