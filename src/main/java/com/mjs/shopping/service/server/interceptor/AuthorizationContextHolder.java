package com.mjs.shopping.service.server.interceptor;

public final class AuthorizationContextHolder {

  private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

  private AuthorizationContextHolder() {}

  public static void clearContext() {
    CONTEXT_HOLDER.remove();
  }

  public static String getUserUuid() {
    return CONTEXT_HOLDER.get();
  }

  public static void setUserUuid(String userUuid) {
    CONTEXT_HOLDER.set(userUuid);
  }
}