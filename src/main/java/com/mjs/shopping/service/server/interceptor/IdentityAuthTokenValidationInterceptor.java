package com.mjs.shopping.service.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mjs.shopping.service.server.security.annotation.AuthenticationRequired;
import com.mjs.shopping.service.server.security.exception.IdentityAuthenticationException;
import com.mjs.shopping.service.server.security.exception.IdentityAuthorizationException;
import com.mjs.shopping.service.server.security.provider.IdentityAuthClient;

import static org.springframework.util.StringUtils.hasText;

public class IdentityAuthTokenValidationInterceptor extends HandlerInterceptorAdapter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";

  private static final Log LOGGER = LogFactory.getLog(IdentityAuthTokenValidationInterceptor.class);

  private IdentityAuthClient identityAuthClient;

  public IdentityAuthTokenValidationInterceptor(IdentityAuthClient identityAuthClient) {
    this.identityAuthClient = identityAuthClient;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    AuthenticationRequired authenticationRequiredAnnotation = this.getAnnotationFromHandlerMethod((HandlerMethod) handler);
    if (authenticationRequiredAnnotation == null) {
      return true;
    }

    try {
      attemptToAuthenticateUser(request, authenticationRequiredAnnotation);
      return true;
    } catch (IdentityAuthenticationException ex) {
      LOGGER.info("Returning unauthorized response for authentication exception: " + ex.getMessage());
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    } catch (IdentityAuthorizationException ex) {
      LOGGER.info("Returning error response for authorization exception: " + ex.getMessage());
      response.sendError(ex.getHttpStatusCode(), ex.getMessage());
    }
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    AuthorizationContextHolder.clearContext();
  }

  private void attemptToAuthenticateUser(HttpServletRequest request, AuthenticationRequired authenticationRequiredAnnotation) {
    String token = getAuthorizationToken(request);
    String scope = getScopeFromAnnotation(authenticationRequiredAnnotation);

    AuthorizationContextHolder.setUserUuid(this.identityAuthClient.fetchAuthorizedUser(token, scope));
  }

  protected AuthenticationRequired getAnnotationFromHandlerMethod(HandlerMethod handlerMethod) {
    AuthenticationRequired methodAnnotation = handlerMethod.getMethodAnnotation(AuthenticationRequired.class);

    if (methodAnnotation == null) {
      Class<?> declaringClass = handlerMethod.getBean().getClass();
      return declaringClass.getAnnotation(AuthenticationRequired.class);
    }

    return methodAnnotation;
  }

  private String getAuthorizationToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

    if (!hasText(authorizationHeader) ||
      !authorizationHeader.startsWith(BEARER_PREFIX)) {
      throw new IdentityAuthenticationException("Token not present on Authorization header");
    }

    return getAuthorizationTokenFromHeader(authorizationHeader);
  }

  private String getAuthorizationTokenFromHeader(String authorizationHeader) {
    return authorizationHeader.replaceFirst(BEARER_PREFIX, "");
  }

  private String getScopeFromAnnotation(AuthenticationRequired authenticationRequiredAnnotation) {
    String scope = authenticationRequiredAnnotation.scope();

    if (!hasText(scope)) {
      throw new IdentityAuthenticationException("Scope not present on @AuthenticationRequired");
    }

    return scope;
  }

}
