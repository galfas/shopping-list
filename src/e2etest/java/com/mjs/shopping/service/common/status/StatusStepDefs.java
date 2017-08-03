package com.mjs.shopping.service.common.status;

import com.jayway.restassured.response.ResponseOptions;
import com.mjs.shopping.service.common.BaseApiClient;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class StatusStepDefs {
  ResponseOptions responseOptions;

  @Given("^app has started$")
  public void app_has_started() {
    responseOptions = BaseApiClient.givenApiClient().get("/status");
    assertThat(responseOptions.statusCode(), equalTo(200));
  }

  @And("^app is health$")
  public void app_body_status_is() {
    String body = responseOptions.body().print();
    assertThat("ok", equalTo(body));
  }
}