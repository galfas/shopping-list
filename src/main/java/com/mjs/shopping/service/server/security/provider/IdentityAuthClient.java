package com.mjs.shopping.service.server.security.provider;

import com.mjs.shopping.service.server.security.exception.IdentityClientException;

public interface IdentityAuthClient {

  /**
   * Call /authorization api endpoint
   *
   * @param token token to be validated
   * @param scope authorization scope to be validated
   * @throws IdentityClientException if authorization fails, IdentityClientException will be thrown
   */
  void authorizeToken(String token, String scope) throws IdentityClientException;

}
