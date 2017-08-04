package com.mjs.shopping.service.server.controller;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mjs.shopping.service.server.business.ShoppingListBo;
import com.mjs.shopping.service.server.model.ShoppingList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.mjs.shopping.service.server.TestUtils.ShoppingListUtils.buildEmptyItemList;
import static com.mjs.shopping.service.server.TestUtils.ShoppingListUtils.buildEmptyShoppingList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingListControllerTest {

  @Mock
  ShoppingListBo shoppingListBo;

  @InjectMocks
  ShoppingListController shoppingListController = new ShoppingListController();


  @Test
  public void shouldRetrieveAListById() throws IOException {
    String givenListId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    when(shoppingListBo.fetchShoppingListBy(givenListId)).thenReturn(expectedShoppingList);

    ShoppingList receivedList = shoppingListController.fetchList(givenListId);

    assertNotNull(receivedList);
    assertEquals(expectedShoppingList, receivedList);
  }

  @Test(expected = Exception.class)
  public void shouldPassTheExceptionReceivedWhileRetrievingShoppingList() throws IOException {
    String givenListId = "1";

    when(shoppingListBo.fetchShoppingListBy(givenListId)).thenThrow(Exception.class);

    shoppingListController.fetchList(givenListId);
  }

  @Test
  public void shouldCreateANewShoppingList() throws IOException {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    shoppingListController.create(expectedShoppingList);
    verify(shoppingListBo, times(1)).create(expectedShoppingList);
  }

  @Test(expected = Exception.class)
  public void shouldPassExceptionFromBusinnesWhenCreateANewShoppingList() throws IOException {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    doThrow(new Exception()).when(shoppingListBo).create(expectedShoppingList);

    shoppingListController.create(expectedShoppingList);
  }

  @Test(expected = NotImplementedException.class)
  public void shouldReceiveNotImplementForUpdateMethod() throws IOException {
    String givenListId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    shoppingListController.update(givenListId, expectedShoppingList);
  }

  @Test(expected = NotImplementedException.class)
  public void shouldReceiveNotImplementForUpdateProductMethod() throws IOException {
    String givenListId = "1";
    Integer version = 1;
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    shoppingListController.updateProduct(givenListId, version, buildEmptyItemList());
  }

  @Test(expected = NotImplementedException.class)
  public void shouldReceiveNotImplementForDeleteProductMethod() throws IOException {
    String givenListId = "1";
    Integer version = 1;
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    shoppingListController.deleteList(givenListId);
  }
}
