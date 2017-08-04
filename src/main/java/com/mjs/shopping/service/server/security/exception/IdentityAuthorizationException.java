package com.mjs.shopping.service.server.security.exception;

@SuppressWarnings("serial")
public class IdentityAuthorizationException extends IdentityClientException {

  private final int httpStatusCode;

  public IdentityAuthorizationException(String msg, int httpStatusCode) {
    super(msg);
    this.httpStatusCode = httpStatusCode;
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }
}
