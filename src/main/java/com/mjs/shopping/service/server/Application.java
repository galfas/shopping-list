package com.mjs.shopping.service.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.mjs.shopping.service.server.interceptor.IdentityAuthTokenValidationInterceptor;
import com.mjs.shopping.service.server.security.provider.IdentityAuthClient;

@SpringBootApplication
@ComponentScan("com.mjs.shopping.service")
public class Application extends WebMvcConfigurationSupport {

  @Autowired
  IdentityAuthClient identityAuthClient;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(new IdentityAuthTokenValidationInterceptor(identityAuthClient))
      .addPathPatterns("/**");
  }
}
