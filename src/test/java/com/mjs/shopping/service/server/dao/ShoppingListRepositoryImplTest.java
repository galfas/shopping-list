package com.mjs.shopping.service.server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mjs.shopping.service.server.dao.impl.ShoppingListRepositoryImpl;
import com.mjs.shopping.service.server.dao.impl.ShoppingListRepositoryImplMongo;
import com.mjs.shopping.service.server.model.ShoppingList;

import static com.mjs.shopping.service.server.TestUtils.ShoppingListUtils.buildEmptyShoppingList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingListRepositoryImplTest {

  @Mock
  private ShoppingListRepositoryImplMongo shoppingListRepositoryImplMongo;

  @InjectMocks
  ShoppingListRepositoryImpl shoppingListRepository = new ShoppingListRepositoryImpl();

  @Test
  public void shouldInsertShopingListAndReturnIt() {
    String listId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    when(shoppingListRepositoryImplMongo.save(expectedShoppingList)).thenReturn(expectedShoppingList);

    ShoppingList receivedShoppingList = shoppingListRepository.insert(expectedShoppingList);

    assertNotNull(receivedShoppingList);
    assertEquals(expectedShoppingList, receivedShoppingList);
    verify(shoppingListRepositoryImplMongo, times(1)).findOne(listId);
  }

  @Test(expected = Exception.class)
  public void shouldRepassExceptionReceivedFromRepositoryMongoWhenCreateList() {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    when(shoppingListRepositoryImplMongo.save(expectedShoppingList)).thenThrow(Exception.class);

    shoppingListRepository.insert(expectedShoppingList);
  }


  @Test
  public void shouldFetchShoppingListById() {
    String listId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    when(shoppingListRepositoryImplMongo.findOne(listId)).thenReturn(expectedShoppingList);

    ShoppingList receivedShoppingList = shoppingListRepository.fetch(listId);

    assertNotNull(receivedShoppingList);
    assertEquals(expectedShoppingList, receivedShoppingList);
    verify(shoppingListRepositoryImplMongo, times(1)).findOne(listId);
  }

  @Test(expected = Exception.class)
  public void shouldRepassExceptionReceivedFromRepositoryMongoWhenFetchingShoppingList() {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();
    String givenListId = "1";

    when(shoppingListRepositoryImplMongo.findOne(givenListId)).thenThrow(Exception.class);

    shoppingListRepository.fetch(givenListId);
  }
}