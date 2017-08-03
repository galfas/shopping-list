package com.mjs.shopping.service.common.steps;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.jayway.restassured.response.Response;
import com.mjs.shopping.service.server.model.Item;
import com.mjs.shopping.service.server.model.ListItem;
import com.mjs.shopping.service.server.model.ShoppingList;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.mjs.shopping.service.common.BaseApiClient.givenApiClient;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ShoppingListStepDefs {

  Response response;
  ShoppingList shoppingList;

  static String DEFAULT_USER = "1234";
  static Integer INITIAL_VERSION = 1;

  @Given("^I have an empty list$")
  public void i_have_an_empty_list() {
    shoppingList = new ShoppingList(DEFAULT_USER, new ArrayList<ListItem>());
  }

  @And("^the list has \"([^\"]*)\" \"([^\"]*)\" and check status is \"([^\"]*)\"$")
  public void the_list_has(String itemName, int quantity, boolean isChecked) {
    Item item = new Item(itemName, String.format("description for s%", itemName));

    ListItem listItem = new ListItem(item, quantity, INITIAL_VERSION, isChecked);
    shoppingList.addListItem(listItem);
  }

  @When("^I request the list creation")
  public void i_request_the_list_creation() {
    response = null;
    response = givenApiClient()
      .body(shoppingList)
      .post("/list");

    assertThat(response.statusCode(), equalTo(201));
  }

  @Then("^I should receive a valid list with an id$")
  public void i_should_receive_a_valid_list_with_an_id() {
    Map listAsMap = response.body().as(Map.class);
    Assert.assertTrue(listAsMap.get("id").toString().length() >0);
    Assert.assertEquals(shoppingList.getOwner(), listAsMap.get("owner"));
    shoppingList = null;
  }
}
