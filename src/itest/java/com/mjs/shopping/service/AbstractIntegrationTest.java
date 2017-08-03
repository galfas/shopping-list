package com.mjs.shopping.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;
import com.mjs.shopping.service.server.Application;

import groovy.util.logging.Slf4j;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@DirtiesContext
@Slf4j
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class AbstractIntegrationTest {

  private static final Logger logger = LoggerFactory.getLogger(AbstractIntegrationTest.class);

  protected static ConfigurableWebApplicationContext context;

  public MockMvc mockMvc;

  @Autowired
  public ObjectMapper mapper;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.context).build();
  }

  @Autowired
  public void setContext(ConfigurableWebApplicationContext context) {
    AbstractIntegrationTest.context = context;
  }

  @BeforeClass
  public static void init() {
  }


  @AfterClass
  public static void tearDown() {
    Stopwatch stopwatch = Stopwatch.createStarted();
    logger.info("Closing spring application context...");
  }
}
