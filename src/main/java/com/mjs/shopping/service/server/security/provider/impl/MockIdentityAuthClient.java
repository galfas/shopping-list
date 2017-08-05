package com.mjs.shopping.service.server.security.provider.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mjs.shopping.service.server.security.exception.IdentityAuthenticationException;
import com.mjs.shopping.service.server.security.exception.IdentityAuthorizationException;
import com.mjs.shopping.service.server.security.provider.IdentityAuthClient;

@Component
public class MockIdentityAuthClient implements IdentityAuthClient {

  Map<String, String> tokenByUserUuid = new HashMap<>();

  public MockIdentityAuthClient() {

    tokenByUserUuid.put("token1", "useruuid1");
    tokenByUserUuid.put("token2", "useruuid2");
    tokenByUserUuid.put("token3", "useruuid3");
    tokenByUserUuid.put("token4", "useruuid4");
    tokenByUserUuid.put("token5", "useruuid5");
  }


  @Override
  public String fetchAuthorizedUser(String token, String scope) {

    //Test Error in the authentication
    if (!tokenByUserUuid.containsKey(token)) {
      throw new IdentityAuthenticationException("User not authenticated properly");
    }

    //Test Error in the authorization
    if (tokenByUserUuid.get(token) == "useruuid4" || tokenByUserUuid.get(token) == "useruuid5") {
      throw new IdentityAuthorizationException("User Doesn't have the right access", HttpStatus.FORBIDDEN.value());
    }
    return tokenByUserUuid.get(token);
  }
}