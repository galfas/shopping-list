package com.mjs.shopping.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.mjs.shopping.service.common.ServerConfig;
import com.mjs.shopping.service.server.Application;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/e2etest/resources/"
)
public class RunEndToEndTests {


  public static ConfigurableApplicationContext app;

  @BeforeClass
  public static void setUp() {

    System.setProperty("spring.profiles.active", ServerConfig.springActiveProfile);
    System.setProperty("server.port", String.valueOf(ServerConfig.serverPort));
    System.setProperty("server.contextPath", ServerConfig.serverContextPath);
    app = SpringApplication.run(Application.class);

    RestAssured.defaultParser = Parser.JSON;
  }


  @AfterClass
  public static void tearDown() {
    app.stop();
  }
}
