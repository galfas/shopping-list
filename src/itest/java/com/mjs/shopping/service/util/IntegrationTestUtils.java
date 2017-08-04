package com.mjs.shopping.service.util;


import org.springframework.http.HttpHeaders;

public class IntegrationTestUtils {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  public static HttpHeaders validAppUserHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION_HEADER, "Bearer appToken");

    return headers;
  }

}
