package com.mjs.shopping.service.server.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mjs.shopping.service.server.business.impl.ShoppingListBoImpl;
import com.mjs.shopping.service.server.dao.impl.ShoppingListRepositoryImpl;
import com.mjs.shopping.service.server.dao.impl.ShoppingListRepositoryImplMongo;
import com.mjs.shopping.service.server.model.ShoppingList;

import static com.mjs.shopping.service.server.TestUtils.ShoppingListUtils.buildEmptyShoppingList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingListBoImplTest {

  @Mock
  private ShoppingListRepositoryImpl shoppingListRepositoryImpl;


  @InjectMocks
  ShoppingListBo shoppingListBo = new ShoppingListBoImpl();

  @Test
  public void shouldInsertShoppingListIntoRepository() {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    shoppingListBo.create(expectedShoppingList);
    verify(shoppingListRepositoryImpl, times(1)).insert(expectedShoppingList);
  }

  @Test(expected = Exception.class)
  public void shouldPassExceptionFromRepositoryWhileCreateList() {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    doThrow(new Exception()).when(shoppingListRepositoryImpl).insert(expectedShoppingList);

    shoppingListBo.create(expectedShoppingList);
  }

  @Test
  public void shouldRetrieveShoppingListFromRepository() {
    String listId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    when(shoppingListRepositoryImpl.fetch(listId)).thenReturn(expectedShoppingList);

    ShoppingList receivedShoppingList = shoppingListBo.fetchShoppingListBy(listId);

    assertNotNull(receivedShoppingList);
    assertEquals(expectedShoppingList, receivedShoppingList);
    verify(shoppingListRepositoryImpl, times(1)).fetch(listId);
  }

  @Test(expected = Exception.class)
  public void shouldPassExceptionFromRepositoryWhileRetrievingList() {
    String listId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    doThrow(new Exception()).when(shoppingListRepositoryImpl).fetch(listId);

    shoppingListBo.fetchShoppingListBy(listId);
  }
}
