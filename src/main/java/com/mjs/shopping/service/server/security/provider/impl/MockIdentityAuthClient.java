package com.mjs.shopping.service.server.security.provider.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mjs.shopping.service.server.security.exception.IdentityAuthenticationException;
import com.mjs.shopping.service.server.security.exception.IdentityAuthorizationException;
import com.mjs.shopping.service.server.security.provider.IdentityAuthClient;

@Component
public class MockIdentityAuthClient implements IdentityAuthClient {

  public static final String INVALID_AUTHENTICATION_TOKEN = "1234";
  public static final String INVALID_AUTHORIZATION_TOKEN = "5678";

  public void authorizeToken(String token, String scope) {

    //Test Error in the authentication
    if (token.equals(INVALID_AUTHENTICATION_TOKEN)) {
      throw new IdentityAuthenticationException("User not authenticated properly");
    }

    //Test Error in the authorization
    if (token.equals(INVALID_AUTHORIZATION_TOKEN)) {
      throw new IdentityAuthorizationException("User Doesn't have the right access", HttpStatus.FORBIDDEN.value());
    }
  }
}