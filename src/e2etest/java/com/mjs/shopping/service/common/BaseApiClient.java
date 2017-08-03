package com.mjs.shopping.service.common;

import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.DecoderConfig.decoderConfig;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;

public class BaseApiClient {

  public static RequestSpecification givenApiClient() {
    String baseUrl = "http://localhost:8080" + ServerConfig.serverContextPath;
    RestAssuredConfig config = newConfig()
      .encoderConfig(encoderConfig().defaultContentCharset("utf-8"))
      .decoderConfig(decoderConfig().defaultContentCharset("utf-8"));

    return given().contentType(ContentType.JSON).port(ServerConfig.serverPort)
      .config(config)
      .baseUri(baseUrl)
      .log().all();
  }
}
